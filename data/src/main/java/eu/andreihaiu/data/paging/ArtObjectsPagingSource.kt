package eu.andreihaiu.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import eu.andreihaiu.common.utils.ApiResponse
import eu.andreihaiu.data.mappers.mapToDomain
import eu.andreihaiu.data.services.ArtObjectsService
import eu.andreihaiu.data.utils.safeApiCall
import eu.andreihaiu.domain.entities.ArtObjectEntity
import retrofit2.HttpException
import java.io.IOException

class ArtObjectsPagingSource(
    private val artObjectsService: ArtObjectsService,
) : PagingSource<Int, ArtObjectEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtObjectEntity> {
        return try {
            val currentPage = params.key ?: 1

            when (val artObjects = safeApiCall { artObjectsService.getArtObjects(page = currentPage) }) {
                is ApiResponse.Success -> {
                    LoadResult.Page(
                        data = artObjects.value.artObjects?.map { it.mapToDomain() } ?: emptyList(),
                        prevKey = if (currentPage == 1) null else currentPage - 1,
                        nextKey = if (artObjects.value.artObjects.isNullOrEmpty()) null else currentPage + 1
                    )
                }

                is ApiResponse.Error -> {
                    LoadResult.Error(artObjects.exception)
                }
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtObjectEntity>): Int? {
        return state.anchorPosition
    }
}
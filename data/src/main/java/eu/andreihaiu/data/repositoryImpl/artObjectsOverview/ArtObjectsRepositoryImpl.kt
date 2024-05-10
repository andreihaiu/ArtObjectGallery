package eu.andreihaiu.data.repositoryImpl.artObjectsOverview

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import eu.andreihaiu.common.utils.ApiResponse
import eu.andreihaiu.data.mappers.mapToDomain
import eu.andreihaiu.data.paging.ArtObjectsPagingSource
import eu.andreihaiu.data.services.ArtObjectsService
import eu.andreihaiu.data.utils.safeApiCall
import eu.andreihaiu.domain.entities.ArtObjectDetailsEntity
import eu.andreihaiu.domain.entities.ArtObjectEntity
import eu.andreihaiu.domain.repositories.ArtObjectsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArtObjectsRepositoryImpl(
    private val artObjectsService: ArtObjectsService,
) : ArtObjectsRepository {

    override suspend fun fetchArtObjects(): Flow<PagingData<ArtObjectEntity>> {
        return Pager(config = PagingConfig(
            pageSize = ArtObjectsService.PAGE_SIZE,
            prefetchDistance = 2
        ),
            pagingSourceFactory = { ArtObjectsPagingSource(artObjectsService = artObjectsService) }).flow
    }

    override suspend fun fetchArtObjectDetails(objectId: String): Flow<ArtObjectDetailsEntity?> =
        flow {
            when (val response =
                safeApiCall { artObjectsService.getArtObjectDetails(objectId = objectId) }) {
                is ApiResponse.Success -> {
                    emit(response.value.artObject.mapToDomain())
                }

                is ApiResponse.Error -> {
                    Log.d(TAG, response.exception.localizedMessage.toString())
                    emit(null)
                }
            }
        }

    companion object {
        private const val TAG = "ArtObjectsRepository"
    }
}
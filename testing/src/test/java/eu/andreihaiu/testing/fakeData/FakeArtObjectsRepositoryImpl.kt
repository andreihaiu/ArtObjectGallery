package eu.andreihaiu.testing.fakeData

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.testing.asPagingSourceFactory
import eu.andreihaiu.domain.entities.ArtObjectDetailsEntity
import eu.andreihaiu.domain.entities.ArtObjectEntity
import eu.andreihaiu.domain.repositories.ArtObjectsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/** Fake repository that helps with testing the paging data */
class FakeArtObjectsRepositoryImpl(
    items: List<ArtObjectEntity> = (1..15).toList().map {
        ArtObjectEntity(
            longTitle = null,
            objectNumber = it.toString(),
            principalOrFirstMaker = null,
            imageUrl = null,
            headerImageUrl = null,
            productionPlaces = listOf(),
            title = "Title $it"
        )
    }
) : ArtObjectsRepository {
    private val pagingSourceFactory = items.asPagingSourceFactory()
    private val pagingSource = pagingSourceFactory()

    override suspend fun fetchArtObjects(): Flow<PagingData<ArtObjectEntity>> {
        return Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = { pagingSource }).flow
    }

    override suspend fun fetchArtObjectDetails(objectId: String): Flow<ArtObjectDetailsEntity> {
        return flowOf(
            ArtObjectDetailsEntity(
                acquisitionMethod = null,
                acquisitionDate = null,
                presentingDate = null,
                description = null,
                height = null,
                width = null,
                depth = null,
                documentation = listOf(),
                labelText = null,
                location = null,
                longTitle = null,
                materials = listOf(),
                objectCollection = listOf(),
                objectNumber = null,
                artObjectTypes = listOf(),
                physicalMedium = null,
                plaqueDescriptionEnglish = null,
                principalOrFirstMaker = null,
                productionPlaces = listOf(),
                scLabelLine = null,
                subTitle = null,
                title = "Title",
                imageUrl = null
            )
        )
    }
}
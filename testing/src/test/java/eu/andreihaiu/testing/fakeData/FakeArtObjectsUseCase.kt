package eu.andreihaiu.testing.fakeData

import androidx.paging.PagingData
import eu.andreihaiu.domain.entities.ArtObjectEntity
import eu.andreihaiu.domain.repositories.ArtObjectsRepository
import eu.andreihaiu.domain.usecases.artObjectsOverview.ArtObjectsUseCase
import kotlinx.coroutines.flow.Flow

/** Fake use case that helps with testing the paging data */
class FakeArtObjectsUseCase(
    private val artObjectsRepository: ArtObjectsRepository
) : ArtObjectsUseCase(artObjectsRepository) {
    override suspend operator fun invoke(): Flow<PagingData<ArtObjectEntity>> =
        artObjectsRepository.fetchArtObjects()
}
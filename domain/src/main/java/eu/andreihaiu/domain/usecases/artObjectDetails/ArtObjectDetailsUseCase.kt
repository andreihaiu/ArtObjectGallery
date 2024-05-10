package eu.andreihaiu.domain.usecases.artObjectDetails

import eu.andreihaiu.domain.entities.ArtObjectDetailsEntity
import eu.andreihaiu.domain.repositories.ArtObjectsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtObjectDetailsUseCase @Inject constructor(
    private val artObjectsRepository: ArtObjectsRepository
) {
    suspend operator fun invoke(objectId: String): Flow<ArtObjectDetailsEntity?> =
        artObjectsRepository.fetchArtObjectDetails(objectId = objectId)
}
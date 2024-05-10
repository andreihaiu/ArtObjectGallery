package eu.andreihaiu.domain.usecases.artObjectsOverview

import androidx.paging.PagingData
import eu.andreihaiu.domain.entities.ArtObjectEntity
import eu.andreihaiu.domain.repositories.ArtObjectsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class ArtObjectsUseCase @Inject constructor(
    private val artObjectsRepository: ArtObjectsRepository
) {
    open suspend operator fun invoke(): Flow<PagingData<ArtObjectEntity>> = artObjectsRepository.fetchArtObjects()
}
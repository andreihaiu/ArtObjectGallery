package eu.andreihaiu.domain.repositories

import androidx.paging.PagingData
import eu.andreihaiu.domain.entities.ArtObjectDetailsEntity
import eu.andreihaiu.domain.entities.ArtObjectEntity
import kotlinx.coroutines.flow.Flow

interface ArtObjectsRepository {
    suspend fun fetchArtObjects(): Flow<PagingData<ArtObjectEntity>>
    suspend fun fetchArtObjectDetails(objectId: String): Flow<ArtObjectDetailsEntity?>
}
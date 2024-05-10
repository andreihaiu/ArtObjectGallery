package eu.andreihaiu.data.services

import eu.andreihaiu.data.models.ArtObjectDetailsResponse
import eu.andreihaiu.data.models.ArtObjectsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtObjectsService {

    @GET("api/en/collection")
    suspend fun getArtObjects(
        @Query("p") page: Int,
        @Query("ps") pageSize: Int = PAGE_SIZE,
        @Query("key") apiKey: String = API_KEY,
    ): Response<ArtObjectsResponse>

    @GET("api/en/collection/{objectId}")
    suspend fun getArtObjectDetails(
        @Path("objectId") objectId: String,
        @Query("key") apiKey: String = API_KEY,
    ): Response<ArtObjectDetailsResponse>

    companion object {
        const val BASE_URL = "https://www.rijksmuseum.nl"
        const val API_KEY = "0fiuZFh4"
        const val PAGE_SIZE = 10
    }
}
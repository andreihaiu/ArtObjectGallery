package eu.andreihaiu.data.utils

import eu.andreihaiu.common.utils.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): ApiResponse<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response: Response<T> = apiToBeCalled()
            if (response.isSuccessful) {
                ApiResponse.Success(value = response.body()!!)
            } else {
                ApiResponse.Error(exception = Exception(response.errorBody().toString()))
            }
        } catch (e: HttpException) {
            ApiResponse.Error(exception = Exception(e.message ?: "Something went wrong"))
        } catch (e: IOException) {
            ApiResponse.Error(exception = Exception("Please check your network connection"))
        } catch (e: Exception) {
            ApiResponse.Error(exception = Exception("Something went wrong"))
        }
    }
}

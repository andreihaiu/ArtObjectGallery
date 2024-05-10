package eu.andreihaiu.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.andreihaiu.data.repositoryImpl.artObjectsOverview.ArtObjectsRepositoryImpl
import eu.andreihaiu.data.services.ArtObjectsService
import eu.andreihaiu.domain.repositories.ArtObjectsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val timeout = 20L

    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(ChuckerInterceptor(context))
        .readTimeout(timeout, TimeUnit.SECONDS)
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideArtObjectsService(@ApplicationContext context: Context): ArtObjectsService = Retrofit.Builder()
        .baseUrl(ArtObjectsService.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
        .client(provideOkHttpClient(context))
        .build().create(ArtObjectsService::class.java)

    @Provides
    fun provideArtObjectsRepository(
        artObjectsService: ArtObjectsService
    ): ArtObjectsRepository =
        ArtObjectsRepositoryImpl(artObjectsService)
}
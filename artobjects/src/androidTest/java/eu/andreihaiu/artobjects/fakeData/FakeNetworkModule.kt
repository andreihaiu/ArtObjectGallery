package eu.andreihaiu.artobjects.fakeData

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import eu.andreihaiu.data.di.NetworkModule
import eu.andreihaiu.domain.repositories.ArtObjectsRepository

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [NetworkModule::class])
object FakeNetworkModule {

    @Provides
    fun provideArtObjectsRepository(): ArtObjectsRepository = FakeArtObjectsRepositoryImpl()
}
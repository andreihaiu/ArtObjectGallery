package eu.andreihaiu.artobjects.artObjectsOverview

import android.util.Log
import androidx.paging.testing.asSnapshot
import eu.andreihaiu.testing.base.BaseViewModelTest
import eu.andreihaiu.testing.fakeData.FakeArtObjectsRepositoryImpl
import eu.andreihaiu.testing.fakeData.FakeArtObjectsUseCase
import io.mockk.every
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtObjectsOverviewViewModelTest : BaseViewModelTest() {

    @Before
    override fun setup() {
        super.setup()
        mockkStatic(Log::class)
    }

    @Test
    fun fetchArtObjectsList_whenDataIsAvailable_shouldPopulateArtObjects() = runTest {
        every { Log.isLoggable(any(), any()) } returns false

        val artObjectsRepository = FakeArtObjectsRepositoryImpl()
        val artObjectsUseCase = FakeArtObjectsUseCase(artObjectsRepository = artObjectsRepository)
        val viewModel = ArtObjectsOverviewViewModel(artObjectsUseCase = artObjectsUseCase)

        val items = viewModel.artObjectsList.asSnapshot()
        assertEquals(15, items.size)
        assertEquals("1", items.first().objectNumber)
        assertEquals("15", items.last().objectNumber)
    }

    @Test
    fun fetchArtObjectsList_whenNoDataIsAvailable_shouldNotPopulateArtObjects() = runTest {
        every { Log.isLoggable(any(), any()) } returns false

        val artObjectsRepository = FakeArtObjectsRepositoryImpl(items = emptyList())
        val artObjectsUseCase = FakeArtObjectsUseCase(artObjectsRepository = artObjectsRepository)
        val viewModel = ArtObjectsOverviewViewModel(artObjectsUseCase = artObjectsUseCase)

        val items = viewModel.artObjectsList.asSnapshot()
        assertEquals(0, items.size)
    }
}

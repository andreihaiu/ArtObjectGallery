package eu.andreihaiu.artobjects.artObjectDetails

import app.cash.turbine.test
import eu.andreihaiu.testing.base.BaseViewModelTest
import eu.andreihaiu.domain.entities.ArtObjectDetailsEntity
import eu.andreihaiu.domain.usecases.artObjectDetails.ArtObjectDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtObjectDetailsViewModelTest : BaseViewModelTest() {

    private val mockUseCase = mockk<ArtObjectDetailsUseCase>()

    private val objectId = "1"
    private val objectNumber = "245"

    @Test
    fun fetchArtObjectDetails_whenDataIsAvailable_shouldPopulateArtObjectDetails() = runTest {
        coEvery { mockUseCase.invoke(objectId) } returns flowOf(mockedData())

        val viewModel = ArtObjectDetailsViewModel(mockUseCase)
        viewModel.fetchArtObjectDetails(objectId)

        viewModel.artObjectDetails.test {
            val details = awaitItem()
            assertEquals(objectNumber, details?.objectNumber)
        }
    }

    @Test
    fun fetchArtObjectDetails_whenDataIsNotAvailable_shouldNotPopulateArtObjectDetails() = runTest {
        coEvery { mockUseCase.invoke(objectId) } returns flowOf(null)

        val viewModel = ArtObjectDetailsViewModel(mockUseCase)
        viewModel.fetchArtObjectDetails(objectId)

        viewModel.artObjectDetails.test {
            val details = awaitItem()
            assertEquals(null, details)
        }
    }

    private fun mockedData() = ArtObjectDetailsEntity(
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
        objectNumber = objectNumber,
        artObjectTypes = listOf(),
        physicalMedium = null,
        plaqueDescriptionEnglish = null,
        principalOrFirstMaker = null,
        productionPlaces = listOf(),
        scLabelLine = null,
        subTitle = null,
        title = null,
        imageUrl = null
    )
}

package eu.andreihaiu.artobjects.artObjectDetails

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import eu.andreihaiu.artobjects.util.LoadingUtilsTestTags
import eu.andreihaiu.common.theme.AppTheme
import eu.andreihaiu.common.ui.TopAppBarTestTags
import eu.andreihaiu.common.utils.HiltTestActivity
import eu.andreihaiu.common.utils.formatDate
import eu.andreihaiu.domain.entities.ArtObjectDetailsEntity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ArtObjectDetailsScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    private lateinit var navController: TestNavHostController

    private val objectId = "1"
    private val objectTitle = "Title"
    private val objectAuthor = "Author"
    private val objectDescription = "Description"
    private val objectPlaqueDescription = "Plaque description"
    private val objectValidAcquisitionDate = "1963-01-01T00:00:00"
    private val objectInvalidAcquisitionDate = "1963-01-01"
    private val imageUrl =
        "https://lh3.googleusercontent.com/i8S7we9zDW-AqUbZvE91O4bqsgVI_MamJhChLz6lE55MIn_kAjrYNoudrM-YEKjZTLUPyXVBgi__m6n1MMWmcbsHMy8=s0"

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun topAppBar_isVisible() {
        setArtObjectDetailsScreenContent()

        composeTestRule.onNodeWithTag(TopAppBarTestTags.CONTAINER)
            .assertIsDisplayed()
    }

    @Test
    fun pageLoader_whenLoading_isVisible() {
        setArtObjectDetailsScreenContent()

        composeTestRule.onNodeWithTag(LoadingUtilsTestTags.PAGE_LOADER)
            .assertIsDisplayed()
    }

    @Test
    fun objectImage_whenNotPresent_isNotVisible() {
        setArtObjectDetailsContent(mockItemDetails())

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_IMAGE)
            .assertIsNotDisplayed()
    }

    @Test
    fun objectImage_whenPresent_isVisible() {
        setArtObjectDetailsContent(mockItemDetails(imageUrl = imageUrl))

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_IMAGE)
            .assertIsDisplayed()
    }

    @Test
    fun objectTitle_whenNotPresent_showUnnamedWork() {
        setArtObjectDetailsContent(mockItemDetails())

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_TITLE)
            .assertIsDisplayed()
            .assertTextEquals("Unnamed work")
    }

    @Test
    fun objectTitle_whenPresent_showObjectTitle() {
        setArtObjectDetailsContent(mockItemDetails(longTitle = objectTitle))

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_TITLE)
            .assertIsDisplayed()
            .assertTextEquals(objectTitle)
    }

    @Test
    fun objectAuthor_whenNotPresent_showUnknown() {
        setArtObjectDetailsContent(mockItemDetails())

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_AUTHOR)
            .assertIsDisplayed()
            .assertTextEquals("Unknown")
    }

    @Test
    fun objectAuthor_whenPresent_showObjectAuthor() {
        setArtObjectDetailsContent(mockItemDetails(author = objectAuthor))

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_AUTHOR)
            .assertIsDisplayed()
            .assertTextEquals(objectAuthor)
    }

    @Test
    fun objectDescription_whenNotPresent_isNotVisible() {
        setArtObjectDetailsContent(mockItemDetails())

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_DESCRIPTION)
            .assertIsNotDisplayed()
    }

    @Test
    fun objectDescription_whenPresent_showObjectDescription() {
        setArtObjectDetailsContent(mockItemDetails(description = objectDescription))

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_DESCRIPTION)
            .assertIsDisplayed()
            .assertTextEquals(objectDescription)
    }

    @Test
    fun objectPlaqueDescription_whenNotPresent_isNotVisible() {
        setArtObjectDetailsContent(mockItemDetails())

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_PLAQUE_DESCRIPTION)
            .assertIsNotDisplayed()
    }

    @Test
    fun objectPlaqueDescription_whenPresent_showObjectPlaqueDescription() {
        setArtObjectDetailsContent(mockItemDetails(plaqueDescription = objectPlaqueDescription))

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_PLAQUE_DESCRIPTION)
            .assertIsDisplayed()
            .assertTextEquals(objectPlaqueDescription)
    }

    @Test
    fun objectAcquisitionDate_whenNotPresent_isNotVisible() {
        setArtObjectDetailsContent(mockItemDetails())

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_ACQUISITION_DATE)
            .assertIsNotDisplayed()
    }

    @Test
    fun objectAcquisitionDate_whenInvalid_isNotVisible() {
        setArtObjectDetailsContent(mockItemDetails(acquisitionDate = objectInvalidAcquisitionDate))

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_ACQUISITION_DATE)
            .assertIsNotDisplayed()
    }

    @Test
    fun objectAcquisitionDate_whenValid_showObjectAcquisitionDate() {
        setArtObjectDetailsContent(mockItemDetails(acquisitionDate = objectValidAcquisitionDate))

        composeTestRule.onNodeWithTag(ArtObjectDetailsTestTags.OBJECT_ACQUISITION_DATE)
            .assertIsDisplayed()
            .assertTextEquals(objectValidAcquisitionDate.formatDate()!!)
    }

    private fun setArtObjectDetailsScreenContent() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppTheme {
                ArtObjectDetailsScreen(
                    navController = navController,
                    objectId = objectId,
                    objectTitle = objectTitle
                )
            }
        }
    }

    private fun setArtObjectDetailsContent(itemDetails: ArtObjectDetailsEntity?) {
        composeTestRule.setContent {
            AppTheme {
                ArtObjectDetails(modifier = Modifier, itemDetails = itemDetails)
            }
        }
    }

    private fun mockItemDetails(
        title: String? = null,
        longTitle: String? = null,
        description: String? = null,
        plaqueDescription: String? = null,
        author: String? = null,
        imageUrl: String? = null,
        acquisitionDate: String? = null
    ) = ArtObjectDetailsEntity(
        acquisitionMethod = null,
        acquisitionDate = acquisitionDate,
        presentingDate = null,
        description = description,
        height = null,
        width = null,
        depth = null,
        documentation = listOf(),
        labelText = null,
        location = null,
        longTitle = longTitle,
        materials = listOf(),
        objectCollection = listOf(),
        objectNumber = objectId,
        artObjectTypes = listOf(),
        physicalMedium = null,
        plaqueDescriptionEnglish = plaqueDescription,
        principalOrFirstMaker = author,
        productionPlaces = listOf(),
        scLabelLine = null,
        subTitle = null,
        title = title,
        imageUrl = imageUrl
    )
}

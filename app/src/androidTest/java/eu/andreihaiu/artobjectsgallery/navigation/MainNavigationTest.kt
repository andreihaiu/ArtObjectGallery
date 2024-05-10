package eu.andreihaiu.artobjectsgallery.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import eu.andreihaiu.artobjects.artObjectsOverview.ArtObjectsOverviewTestTags
import eu.andreihaiu.artobjectsgallery.ui.navigation.MainNavigation
import eu.andreihaiu.artobjectsgallery.ui.navigation.Route
import eu.andreihaiu.common.theme.AppTheme
import eu.andreihaiu.common.utils.HiltTestActivity
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@OptIn(ExperimentalTestApi::class)
class MainNavigationTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun init() {
        hiltRule.inject()
        setMainNavigationContent()
    }

    @Test
    fun verify_startDestination() {
        composeTestRule.onNodeWithTag(ArtObjectsOverviewTestTags.CONTAINER).assertIsDisplayed()
        navController.assertCurrentRouteName(Route.ArtObjectsOverview.route)
    }

    @Test
    fun verify_navigationCorrectness_fromArtObjectsOverview_toArtObjectDetails() {
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(ArtObjectsOverviewTestTags.ART_OBJECT_ITEM))

        composeTestRule.onAllNodesWithTag(ArtObjectsOverviewTestTags.ART_OBJECT_ITEM)
            .onFirst()
            .performClick()

        assertEquals(
            true,
            navController.currentBackStackEntry?.destination?.route?.contains("artObjectDetails")
        )
    }

    private fun setMainNavigationContent() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppTheme {
                MainNavigation(navController = navController)
            }
        }
    }

    private fun NavController.assertCurrentRouteName(expectedRouteName: String) {
        assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
    }
}
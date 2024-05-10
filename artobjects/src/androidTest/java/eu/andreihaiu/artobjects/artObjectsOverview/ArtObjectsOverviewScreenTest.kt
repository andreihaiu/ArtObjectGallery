package eu.andreihaiu.artobjects.artObjectsOverview

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import eu.andreihaiu.common.theme.AppTheme
import eu.andreihaiu.common.ui.TopAppBarTestTags
import eu.andreihaiu.common.utils.HiltTestActivity
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@OptIn(ExperimentalTestApi::class)
class ArtObjectsOverviewScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    private var onClickInvoked: Boolean = false

    @Before
    fun init() {
        hiltRule.inject()
        setArtObjectsOverviewScreenContent()
        onClickInvoked = false
    }

    @Test
    fun topAppBar_isVisible() {
        composeTestRule.onNodeWithTag(TopAppBarTestTags.CONTAINER).assertIsDisplayed()
    }

    @Test
    fun artObjectItem_whenDataIsAvailable_showObjectItems() {
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(ArtObjectsOverviewTestTags.ART_OBJECT_ITEM))

        composeTestRule.onAllNodesWithTag(ArtObjectsOverviewTestTags.ART_OBJECT_ITEM)
            .assertCountEquals(4)
    }

    @Test
    fun artObjectItem_whenClicked_navigateToObjectDetails() {
        composeTestRule.waitUntilAtLeastOneExists(hasTestTag(ArtObjectsOverviewTestTags.ART_OBJECT_ITEM))

        composeTestRule.onAllNodesWithTag(ArtObjectsOverviewTestTags.ART_OBJECT_ITEM)
            .onFirst()
            .performClick()

        assertEquals(true, onClickInvoked)
    }

    private fun setArtObjectsOverviewScreenContent() {
        composeTestRule.setContent {
            AppTheme {
                ArtObjectsOverviewScreen(onItemClick = { onClickInvoked = true })
            }
        }
    }
}
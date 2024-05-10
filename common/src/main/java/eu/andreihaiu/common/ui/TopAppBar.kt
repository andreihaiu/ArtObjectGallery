package eu.andreihaiu.common.ui

import androidx.annotation.VisibleForTesting
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import eu.andreihaiu.common.ui.TopAppBarTestTags.BACK_BUTTON
import eu.andreihaiu.common.ui.TopAppBarTestTags.CONTAINER

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
object TopAppBarTestTags {
    const val CONTAINER = "topAppBarContainer"
    const val BACK_BUTTON = "topAppBarBackButton"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String, backIconEnabled: Boolean = false, onBackPressed: () -> Unit = {}) {
    CenterAlignedTopAppBar(
        modifier = Modifier.testTag(CONTAINER),
        title = { Text(text = title, style = MaterialTheme.typography.titleMedium) },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            if (backIconEnabled) {
                IconButton(modifier = Modifier.testTag(BACK_BUTTON), onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}
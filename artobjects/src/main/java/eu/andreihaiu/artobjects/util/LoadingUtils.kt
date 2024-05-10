package eu.andreihaiu.artobjects.util

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import eu.andreihaiu.artobjects.R
import eu.andreihaiu.artobjects.util.LoadingUtilsTestTags.NEXT_PAGE_LOADER
import eu.andreihaiu.artobjects.util.LoadingUtilsTestTags.PAGE_LOADER

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
object LoadingUtilsTestTags {
    const val PAGE_LOADER = "pageLoader"
    const val NEXT_PAGE_LOADER = "nextPageLoader"
}

@Composable
fun PageLoader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.testTag(PAGE_LOADER),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.server_fetch),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        CircularProgressIndicator(Modifier.padding(top = 10.dp))
    }
}

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .testTag(NEXT_PAGE_LOADER)
    )
}

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
            maxLines = 2
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}
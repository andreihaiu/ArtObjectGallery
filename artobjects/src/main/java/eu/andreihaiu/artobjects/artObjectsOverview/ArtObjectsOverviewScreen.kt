package eu.andreihaiu.artobjects.artObjectsOverview

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import eu.andreihaiu.artobjects.R
import eu.andreihaiu.artobjects.artObjectsOverview.ArtObjectsOverviewTestTags.ART_OBJECT_ITEM
import eu.andreihaiu.artobjects.artObjectsOverview.ArtObjectsOverviewTestTags.CONTAINER
import eu.andreihaiu.artobjects.artObjectsOverview.ArtObjectsOverviewTestTags.ITEMS_CONTAINER
import eu.andreihaiu.artobjects.util.ErrorMessage
import eu.andreihaiu.artobjects.util.LoadingNextPageItem
import eu.andreihaiu.artobjects.util.PageLoader
import eu.andreihaiu.common.ui.TopAppBar
import eu.andreihaiu.domain.entities.ArtObjectEntity

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
object ArtObjectsOverviewTestTags {
    const val CONTAINER = "artObjectOverviewContainer"
    const val ITEMS_CONTAINER = "artObjectOverviewItemsContainer"
    const val ART_OBJECT_ITEM = "artObjectItem"
}

@Composable
fun ArtObjectsOverviewScreen(onItemClick: (ArtObjectEntity) -> Unit) {
    val viewModel: ArtObjectsOverviewViewModel = hiltViewModel()
    val artObjects: LazyPagingItems<ArtObjectEntity> =
        viewModel.artObjectsList.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.testTag(CONTAINER),
        topBar = { TopAppBar(title = stringResource(id = R.string.art_objects)) }
    ) {
        ArtObjectsOverview(
            modifier = Modifier.padding(it),
            artObjects = artObjects,
            onItemClick = onItemClick
        )
    }
}

@Composable
fun ArtObjectsOverview(
    modifier: Modifier,
    artObjects: LazyPagingItems<ArtObjectEntity>,
    onItemClick: (ArtObjectEntity) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .then(modifier)
            .testTag(ITEMS_CONTAINER)
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        items(artObjects.itemCount) { index ->
            artObjects[index]?.let { item ->
                ArtObjectItem(
                    modifier = Modifier.testTag(ART_OBJECT_ITEM),
                    item = item,
                    onClick = {
                        onItemClick(item)
                    }
                )
            }
        }
        LoadingContent(items = artObjects)
        item { Spacer(modifier = Modifier.padding(4.dp)) }
    }
}

fun LazyListScope.LoadingContent(items: LazyPagingItems<ArtObjectEntity>) {
    items.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
            }

            loadState.refresh is LoadState.Error -> {
                val error = items.loadState.refresh as LoadState.Error
                item {
                    ErrorMessage(
                        modifier = Modifier.fillParentMaxSize(),
                        message = error.error.localizedMessage!!,
                        onClickRetry = { retry() })
                }
            }

            loadState.append is LoadState.Loading -> {
                item { LoadingNextPageItem(modifier = Modifier) }
            }

            loadState.append is LoadState.Error -> {
                val error = items.loadState.append as LoadState.Error
                item {
                    ErrorMessage(
                        modifier = Modifier,
                        message = error.error.localizedMessage!!,
                        onClickRetry = { retry() })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtObjectsOverviewScreen(onItemClick = {})
}
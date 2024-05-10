package eu.andreihaiu.artobjects.artObjectDetails

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.VisibleForTesting
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import eu.andreihaiu.artobjects.R
import eu.andreihaiu.artobjects.artObjectDetails.ArtObjectDetailsTestTags.OBJECT_ACQUISITION_DATE
import eu.andreihaiu.artobjects.artObjectDetails.ArtObjectDetailsTestTags.OBJECT_AUTHOR
import eu.andreihaiu.artobjects.artObjectDetails.ArtObjectDetailsTestTags.OBJECT_DESCRIPTION
import eu.andreihaiu.artobjects.artObjectDetails.ArtObjectDetailsTestTags.OBJECT_IMAGE
import eu.andreihaiu.artobjects.artObjectDetails.ArtObjectDetailsTestTags.OBJECT_PLAQUE_DESCRIPTION
import eu.andreihaiu.artobjects.artObjectDetails.ArtObjectDetailsTestTags.OBJECT_TITLE
import eu.andreihaiu.artobjects.util.PageLoader
import eu.andreihaiu.common.ui.TopAppBar
import eu.andreihaiu.common.utils.collectAsStateLifecycleAware
import eu.andreihaiu.common.utils.formatDate
import eu.andreihaiu.domain.entities.ArtObjectDetailsEntity

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
object ArtObjectDetailsTestTags {
    const val CONTAINER = "artObjectDetailsContainer"
    const val OBJECT_IMAGE = "objectImage"
    const val OBJECT_TITLE = "objectTitle"
    const val OBJECT_AUTHOR = "objectAuthor"
    const val OBJECT_DESCRIPTION = "objectDescription"
    const val OBJECT_PLAQUE_DESCRIPTION = "objectPlaqueDescription"
    const val OBJECT_ACQUISITION_DATE = "objectAcquisitionDate"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArtObjectDetailsScreen(navController: NavController, objectId: String?, objectTitle: String?) {
    requireNotNull(objectId)

    val viewModel: ArtObjectDetailsViewModel = hiltViewModel()
    val itemDetails by viewModel.artObjectDetails.collectAsStateLifecycleAware()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchArtObjectDetails(objectId = objectId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = objectTitle ?: stringResource(id = R.string.art_objects),
                backIconEnabled = true,
                onBackPressed = { navController.navigateUp() }
            )
        }
    ) {
        if (itemDetails == null) {
            PageLoader(modifier = Modifier.fillMaxSize())
        } else {
            ArtObjectDetails(modifier = Modifier.padding(it), itemDetails = itemDetails)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArtObjectDetails(modifier: Modifier, itemDetails: ArtObjectDetailsEntity?) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .verticalScroll(state = scrollState)
            .padding(bottom = 16.dp)
            .then(modifier),
    ) {
        ObjectImage(itemDetails = itemDetails)
        ObjectTitle(itemDetails = itemDetails)
        ObjectAuthor(itemDetails = itemDetails)
        ObjectDescription(itemDetails = itemDetails)
        ObjectPlaqueDescription(itemDetails = itemDetails)
        ObjectAcquisitionDate(itemDetails = itemDetails)
    }
}

@Composable
fun ObjectImage(itemDetails: ArtObjectDetailsEntity?) {
    itemDetails?.imageUrl?.let { imageUrl ->
        val painter = rememberAsyncImagePainter(imageUrl)
        val transition by animateFloatAsState(
            targetValue = if (painter.state is AsyncImagePainter.State.Success) 1f else 0f,
            label = ""
        )
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 300.dp)
                .alpha(transition)
                .testTag(OBJECT_IMAGE)
        )
    }
}

@Composable
fun ObjectTitle(itemDetails: ArtObjectDetailsEntity?) {
    Spacer(modifier = Modifier.padding(vertical = 4.dp))
    Text(
        text = itemDetails?.longTitle ?: stringResource(id = R.string.unnamed_work),
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .testTag(OBJECT_TITLE)
    )
}

@Composable
fun ObjectAuthor(itemDetails: ArtObjectDetailsEntity?) {
    Spacer(modifier = Modifier.padding(vertical = 4.dp))
    Text(
        text = stringResource(id = R.string.author),
        modifier = Modifier.padding(horizontal = 8.dp),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.padding(vertical = 4.dp))
    Text(
        text = itemDetails?.principalOrFirstMaker ?: stringResource(id = R.string.unknown),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .testTag(OBJECT_AUTHOR),
        color = MaterialTheme.colorScheme.onSurface,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun ObjectDescription(itemDetails: ArtObjectDetailsEntity?) {
    itemDetails?.description?.let { description ->
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            text = stringResource(id = R.string.description),
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            text = description,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .testTag(OBJECT_DESCRIPTION),
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun ObjectPlaqueDescription(itemDetails: ArtObjectDetailsEntity?) {
    itemDetails?.plaqueDescriptionEnglish?.let { plaqueDescription ->
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            text = stringResource(id = R.string.plaque_description),
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            text = plaqueDescription,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .testTag(OBJECT_PLAQUE_DESCRIPTION),
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ObjectAcquisitionDate(itemDetails: ArtObjectDetailsEntity?) {
    itemDetails?.acquisitionDate?.formatDate()?.let { acquisitionDate ->
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            text = stringResource(id = R.string.acquisition_date),
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        Text(
            text = acquisitionDate,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .testTag(OBJECT_ACQUISITION_DATE),
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ArtObjectDetailsPreview() {
    ArtObjectDetails(
        modifier = Modifier.background(Color.White),
        itemDetails = ArtObjectDetailsEntity(
            acquisitionMethod = null,
            acquisitionDate = "1963-01-01T00:00:00",
            presentingDate = null,
            description = "Description",
            height = null,
            width = null,
            depth = null,
            documentation = listOf(),
            labelText = null,
            location = null,
            longTitle = "Long Title",
            materials = listOf(),
            objectCollection = listOf(),
            objectNumber = null,
            artObjectTypes = listOf(),
            physicalMedium = null,
            plaqueDescriptionEnglish = "Plaque description",
            principalOrFirstMaker = null,
            productionPlaces = listOf(),
            scLabelLine = null,
            subTitle = null,
            title = "Title",
            imageUrl = null
        )
    )
}
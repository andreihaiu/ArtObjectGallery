package eu.andreihaiu.artobjects.artObjectsOverview

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import eu.andreihaiu.domain.entities.ArtObjectEntity

@Composable
fun ArtObjectItem(
    modifier: Modifier,
    item: ArtObjectEntity?,
    onClick: () -> Unit
) {
    item?.let {
        Card(
            modifier = Modifier
                .then(modifier)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { onClick() },
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            val painter = rememberAsyncImagePainter(item.headerImageUrl)
            val transition by animateFloatAsState(
                targetValue = if (painter.state is AsyncImagePainter.State.Success) 1f else 0f,
                label = ""
            )
            Column {
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                            .alpha(transition)
                    )
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .align(Alignment.BottomEnd),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            tint = Color.Yellow
                        )
                        Text(
                            text = item.objectNumber ?: "",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Yellow,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                }
                Text(
                    text = item.title ?: "",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))
                Text(
                    text = "Author: ${item.principalOrFirstMaker}",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtObjectItemPreview() {
    ArtObjectItem(
        modifier = Modifier,
        item = ArtObjectEntity(
            longTitle = "longTitle",
            objectNumber = "objectNumber",
            principalOrFirstMaker = "principalOrFirstMaker",
            imageUrl = "imageUrl",
            headerImageUrl = "headerImageUrl",
            productionPlaces = listOf("productionPlaces"),
            title = "title"
        )
    ) {}
}
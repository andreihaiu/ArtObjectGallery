package eu.andreihaiu.data.mappers

import eu.andreihaiu.data.models.ArtObject
import eu.andreihaiu.domain.entities.ArtObjectEntity

fun ArtObject.mapToDomain(): ArtObjectEntity = ArtObjectEntity(
    longTitle = longTitle,
    objectNumber = objectNumber,
    principalOrFirstMaker = principalOrFirstMaker,
    imageUrl = webImage?.url,
    headerImageUrl = headerImage?.url,
    productionPlaces = productionPlaces?.mapNotNull { it } ?: emptyList(),
    title = title
)
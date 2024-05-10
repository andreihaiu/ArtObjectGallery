package eu.andreihaiu.data.mappers

import eu.andreihaiu.data.models.ArtObjectDetails
import eu.andreihaiu.domain.entities.ArtObjectDetailsEntity

fun ArtObjectDetails.mapToDomain(): ArtObjectDetailsEntity = ArtObjectDetailsEntity(
    longTitle = longTitle,
    objectNumber = objectNumber,
    principalOrFirstMaker = principalOrFirstMaker,
    imageUrl = webImage?.url,
    productionPlaces = productionPlaces?.map { it },
    title = title,
    acquisitionMethod = acquisition?.method,
    acquisitionDate = acquisition?.date,
    documentation = documentation,
    presentingDate = dating?.presentingDate,
    description = description,
    height = dimensions?.firstOrNull { it.type == "height" }?.value,
    width = dimensions?.firstOrNull { it.type == "width" }?.value,
    depth = dimensions?.firstOrNull { it.type == "depth" }?.value,
    labelText = labelText,
    location = location,
    materials = materials,
    objectCollection = objectCollection,
    artObjectTypes = artObjectTypes,
    physicalMedium = physicalMedium,
    plaqueDescriptionEnglish = plaqueDescriptionEnglish,
    scLabelLine = scLabelLine,
    subTitle = subTitle,
)
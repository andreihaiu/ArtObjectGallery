package eu.andreihaiu.domain.entities

data class ArtObjectDetailsEntity(
    val acquisitionMethod: String?,
    val acquisitionDate: String?,
    val presentingDate: String?,
    val description: String?,
    val height: String?,
    val width: String?,
    val depth: String?,
    val documentation: List<String>?,
    val labelText: String?,
    val location: String?,
    val longTitle: String?,
    val materials: List<String>?,
    val objectCollection: List<String>?,
    val objectNumber: String?,
    val artObjectTypes: List<String>?,
    val physicalMedium: String?,
    val plaqueDescriptionEnglish: String?,
    val principalOrFirstMaker: String?,
    val productionPlaces: List<String>?,
    val scLabelLine: String?,
    val subTitle: String?,
    val title: String?,
    val imageUrl: String?
)
package eu.andreihaiu.domain.entities

data class ArtObjectEntity(
    val longTitle: String?,
    val objectNumber: String?,
    val principalOrFirstMaker: String?,
    val imageUrl: String?,
    val headerImageUrl: String?,
    val productionPlaces: List<String> = emptyList(),
    val title: String? = "",
)
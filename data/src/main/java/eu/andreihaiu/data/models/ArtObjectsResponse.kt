package eu.andreihaiu.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtObjectsResponse(
    @Json(name = "artObjects")
    val artObjects: List<ArtObject>?,
    @Json(name = "count")
    val count: Int?,
    @Json(name = "countFacets")
    val countFacets: CountFacets?,
    @Json(name = "elapsedMilliseconds")
    val elapsedMilliseconds: Int?,
    @Json(name = "facets")
    val facets: List<Facet>?
)

@JsonClass(generateAdapter = true)
data class ArtObject(
    @Json(name = "hasImage")
    val hasImage: Boolean?,
    @Json(name = "headerImage")
    val headerImage: HeaderImage?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "links")
    val links: Links?,
    @Json(name = "longTitle")
    val longTitle: String?,
    @Json(name = "objectNumber")
    val objectNumber: String?,
    @Json(name = "permitDownload")
    val permitDownload: Boolean?,
    @Json(name = "principalOrFirstMaker")
    val principalOrFirstMaker: String?,
    @Json(name = "productionPlaces")
    val productionPlaces: List<String?>?,
    @Json(name = "showImage")
    val showImage: Boolean?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "webImage")
    val webImage: WebImage?
)

@JsonClass(generateAdapter = true)
data class CountFacets(
    @Json(name = "hasimage")
    val hasImage: Int?,
    @Json(name = "ondisplay")
    val onDisplay: Int?
)

@JsonClass(generateAdapter = true)
data class Facet(
    @Json(name = "facets")
    val facets: List<FacetPair>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "otherTerms")
    val otherTerms: Int?,
    @Json(name = "prettyName")
    val prettyName: Int?
)

@JsonClass(generateAdapter = true)
data class HeaderImage(
    @Json(name = "guid")
    val guid: String?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "offsetPercentageX")
    val offsetPercentageX: Int?,
    @Json(name = "offsetPercentageY")
    val offsetPercentageY: Int?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "width")
    val width: Int?
)

@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "self")
    val self: String?,
    @Json(name = "web")
    val web: String?
)

@JsonClass(generateAdapter = true)
data class WebImage(
    @Json(name = "guid")
    val guid: String?,
    @Json(name = "height")
    val height: Int?,
    @Json(name = "offsetPercentageX")
    val offsetPercentageX: Int?,
    @Json(name = "offsetPercentageY")
    val offsetPercentageY: Int?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "width")
    val width: Int?
)

@JsonClass(generateAdapter = true)
data class FacetPair(
    @Json(name = "key")
    val key: String?,
    @Json(name = "value")
    val value: Int?
)
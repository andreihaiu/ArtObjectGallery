package eu.andreihaiu.data.models

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class ArtObjectDetailsResponse(
    @Json(name = "artObject")
    val artObject: ArtObjectDetails,
    @Json(name = "artObjectPage")
    val artObjectPage: ArtObjectPage,
    @Json(name = "elapsedMilliseconds")
    val elapsedMilliseconds: Int
)

@JsonClass(generateAdapter = true)
data class ArtObjectDetails(
    @Json(name = "acquisition")
    val acquisition: Acquisition?,
    @Json(name = "dating")
    val dating: Dating?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "dimensions")
    val dimensions: List<Dimension>?,
    @Json(name = "documentation")
    val documentation: List<String>?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "label")
    val label: Label?,
    @Json(name = "labelText")
    val labelText: String?,
    @Json(name = "language")
    val language: String?,
    @Json(name = "location")
    val location: String?,
    @Json(name = "longTitle")
    val longTitle: String?,
    @Json(name = "materials")
    val materials: List<String>?,
    @Json(name = "objectCollection")
    val objectCollection: List<String>?,
    @Json(name = "objectNumber")
    val objectNumber: String?,
    @Json(name = "objectTypes")
    val artObjectTypes: List<String>?,
    @Json(name = "physicalMedium")
    val physicalMedium: String?,
    @Json(name = "plaqueDescriptionDutch")
    val plaqueDescriptionDutch: String?,
    @Json(name = "plaqueDescriptionEnglish")
    val plaqueDescriptionEnglish: String?,
    @Json(name = "principalMaker")
    val principalMaker: String?,
    @Json(name = "principalMakers")
    val principalMakers: List<PrincipalMaker>?,
    @Json(name = "principalOrFirstMaker")
    val principalOrFirstMaker: String?,
    @Json(name = "productionPlaces")
    val productionPlaces: List<String>?,
    @Json(name = "scLabelLine")
    val scLabelLine: String?,
    @Json(name = "subTitle")
    val subTitle: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "titles")
    val titles: List<String>?,
    @Json(name = "webImage")
    val webImage: WebImage?
)

@JsonClass(generateAdapter = true)
data class ArtObjectPage(
    @Json(name = "createdOn")
    val createdOn: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "lang")
    val lang: String?,
    @Json(name = "objectNumber")
    val objectNumber: String?,
    @Json(name = "plaqueDescription")
    val plaqueDescription: String?,
    @Json(name = "updatedOn")
    val updatedOn: String?
)

@JsonClass(generateAdapter = true)
data class Acquisition(
    @Json(name = "creditLine")
    val creditLine: String?,
    @Json(name = "date")
    val date: String?,
    @Json(name = "method")
    val method: String?
)

@JsonClass(generateAdapter = true)
data class Dating(
    @Json(name = "period")
    val period: Int?,
    @Json(name = "presentingDate")
    val presentingDate: String?,
    @Json(name = "sortingDate")
    val sortingDate: Int?,
    @Json(name = "yearEarly")
    val yearEarly: Int?,
    @Json(name = "yearLate")
    val yearLate: Int?
)

@JsonClass(generateAdapter = true)
data class Dimension(
    @Json(name = "part")
    val part: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "unit")
    val unit: String?,
    @Json(name = "value")
    val value: String?
)

@JsonClass(generateAdapter = true)
data class Label(
    @Json(name = "date")
    val date: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "makerLine")
    val makerLine: String?,
    @Json(name = "notes")
    val notes: String?,
    @Json(name = "title")
    val title: String?
)

@JsonClass(generateAdapter = true)
data class PrincipalMaker(
    @Json(name = "biography")
    val biography: String?,
    @Json(name = "dateOfBirth")
    val dateOfBirth: String?,
    @Json(name = "dateOfBirthPrecision")
    val dateOfBirthPrecision: String?,
    @Json(name = "dateOfDeath")
    val dateOfDeath: String?,
    @Json(name = "dateOfDeathPrecision")
    val dateOfDeathPrecision: String?,
    @Json(name = "labelDesc")
    val labelDesc: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "nationality")
    val nationality: String?,
    @Json(name = "occupation")
    val occupation: List<String?>?,
    @Json(name = "placeOfBirth")
    val placeOfBirth: String?,
    @Json(name = "placeOfDeath")
    val placeOfDeath: String?,
    @Json(name = "productionPlaces")
    val productionPlaces: List<String?>?,
    @Json(name = "qualification")
    val qualification: String?,
    @Json(name = "roles")
    val roles: List<String?>?,
    @Json(name = "unFixedName")
    val unFixedName: String?
)

package eu.andreihaiu.artobjectsgallery.ui.navigation

import android.os.Bundle

sealed class Route(val route: String) {
    open fun createRoute(bundle: Bundle?): String = route

    data object MainNavigation : Route("mainNavigation")
    data object ArtObjectsOverview : Route("artObjectsOverview")
    data object ArtObjectDetails : Route("artObjectDetails/{$OBJECT_ID}/{$OBJECT_TITLE}") {
        override fun createRoute(bundle: Bundle?): String {
            val objectId = bundle?.getString(OBJECT_ID)
            val objectTitle = bundle?.getString(OBJECT_TITLE)
            requireNotNull(objectId)
            requireNotNull(objectTitle)
            return route.replace("{$OBJECT_ID}", objectId).replace("{$OBJECT_TITLE}", objectTitle)
        }
    }

    companion object {
        const val OBJECT_ID = "objectId"
        const val OBJECT_TITLE = "objectTitle"
    }
}
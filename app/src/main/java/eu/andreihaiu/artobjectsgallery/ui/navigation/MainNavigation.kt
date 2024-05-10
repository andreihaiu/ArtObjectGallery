package eu.andreihaiu.artobjectsgallery.ui.navigation

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import eu.andreihaiu.artobjects.artObjectDetails.ArtObjectDetailsScreen
import eu.andreihaiu.artobjects.artObjectsOverview.ArtObjectsOverviewScreen
import eu.andreihaiu.artobjectsgallery.ui.navigation.Route.Companion.OBJECT_ID
import eu.andreihaiu.artobjectsgallery.ui.navigation.Route.Companion.OBJECT_TITLE

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController, startDestination = Route.MainNavigation.route
    ) {
        navigation(
            startDestination = Route.ArtObjectsOverview.route, route = Route.MainNavigation.route
        ) {
            composable(route = Route.ArtObjectsOverview.route) {
                ArtObjectsOverviewScreen { item ->
                    val bundle = Bundle().apply {
                        putString(OBJECT_ID, item.objectNumber)
                        putString(OBJECT_TITLE, item.title)
                    }
                    navController.navigate(Route.ArtObjectDetails.createRoute(bundle))
                }
            }
            composable(route = Route.ArtObjectDetails.route,
                arguments = listOf(navArgument(OBJECT_ID) {
                    type = NavType.StringType
                })) {
                ArtObjectDetailsScreen(
                    navController,
                    it.arguments?.getString(OBJECT_ID),
                    it.arguments?.getString(OBJECT_TITLE)
                )
            }
        }
    }
}
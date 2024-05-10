package eu.andreihaiu.artobjectsgallery.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import eu.andreihaiu.artobjectsgallery.ui.navigation.MainNavigation
import eu.andreihaiu.common.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MainNavigation()
            }
        }
    }
}
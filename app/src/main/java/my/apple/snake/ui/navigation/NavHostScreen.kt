package my.apple.snake.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import my.apple.snake.ui.screens.game.GameScreen
import my.apple.snake.ui.screens.menu.MenuScreen
import my.apple.snake.ui.screens.settings.SettingsScreen
import my.apple.snake.ui.screens.settings.views.SettingsViewDisplay
import my.apple.snake.ui.screens.table.RatingTableScreen

@ExperimentalFoundationApi
@Composable
fun NavHostScreen(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = NavScreen.MainMenu.route,
        route = "root_graph"
    ) {
        composable(NavScreen.MainMenu.route) {
            MenuScreen(navController)
        }

        composable(NavScreen.RatingTable.route) {
            RatingTableScreen(navController)
        }

        composable(NavScreen.Settings.route) {
            SettingsScreen(navController)
        }

        composable(NavScreen.Game.route) {
            GameScreen(navController)
        }
    }
}

sealed class NavScreen(val route: String) {

    data object MainMenu : NavScreen("MainMenu")

    data object RatingTable : NavScreen("RatingTable")

    data object Settings : NavScreen("Settings")

    data object Game : NavScreen("Game")
}
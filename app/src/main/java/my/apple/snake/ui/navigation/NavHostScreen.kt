package my.apple.snake.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import my.apple.snake.ui.screens.game.GameScreen
import my.apple.snake.ui.screens.menu.MenuScreen
import my.apple.snake.ui.screens.rating.table.RatingTableScreen
import my.apple.snake.ui.screens.settings.SettingsScreen

@ExperimentalFoundationApi
@Composable
fun NavHostScreen(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = NavScreen.MainMenu
    ) {
        composable<NavScreen.MainMenu> {
            MenuScreen(navController)
        }

        composable<NavScreen.RatingTable> {
            RatingTableScreen(navController)
        }

        composable<NavScreen.Settings> {
            SettingsScreen(navController)
        }

        composable<NavScreen.Game> {
            GameScreen(navController)
        }
    }
}

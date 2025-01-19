package my.apple.snake.ui.screens.menu

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import my.apple.snake.ui.screens.menu.views.MenuViewDisplay

@Composable
fun MenuScreen(navController: NavController) {
    MenuViewDisplay {
        navController.navigate(it)
    }
}
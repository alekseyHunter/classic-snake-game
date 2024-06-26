package my.apple.snake.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.rememberNavController
import my.apple.snake.ui.navigation.NavHostScreen
import my.apple.snake.ui.screens.game.GameScreen
import my.apple.snake.ui.theme.AppleSnakeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppleSnakeTheme {
                NavHostScreen(navController)
            }
        }
    }
}

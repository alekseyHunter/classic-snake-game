package my.apple.snake.ui.screens.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import my.apple.snake.ui.screens.game.models.GameEvent
import my.apple.snake.ui.screens.game.models.GameViewState
import my.apple.snake.ui.screens.game.views.GameBoard
import my.apple.snake.ui.screens.game.views.GameViewDisplay

@Composable
fun GameScreen(navController: NavController) {
    val viewModel = remember { GameViewModel() }

    val viewState = viewModel.viewStates().collectAsState()

    when (val state = viewState.value) {
        is GameViewState.Loading -> {

        }

        is GameViewState.Display -> {
            GameViewDisplay()
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.obtainEvent(GameEvent.EnterScreen)
    }
}
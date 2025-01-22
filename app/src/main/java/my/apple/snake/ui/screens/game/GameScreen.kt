package my.apple.snake.ui.screens.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import my.apple.snake.data.local.datastore.DataStoreManager
import my.apple.snake.domain.repository.SnakeController
import my.apple.snake.ui.screens.game.models.GameAction
import my.apple.snake.ui.screens.game.models.GameEvent
import my.apple.snake.ui.screens.game.models.GameViewState
import my.apple.snake.ui.screens.game.views.GameViewDisplay

@Composable
fun GameScreen(navController: NavController) {
    val context = LocalContext.current

    val viewModel = remember {
        GameViewModel(
            DataStoreManager(context),
             SnakeController()
        )
    }

    val viewState = viewModel.viewStates().collectAsState()
    val viewAction = viewModel.viewActions().collectAsState(initial = null)

    when(val action = viewAction.value) {
        is GameAction.ShowDialog -> {

        }
        is GameAction.CloseScreen -> {
            navController.navigateUp()
        }
        else -> {

        }
    }

    when (val state = viewState.value) {
        is GameViewState.Loading -> {

        }

        is GameViewState.Display -> {
            GameViewDisplay(state) { event ->
                viewModel.obtainEvent(event)
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.obtainEvent(GameEvent.EnterScreen)
    }
}
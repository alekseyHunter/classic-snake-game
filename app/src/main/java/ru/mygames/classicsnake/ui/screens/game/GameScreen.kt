package ru.mygames.classicsnake.ui.screens.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.mygames.classicsnake.data.local.datastore.DataStoreManager
import ru.mygames.classicsnake.domain.repository.SnakeController
import ru.mygames.classicsnake.ui.screens.game.models.GameAction
import ru.mygames.classicsnake.ui.screens.game.models.GameEvent
import ru.mygames.classicsnake.ui.screens.game.models.GameViewState
import ru.mygames.classicsnake.ui.screens.game.views.GameViewDisplay

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
        is GameAction.CloseScreen -> {
            navController.navigateUp()
        }
        else -> {

        }
    }

    when(val state = viewState.value) {
        is GameViewState.Loading -> {

        }

        is GameViewState.Display -> {
            GameViewDisplay(state = state) {
                viewModel.obtainEvent(it)
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.obtainEvent(GameEvent.EnterScreen)
    }
}
package ru.mygames.classicsnake.ui.screens.rating.table

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.mygames.classicsnake.data.local.datastore.DataStoreManager
import ru.mygames.classicsnake.ui.screens.rating.table.models.RatingTableEvent
import ru.mygames.classicsnake.ui.screens.rating.table.models.RatingTableViewState
import ru.mygames.classicsnake.ui.screens.rating.table.views.RatingTableViewDisplay

@Composable
fun RatingTableScreen(navController: NavController) {
    val context = LocalContext.current

    val viewModel = remember {
        RatingTableViewModel(
            DataStoreManager((context))
        )
    }

    val viewState = viewModel.viewStates().collectAsState()

    when(val state = viewState.value) {
        is RatingTableViewState.Loading -> {

        }

        is RatingTableViewState.Display -> {
            RatingTableViewDisplay(state) { event ->
                when(event) {
                    is RatingTableEvent.CloseScreen -> navController.navigateUp()
                    else -> viewModel.obtainEvent(event)
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.obtainEvent(RatingTableEvent.EnterScreen)
    }
}
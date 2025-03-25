package ru.mygames.classicsnake.ui.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import ru.mygames.classicsnake.data.local.datastore.DataStoreManager
import ru.mygames.classicsnake.ui.screens.settings.models.SettingsEvent
import ru.mygames.classicsnake.ui.screens.settings.models.SettingsViewState
import ru.mygames.classicsnake.ui.screens.settings.views.SettingsViewDisplay

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current

    val viewModel = remember {
        SettingsViewModel(DataStoreManager(context))
    }

    val viewState = viewModel.viewStates().collectAsState()

    when(val state = viewState.value) {
        is SettingsViewState.Loading -> {

        }

        is SettingsViewState.Display -> {
            SettingsViewDisplay(state = state) { event ->
                when(event) {
                    is SettingsEvent.CloseScreen -> navController.navigateUp()
                    else -> viewModel.obtainEvent(event)
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.obtainEvent(SettingsEvent.EnterScreen)
    }
}
package ru.mygames.classicsnake.ui.screens.menu

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.mygames.classicsnake.ui.screens.menu.views.MainMenuViewDisplay

@Composable
fun MainMenuScreen(navController: NavController) {
    MainMenuViewDisplay() {
        navController.navigate(it)
    }
}
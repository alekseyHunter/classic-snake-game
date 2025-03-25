package ru.mygames.classicsnake.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavScreen {

    @Serializable
    data object MainMenu: NavScreen

    @Serializable
    data object RatingTable: NavScreen

    @Serializable
    data object Settings: NavScreen

    @Serializable
    data object Game: NavScreen
}
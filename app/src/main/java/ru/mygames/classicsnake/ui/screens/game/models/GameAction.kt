package ru.mygames.classicsnake.ui.screens.game.models

import ru.mygames.classicsnake.ui.base.architecture.UiAction

sealed interface GameAction: UiAction {
    data object CloseScreen: GameAction
}
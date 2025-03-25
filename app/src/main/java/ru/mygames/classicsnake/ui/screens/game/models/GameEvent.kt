package ru.mygames.classicsnake.ui.screens.game.models

import ru.mygames.classicsnake.domain.models.Direction
import ru.mygames.classicsnake.ui.base.architecture.UiEvent

sealed interface GameEvent: UiEvent {
    data object EnterScreen: GameEvent

    data class ChangeSnakeDirection(val newValue: Direction): GameEvent

    data object CloseScreen: GameEvent
}
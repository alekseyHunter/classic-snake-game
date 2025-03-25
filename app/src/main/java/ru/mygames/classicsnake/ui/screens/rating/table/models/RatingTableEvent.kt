package ru.mygames.classicsnake.ui.screens.rating.table.models

import ru.mygames.classicsnake.ui.base.architecture.UiEvent

sealed interface RatingTableEvent: UiEvent {
    data object EnterScreen: RatingTableEvent

    data class ChangeDifficultyLevel(val number: Int): RatingTableEvent

    data object CloseScreen: RatingTableEvent
}
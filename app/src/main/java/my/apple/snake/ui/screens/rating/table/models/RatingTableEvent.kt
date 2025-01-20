package my.apple.snake.ui.screens.rating.table.models

import my.apple.snake.base.archirecture.UiEvent

sealed class RatingTableEvent : UiEvent {
    data object EnterScreen : RatingTableEvent()
    data class ChangeDifficultyLevel(val number: Int) : RatingTableEvent()

    data object CloseScreen: RatingTableEvent()
}
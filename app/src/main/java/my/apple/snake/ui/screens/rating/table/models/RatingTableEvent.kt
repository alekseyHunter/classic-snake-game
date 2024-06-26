package my.apple.snake.ui.screens.table.models

import androidx.compose.ui.unit.IntSize
import my.apple.snake.domain.models.Direction
import my.apple.snake.base.archirecture.UiEvent

sealed class RatingTableEvent : UiEvent {
    data object EnterScreen : RatingTableEvent()
    data class ChangeSnakeDirection(val direction: Direction) : RatingTableEvent()
    data class ChangeBoardSize(val size: IntSize): RatingTableEvent()
}
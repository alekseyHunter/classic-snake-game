package my.apple.snake.ui.screens.game.models

import androidx.compose.ui.unit.IntSize
import my.apple.snake.domain.models.Direction
import my.apple.snake.base.archirecture.UiEvent

sealed class GameEvent : UiEvent {
    data object EnterScreen : GameEvent()
    data class ChangeSnakeDirection(val direction: Direction) : GameEvent()
    data class ChangeBoardSize(val size: IntSize): GameEvent()
}
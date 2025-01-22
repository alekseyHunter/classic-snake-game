package my.apple.snake.ui.screens.game.models

import my.apple.snake.base.archirecture.UiEvent
import my.apple.snake.domain.models.Direction

sealed class GameEvent : UiEvent {
    data object EnterScreen : GameEvent()
    data class ChangeSnakeDirection(val direction: Direction) : GameEvent()
}
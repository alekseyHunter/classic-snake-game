package my.apple.snake.ui.screens.game.models

import my.apple.snake.base.archirecture.UiAction

sealed class GameAction : UiAction {
    data class ShowDialog(val title: String, val body: String)
}

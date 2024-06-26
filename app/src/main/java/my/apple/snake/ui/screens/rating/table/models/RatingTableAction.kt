package my.apple.snake.ui.screens.table.models

import my.apple.snake.base.archirecture.UiAction

sealed class RatingTableAction : UiAction {
    data class ShowDialog(val title: String, val body: String)
}

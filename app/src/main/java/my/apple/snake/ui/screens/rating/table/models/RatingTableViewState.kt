package my.apple.snake.ui.screens.table.models

import my.apple.snake.base.archirecture.UiState

sealed class RatingTableViewState : UiState {
    data object Loading : RatingTableViewState()

    data class Display(
        val table: String
    ) : RatingTableViewState()
}

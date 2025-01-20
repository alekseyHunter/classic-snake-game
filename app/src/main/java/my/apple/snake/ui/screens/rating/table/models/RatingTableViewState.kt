package my.apple.snake.ui.screens.rating.table.models

import my.apple.snake.base.archirecture.UiState
import my.apple.snake.data.local.datastore.GameLevel
import my.apple.snake.data.local.datastore.UserGameResult

sealed class RatingTableViewState : UiState {
    data object Loading : RatingTableViewState()

    data class Display(
        val gameLevel: GameLevel,
        val gameResults: List<UserGameResult>
    ) : RatingTableViewState()
}

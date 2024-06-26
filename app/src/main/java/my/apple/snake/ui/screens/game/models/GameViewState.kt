package my.apple.snake.ui.screens.game.models

import my.apple.snake.domain.models.BonusItem
import my.apple.snake.domain.models.GameStatus
import my.apple.snake.domain.models.Snake
import my.apple.snake.base.archirecture.UiState
import my.apple.snake.domain.models.GameBoardSettings

sealed class GameViewState : UiState {
    data object Loading : GameViewState()

    data class Display(
        val snake: Snake,
        val bonusItems: List<BonusItem>,
        val gameStatus: GameStatus,
        val boardSettings: GameBoardSettings
    ) : GameViewState()
}

package my.apple.snake.ui.screens.game.models

import my.apple.snake.base.archirecture.UiState
import my.apple.snake.data.local.datastore.GameBoardSize
import my.apple.snake.data.local.datastore.GameRules
import my.apple.snake.domain.models.BonusItem
import my.apple.snake.domain.models.GameLevelConfig
import my.apple.snake.domain.models.GameStatus
import my.apple.snake.domain.models.Point
import my.apple.snake.domain.models.Snake

sealed class GameViewState : UiState {
    data object Loading : GameViewState()

    data class Display(
        val snake: Snake,
        val currentSnakeLives: Int,
        val gameLevelConfig: GameLevelConfig,
        val bonusItems: List<BonusItem>,
        val blockItems: List<Point>,
        val gameStatus: GameStatus,
        val gameRules: GameRules,
        val boardSettings: GameBoardSize,
        val time: Long,
        val sessionTime: String,
        val score: Int
    ) : GameViewState()
}
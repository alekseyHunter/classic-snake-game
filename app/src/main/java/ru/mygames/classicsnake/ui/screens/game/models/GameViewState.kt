package ru.mygames.classicsnake.ui.screens.game.models

import ru.mygames.classicsnake.data.local.datastore.GameBoardSize
import ru.mygames.classicsnake.data.local.datastore.GameRules
import ru.mygames.classicsnake.domain.models.BonusItem
import ru.mygames.classicsnake.domain.models.GameLevelConfig
import ru.mygames.classicsnake.domain.models.GameStatus
import ru.mygames.classicsnake.domain.models.Point
import ru.mygames.classicsnake.domain.models.Snake
import ru.mygames.classicsnake.ui.base.architecture.UiState

sealed interface GameViewState: UiState {
    data object Loading: GameViewState

    data class Display(
        val snake: Snake,
        val currentSnakeLives: Int,
        val maxSnakeLives: Int,
        val bonusItems: List<BonusItem>,
        val blockItems: List<Point>,
        val boardSize: GameBoardSize,
        val time: Long,
        val sessionTime: String,
        val score: Int,
        val gameStatus: GameStatus,
        val gameLevelConfig: GameLevelConfig,
        val gameRules: GameRules
    ): GameViewState
}
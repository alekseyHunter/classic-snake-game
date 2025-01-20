package my.apple.snake.ui.screens.settings.models

import my.apple.snake.base.archirecture.UiState
import my.apple.snake.data.local.datastore.GameBoardSize
import my.apple.snake.data.local.datastore.GameLevel
import my.apple.snake.data.local.datastore.GameRules
import my.apple.snake.data.local.datastore.SpawnChangeOfBonusItems

sealed class SettingsViewState : UiState {
    data object Loading : SettingsViewState()

    data class Display(
        val gameLevel: GameLevel = GameLevel.Normal,
        val boardSize: GameBoardSize = GameBoardSize.MEDIUM,
        val spawnChangeOfBonusItems: SpawnChangeOfBonusItems = SpawnChangeOfBonusItems.Normal,
        val gameRules: GameRules = GameRules()
    ) : SettingsViewState()
}
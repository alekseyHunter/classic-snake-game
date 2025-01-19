package my.apple.snake.ui.screens.settings.models

import my.apple.snake.base.archirecture.UiState
import my.apple.snake.data.local.datastore.GameRules

sealed class SettingsViewState : UiState {
    data object Loading : SettingsViewState()

    data class Display(
        val gameLevel: Int = 0,
        val boardSize: Int = 0,
        val spawnChangeOfBonusItems: Int = 0,
        val gameRules: GameRules = GameRules()
    ) : SettingsViewState()
}
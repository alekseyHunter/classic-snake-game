package my.apple.snake.ui.screens.settings.models

import my.apple.snake.base.archirecture.UiState

sealed class SettingsViewState : UiState {
    data object Loading : SettingsViewState()

    data class Display(
        val settings: String
    ) : SettingsViewState()
}

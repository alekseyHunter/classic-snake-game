package my.apple.snake.ui.screens.settings.models

import my.apple.snake.base.archirecture.UiAction

sealed class SettingsAction : UiAction {
    data class ShowDialog(val title: String, val body: String)
}

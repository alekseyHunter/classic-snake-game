package my.apple.snake.ui.screens.settings.models

import androidx.compose.ui.unit.IntSize
import my.apple.snake.domain.models.Direction
import my.apple.snake.base.archirecture.UiEvent

sealed class SettingsEvent : UiEvent {
    data object EnterScreen : SettingsEvent()
    data class ChangeSnakeDirection(val direction: Direction) : SettingsEvent()
    data class ChangeBoardSize(val size: IntSize): SettingsEvent()
}
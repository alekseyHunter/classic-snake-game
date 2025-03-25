package ru.mygames.classicsnake.ui.screens.settings.models

import ru.mygames.classicsnake.ui.base.architecture.UiEvent

sealed interface SettingsEvent: UiEvent {
    data object EnterScreen: SettingsEvent

    data class ChangeDifficultyLevel(val number: Int): SettingsEvent
    data class ChangeChance(val number: Int): SettingsEvent
    data class ChangeBoardSize(val number: Int): SettingsEvent
    data class ChangeRules(val ruleEvent: GameRuleEvent): SettingsEvent

    data object CloseScreen: SettingsEvent
}

sealed interface GameRuleEvent {
    data class ChangeRandomWalls(val newValue: Boolean): GameRuleEvent
    data class ChangeExtraLives(val newValue: Boolean): GameRuleEvent
    data class ChangeThrowTail(val newValue: Boolean): GameRuleEvent
    data class ChangeDamageCollider(val newValue: Boolean): GameRuleEvent
}
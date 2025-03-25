package ru.mygames.classicsnake.ui.screens.settings.models

import ru.mygames.classicsnake.data.local.datastore.GameBoardSize
import ru.mygames.classicsnake.data.local.datastore.GameLevel
import ru.mygames.classicsnake.data.local.datastore.GameRules
import ru.mygames.classicsnake.data.local.datastore.SpawnChanceOfBonusItems
import ru.mygames.classicsnake.ui.base.architecture.UiState

sealed interface SettingsViewState: UiState {

    data object Loading: SettingsViewState

    data class Display(
        val gameLevel: GameLevel,
        val boardSize: GameBoardSize,
        val spawnChanceOfBonusItems: SpawnChanceOfBonusItems,
        val gameRules: GameRules
    ): SettingsViewState
}
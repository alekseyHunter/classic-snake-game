package ru.mygames.classicsnake.ui.screens.settings

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mygames.classicsnake.data.local.datastore.DataStoreManager
import ru.mygames.classicsnake.data.local.datastore.GameBoardSize
import ru.mygames.classicsnake.data.local.datastore.GameLevel
import ru.mygames.classicsnake.data.local.datastore.GameRules
import ru.mygames.classicsnake.data.local.datastore.SpawnChanceOfBonusItems
import ru.mygames.classicsnake.ui.base.architecture.BaseViewModel
import ru.mygames.classicsnake.ui.screens.settings.models.GameRuleEvent
import ru.mygames.classicsnake.ui.screens.settings.models.SettingsAction
import ru.mygames.classicsnake.ui.screens.settings.models.SettingsEvent
import ru.mygames.classicsnake.ui.screens.settings.models.SettingsViewState

class SettingsViewModel(
    private val dataStoreManager: DataStoreManager
): BaseViewModel<SettingsEvent, SettingsViewState, SettingsAction>(
    SettingsViewState.Loading
) {
    override fun obtainEvent(event: SettingsEvent) {
        when(val state = viewState) {
            is SettingsViewState.Loading -> reduce(state, event)
            is SettingsViewState.Display -> reduce(state, event)
        }
    }

    private fun reduce(state: SettingsViewState.Loading, event: SettingsEvent) {
        when(event) {
            is SettingsEvent.EnterScreen -> loadData()
            else -> {

            }
        }
    }

    private fun reduce(state: SettingsViewState.Display, event: SettingsEvent) {
        when(event) {
            is SettingsEvent.ChangeChance -> changeChance(event.number)
            is SettingsEvent.ChangeBoardSize -> changeBoardSize(event.number)
            is SettingsEvent.ChangeDifficultyLevel -> changeDifficultyLevel(event.number)
            is SettingsEvent.ChangeRules -> changeRules(state, event.ruleEvent)
            else -> {

            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            dataStoreManager.appSettings.collectLatest { settings ->
                viewState = SettingsViewState.Display(
                    gameLevel = settings.gameLevel,
                    spawnChanceOfBonusItems = settings.spawnChanceOfBonusItems,
                    boardSize = settings.boardSize,
                    gameRules = settings.gameRules
                )
            }
        }
    }

    private fun changeChance(newValue: Int) {
        viewModelScope.launch {
            val spawnChanceOfBonusItems = SpawnChanceOfBonusItems.entries[newValue]
            dataStoreManager.saveSpawnChanceOfBonusItems(spawnChanceOfBonusItems)
        }
    }

    private fun changeBoardSize(newValue: Int) {
        viewModelScope.launch {
            val boardSize = GameBoardSize.entries[newValue]
            dataStoreManager.saveGameBoardSize(boardSize)
        }
    }

    private fun changeDifficultyLevel(newValue: Int) {
        viewModelScope.launch {
            val gameLevel = GameLevel.entries[newValue]
            dataStoreManager.saveGameLevel(gameLevel)
        }
    }

    private fun changeRules(state: SettingsViewState.Display, ruleEvent: GameRuleEvent) {
        viewModelScope.launch {
            val gameRules = when(ruleEvent) {
                is GameRuleEvent.ChangeDamageCollider -> {
                    state.gameRules.copy(
                        damageColliderEnabled = ruleEvent.newValue
                    )
                }
                is GameRuleEvent.ChangeThrowTail -> {
                    state.gameRules.copy(
                        throwTailEnabled = ruleEvent.newValue
                    )
                }
                is GameRuleEvent.ChangeExtraLives -> {
                    state.gameRules.copy(
                        extraLivesEnabled = ruleEvent.newValue
                    )
                }
                is GameRuleEvent.ChangeRandomWalls -> {
                    state.gameRules.copy(
                        randomWallsEnabled = ruleEvent.newValue
                    )
                }
            }

            dataStoreManager.saveGameRules(gameRules)
        }
    }
}
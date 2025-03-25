package ru.mygames.classicsnake.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow

class DataStoreManager(context: Context) {
    private val dataStore = context.settingsDataStore
    val appSettings: Flow<UserSettings> = dataStore.data

    suspend fun saveGameLevel(newValue: GameLevel) {
        dataStore.updateData {
            it.copy(gameLevel = newValue)
        }
    }

    suspend fun saveGameBoardSize(newValue: GameBoardSize) {
        dataStore.updateData {
            it.copy(boardSize = newValue)
        }
    }

    suspend fun saveSpawnChanceOfBonusItems(newValue: SpawnChanceOfBonusItems) {
        dataStore.updateData {
            it.copy(spawnChanceOfBonusItems = newValue)
        }
    }

    suspend fun saveGameRules(newValue: GameRules) {
        dataStore.updateData {
            it.copy(gameRules = newValue)
        }
    }

    suspend fun saveGameResult(newValue: UserGameResultDto) {
        dataStore.updateData {
            val gameResults = it.gameResults.toMutableMap()
            val results = gameResults.getOrDefault(it.gameLevel, emptyList()).toMutableList()

            results.add(newValue)
            results.sortByDescending { it.score }

            val topResults = results.take(10)
            gameResults[it.gameLevel] = topResults

            it.copy(gameResults = gameResults)
        }
    }

    suspend fun clearDataStore() {
        dataStore.updateData {
            UserSettings()
        }
    }

    companion object {
        val Context.settingsDataStore: DataStore<UserSettings> by dataStore(
            fileName = "settings.pb",
            serializer = UserSettingsSerializer()
        )
    }
}
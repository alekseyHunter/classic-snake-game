package my.apple.snake.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow

class DataStoreManager(context: Context) {
    private val dataStore = context.settingsDataStore
    val appSettings: Flow<UserSettings> = dataStore.data

    suspend fun saveGameLevel(value: GameLevel) {
        dataStore.updateData {
            it.copy(gameLevel = value)
        }
    }

    suspend fun saveBoardSize(value: GameBoardSize) {
        dataStore.updateData {
            it.copy(boardSize = value)
        }
    }

    suspend fun saveSpawnChanceOfBonusItems(value: SpawnChangeOfBonusItems) {
        dataStore.updateData {
            it.copy(spawnChangeOfBonusItems = value)
        }
    }

    suspend fun saveRules(value: GameRules) {
        dataStore.updateData {
            it.copy(gameRules = value)
        }
    }

    suspend fun saveGameResult(value: UserGameResult) {
        dataStore.updateData {
            val results = it.gameResults.getOrDefault(it.gameLevel, emptyList()).toMutableList()
            results.add(value)
            results.sortBy { it.score }
            val topResults = results.take(10)
            it.gameResults.toMutableMap()[it.gameLevel] = topResults
            it.copy(gameResults = it.gameResults)
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


package my.apple.snake.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow

class DataStoreManager(context: Context) {
    private val dataStore = context.settingsDataStore
    val appSettings: Flow<UserSettings> = dataStore.data

    suspend fun saveGameLevel(value: Int) {
        dataStore.updateData {
            it.copy(gameLevel = value)
        }
    }

    suspend fun saveBoardSize(value: Int) {
        dataStore.updateData {
            it.copy(boardSize = value)
        }
    }

    suspend fun saveSpawnChanceOfBonusItems(value: Int) {
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
            val results = it.gameResults.toMutableList()
            results.add(value)
            it.copy(gameResults = results)
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


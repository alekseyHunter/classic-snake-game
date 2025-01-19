package my.apple.snake.data.local.datastore

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val gameLevel: Int = 0,
    val boardSize: Int = 0,
    val spawnChangeOfBonusItems: Int = 0,
    val gameRules: GameRules = GameRules(),
    val gameResults: List<UserGameResult> = emptyList()
)

@Serializable
data class UserGameResult(
    val score: Int,
    val time: String
)

@Serializable
data class GameRules(
    val randomWallsEnabled: Boolean = true,
    val extraLivesEnabled: Boolean = false,
    val throwTailEnabled: Boolean = false,
    val damageColliderEnabled: Boolean = true
)
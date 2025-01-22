package my.apple.snake.data.local.datastore

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val gameLevel: GameLevel = GameLevel.Normal,
    val boardSize: GameBoardSize = GameBoardSize.MEDIUM,
    val spawnChangeOfBonusItems: SpawnChangeOfBonusItems = SpawnChangeOfBonusItems.Normal,
    val gameRules: GameRules = GameRules(),
    val gameResults: Map<GameLevel, List<UserGameResult>> = emptyMap()
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
    val throwTailEnabled: Boolean = true,
    val damageColliderEnabled: Boolean = true
)

@Serializable
enum class GameLevel {
    Easy,
    Normal,
    Hard,
}

@Serializable
enum class SpawnChangeOfBonusItems {
    Low,
    Normal,
    Often
}

@Serializable
enum class GameBoardSize(val rows: Int, val columns: Int) {
    SMALL(10, 10),
    MEDIUM(15, 15),
    HUGE(20, 20)
}

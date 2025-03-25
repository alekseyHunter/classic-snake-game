package ru.mygames.classicsnake.data.local.datastore

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val gameLevel: GameLevel = GameLevel.Normal,
    val boardSize: GameBoardSize = GameBoardSize.Medium,
    val spawnChanceOfBonusItems: SpawnChanceOfBonusItems = SpawnChanceOfBonusItems.Normal,
    val gameRules: GameRules = GameRules(),
    val gameResults: Map<GameLevel, List<UserGameResultDto>> = emptyMap()
)

@Serializable
data class UserGameResultDto(
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

@Serializable
enum class GameLevel {
    Easy,
    Normal,
    Hard
}

@Serializable
enum class SpawnChanceOfBonusItems {
    Low,
    Normal,
    Often
}

@Serializable
enum class GameBoardSize(val rows: Int, val columns: Int) {
    Small(10, 10),
    Medium(20, 20),
    Huge(30, 30)
}
package ru.mygames.classicsnake.domain.models

sealed class GameLevelConfig(
    val snakeSpeed: Long,
    val blockItemRespawnTime: Long,
    val startSnakeLives: Int,
    val maxSnakeLives: Int
) {
    data object Easy: GameLevelConfig(
        blockItemRespawnTime = 6000L,
        maxSnakeLives = 4,
        startSnakeLives = 4,
        snakeSpeed = 300L
    )

    data object Medium: GameLevelConfig(
        blockItemRespawnTime = 5000L,
        maxSnakeLives = 4,
        startSnakeLives = 2,
        snakeSpeed = 275L
    )

    data object Hard: GameLevelConfig(
        blockItemRespawnTime = 4000L,
        maxSnakeLives = 4,
        startSnakeLives = 1,
        snakeSpeed = 250L
    )
}
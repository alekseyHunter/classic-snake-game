package ru.mygames.classicsnake.domain.models

sealed interface GameStatus {
    data object Playing: GameStatus
    data object Stopped: GameStatus
    data object Finished: GameStatus
}
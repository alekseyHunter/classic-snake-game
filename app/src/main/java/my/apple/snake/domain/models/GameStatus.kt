package my.apple.snake.domain.models

sealed class GameStatus {
    data object Playing: GameStatus()
    data object Stopped: GameStatus()
    data object Finished: GameStatus()
}
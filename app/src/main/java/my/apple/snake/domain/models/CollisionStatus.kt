package my.apple.snake.domain.models

sealed class CollisionStatus {
    data object None : CollisionStatus()
    data class Board(val point: Point) : CollisionStatus()
    data class Snake(val point: Point) : CollisionStatus()
    data class BonusItem(val point: Point) : CollisionStatus()
    data class BlockItem(val point: Point) : CollisionStatus()
}
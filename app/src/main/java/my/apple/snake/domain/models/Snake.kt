package my.apple.snake.domain.models

data class Snake(
    val body: List<Point>,
    val direction: Direction = Direction.NONE
)
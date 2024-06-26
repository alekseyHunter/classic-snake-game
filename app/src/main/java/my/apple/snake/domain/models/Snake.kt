package my.apple.snake.domain.models

data class Snake(
    val body: List<Point> = listOf(Point(5, 18), Point(5, 19)),
    var direction: Direction = Direction.NONE
) {

    fun move(dir: Direction): Snake {
        val body = body.toMutableList()
        body.add(0, Point(body[0].x + dir.dx, body[0].y + dir.dy))

        if (body.size > 1) body.removeLast()

        return Snake(body, dir)
    }

    fun checkCollision(boardWidth: Int, boardHeight: Int): Boolean {
        if (body[0].x <= 0 || body[0].x >= boardWidth - 1 || body[0].y <= 0 || body[0].y >= boardHeight - 1)
            return true

        for (i in 1 until body.size) {
            if (body[0].x == body[i].x && body[0].y == body[i].y)
                return true
        }

        return false
    }

    fun checkCollision(item: BonusItem): Boolean {
        return body[0].x == item.position.x && body[0].y == item.position.y
    }

    fun grow() {
        val body = body.toMutableList()
        body.add(Point(body[body.size - 1].x, body[body.size - 1].y))
    }
}

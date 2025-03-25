package ru.mygames.classicsnake.domain.repository

import ru.mygames.classicsnake.data.local.datastore.GameBoardSize
import ru.mygames.classicsnake.domain.models.BonusItem
import ru.mygames.classicsnake.domain.models.CollisionStatus
import ru.mygames.classicsnake.domain.models.Direction
import ru.mygames.classicsnake.domain.models.Point
import ru.mygames.classicsnake.domain.models.Snake

class SnakeController {

    fun spawn(point: Point): Snake {
        return Snake(listOf(point))
    }

    fun rotate(snake: Snake, direction: Direction): Snake {
        return snake.copy(direction = direction)
    }

    fun move(snake: Snake): Snake {
        if (snake.body.isEmpty())
            return snake

        if (snake.direction == Direction.NONE)
            return snake

        val body = snake.body.toMutableList()
        body.add(0, Point(body[0].x + snake.direction.dx, body[0].y + snake.direction.dy))

        if (body.size > 1)
            body.removeAt(body.lastIndex)

        return snake.copy(body = body)
    }

    fun checkCollision(
        snake: Snake,
        boardSize: GameBoardSize,
        bonusItems: List<BonusItem>,
        blockItems: List<Point>
    ): CollisionStatus {
        val snakeCollisionStatus = checkSnakeCollision(snake)
        val boardCollisionStatus = checkBoardCollision(snake, boardSize)
        val bonusItemCollisionStatus = checkBonusItemCollision(snake, bonusItems)
        val blockItemCollisionStatus = checkBlockItemCollision(snake, blockItems)

        return if (snakeCollisionStatus != CollisionStatus.None) {
            snakeCollisionStatus
        } else if (boardCollisionStatus != CollisionStatus.None) {
            boardCollisionStatus
        } else if (bonusItemCollisionStatus != CollisionStatus.None) {
            bonusItemCollisionStatus
        } else if (blockItemCollisionStatus != CollisionStatus.None) {
            blockItemCollisionStatus
        } else {
            CollisionStatus.None
        }
    }

    private fun checkSnakeCollision(snake: Snake): CollisionStatus {
        if (snake.body.size > 4) {
            for (index in 1..snake.body.lastIndex) {
                if (
                    snake.body[index].x == snake.body.first().x &&
                    snake.body[index].y == snake.body.first().y
                ) {
                    return CollisionStatus.Snake(snake.body.first())
                }
            }
        }

        return CollisionStatus.None
    }

    private fun checkBoardCollision(snake: Snake, boardSize: GameBoardSize): CollisionStatus {
        if (snake.body.isNotEmpty()) {
            val newX = snake.body[0].x + snake.direction.dx
            val newY = snake.body[0].y + snake.direction.dy

            if (newX < 0 || newX >= boardSize.columns)
                return CollisionStatus.Board(snake.body.first())
            else if (newY < 0 || newY >= boardSize.rows)
                return CollisionStatus.Board(snake.body.first())
        }

        return CollisionStatus.None
    }

    private fun checkBonusItemCollision(
        snake: Snake,
        bonusItems: List<BonusItem>
    ): CollisionStatus {
        if (snake.body.isNotEmpty()) {
            bonusItems.forEach { item ->
                if (
                    item.position.x == snake.body.first().x &&
                    item.position.y == snake.body.first().y
                ) {
                    return CollisionStatus.BonusItem(snake.body.first())
                }
            }
        }

        return CollisionStatus.None
    }

    private fun checkBlockItemCollision(snake: Snake, blockItems: List<Point>): CollisionStatus {
        if (snake.body.isNotEmpty()) {
            val newX = snake.body[0].x + snake.direction.dx
            val newY = snake.body[0].y + snake.direction.dy

            blockItems.forEach { item ->
                if (item.x == newX && item.y == newY) {
                    return CollisionStatus.BlockItem(snake.body.first())
                }
            }
        }

        return CollisionStatus.None
    }

    fun grow(snake: Snake): Snake {
        if (snake.body.isEmpty())
            return snake

        val body = snake.body.toMutableList()
        body.add(Point(body.last().x, body.last().y))

        return snake.copy(body = body)
    }

    fun discard(snake: Snake, distance: Int): Snake {
        val body = snake.body.map {
            it.copy(
                it.x - snake.direction.dx * distance,
                it.y - snake.direction.dy * distance
            )
        }

        return snake.copy(body = body)
    }

    fun throwTail(snake: Snake, point: Point): Snake {
        val index = snake.body.indexOfLast { it == point }

        if(index == -1)
            return snake

        val body = snake.body.toMutableList()
            .dropLast(snake.body.lastIndex - index + 1)

        return snake.copy(body = body)
    }
}
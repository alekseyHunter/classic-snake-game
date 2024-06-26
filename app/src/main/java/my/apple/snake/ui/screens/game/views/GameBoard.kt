package my.apple.snake.ui.screens.game.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import my.apple.snake.domain.models.BonusItem
import my.apple.snake.domain.models.BonusStatus
import my.apple.snake.domain.models.Direction
import my.apple.snake.domain.models.GameStatus
import my.apple.snake.domain.models.Point
import my.apple.snake.domain.models.Snake
import my.apple.snake.domain.models.GameBoardSettings
import my.apple.snake.ui.screens.game.models.GameViewState

@Composable
fun GameBoard(
    state: GameViewState.Display,
    snake: Snake,
    bonusItems: List<BonusItem>,
    onMove: (Direction) -> Unit,
    onChangeBoardSize: (IntSize) -> Unit
) {
    BoxWithConstraints(Modifier.fillMaxSize()) {
        Canvas(
            Modifier
                .requiredSize(this.maxWidth, this.maxWidth)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragDistance ->
                        val direction = when {
                            dragDistance.x > 0 -> Direction.RIGHT
                            dragDistance.x < 0 -> Direction.LEFT
                            dragDistance.y > 0 -> Direction.DOWN
                            dragDistance.y < 0 -> Direction.UP
                            else -> Direction.NONE
                        }

                        if (direction != Direction.NONE) {
                            onMove(direction)
                        }
                    }
                }
                .onSizeChanged {
                    onChangeBoardSize.invoke(IntSize(it.width, it.width))
                }
        ) {

            val canvasWidth = size.width
            val canvasHeight = size.height

            val fieldWidth = canvasWidth / state.boardSettings.rows
            val fieldHeight = canvasHeight / state.boardSettings.columns


            drawBoard(
                this,
                state.boardSettings.rows,
                state.boardSettings.columns,
                fieldWidth,
                fieldHeight
            )

            drawSnake(this, snake, fieldWidth, fieldHeight)
            bonusItems.forEach {
                drawApple(this, it, fieldWidth, fieldHeight)
            }
        }
    }
}

// Отрисовка сетки поля
fun drawBoard(
    drawScope: DrawScope,
    rows: Int,
    columns: Int,
    fieldWidth: Float,
    fieldHeight: Float
) {
    for (x in 0 until rows) {

        drawScope.drawLine(
            color = Color.Black,
            strokeWidth = 1f,
            start = Offset(x * fieldWidth, 0f),
            end = Offset(x * fieldWidth, drawScope.size.height),
        )
    }

    for (y in 0 until columns) {
        drawScope.drawLine(
            color = Color.Black,
            strokeWidth = 1f,
            start = Offset(0f, y * fieldHeight),
            end = Offset(drawScope.size.width, y * fieldHeight),
        )
    }
}

fun drawSnake(drawScope: DrawScope, snake: Snake, width: Float, height: Float) {

    snake.body.forEachIndexed { index, point ->
        if (index == 0) {
            drawScope.drawRoundRect(
                color = Color.Green,
                topLeft = Offset(
                    (point.x * width),
                    (point.y * height)
                ),
                size = Size(width, height),
                cornerRadius = CornerRadius(width / 4f, height / 4f)
            )

            drawScope.drawCircle(
                color = Color.Black,
                center = Offset(
                    (point.x * width + width / 4),
                    (point.y * height + height / 4)
                ),
                radius = width / 6f
            )

            drawScope.drawCircle(
                color = Color.Black,
                center = Offset(
                    (point.x * width + width / 4 * 3),
                    (point.y * height + height / 4)
                ),
                radius = width / 6f
            )
        } else {
            drawScope.drawRoundRect(
                color = Color.Green,
                topLeft = Offset(
                    (point.x * width),
                    (point.y * height)
                ),
                size = Size(width, height),
                cornerRadius = CornerRadius(width / 4f, height / 4f)
            )
        }
    }
}

fun drawApple(drawScope: DrawScope, apple: BonusItem, width: Float, height: Float) {
    drawScope.drawCircle(
        color = Color.Red,
        center = Offset(
            (apple.position.x * width + width / 2),
            (apple.position.y * height + height / 2)
        ),
        radius = width / 2f
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun ShowPreview() {
    val state = GameViewState.Display(
        snake = Snake(),
        bonusItems = listOf(BonusItem(Point(10, 1), BonusStatus.IncreaseScore)),
        gameStatus = GameStatus.Playing,
        boardSettings = GameBoardSettings(20, 20)
    )

    GameBoard(
        state = state, snake = state.snake, bonusItems = state.bonusItems, onMove = {},
    ) {

    }
}
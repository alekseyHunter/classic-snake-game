package my.apple.snake.ui.screens.game.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.scale
import my.apple.snake.R
import my.apple.snake.data.local.datastore.GameBoardSize
import my.apple.snake.data.local.datastore.GameRules
import my.apple.snake.domain.models.BonusItem
import my.apple.snake.domain.models.BonusStatus
import my.apple.snake.domain.models.GameLevelConfig
import my.apple.snake.domain.models.GameStatus
import my.apple.snake.domain.models.Point
import my.apple.snake.domain.models.Snake
import my.apple.snake.ui.screens.game.models.GameViewState

@Composable
fun GameBoard(
    boardSettings: GameBoardSize,
    snake: Snake,
    bonusItems: List<BonusItem>,
    blockItems: List<Point>
) {
    val snakeHeadImage = ImageBitmap.imageResource(R.drawable.ic_snake_head)
    val snakeTailImage = ImageBitmap.imageResource(R.drawable.ic_snake_tail)
    val appleImage = ImageBitmap.imageResource(R.drawable.ic_apple)
    val wallImage = ImageBitmap.imageResource(R.drawable.ic_wall)

    BoxWithConstraints(
        Modifier
            .wrapContentSize()
            .border(6.dp, Color(0xFFD7E0D7), RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(6.dp)
    ) {
        Canvas(
            Modifier
                .requiredSize(this.maxWidth - 6.dp, this.maxWidth - 6.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val fieldWidth = canvasWidth / boardSettings.rows
            val fieldHeight = canvasHeight / boardSettings.columns

            bonusItems.forEach { item ->
                drawObject(this, appleImage, item.position, fieldWidth, fieldHeight)
            }
            blockItems.forEach { item ->
                drawObject(this, wallImage, item, fieldWidth, fieldHeight)
            }

            drawSnake(this, snake, snakeHeadImage, snakeTailImage, fieldWidth, fieldHeight)
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

fun drawSnake(
    drawScope: DrawScope,
    snake: Snake,
    snakeHead: ImageBitmap,
    snakeTail: ImageBitmap,
    width: Float,
    height: Float
) {
    snake.body.forEachIndexed { index, point ->
        val image = if (index == 0) {
            snakeHead
        } else {
            snakeTail
        }

        drawObject(
            drawScope = drawScope,
            imageBitmap = image,
            coordinates = point,
            width = width,
            height = height
        )
    }
}

fun drawObject(
    drawScope: DrawScope,
    imageBitmap: ImageBitmap,
    coordinates: Point,
    width: Float,
    height: Float
) {
    val scaledBitmap = imageBitmap.asAndroidBitmap()
        .scale(
            width = width.toInt(),
            height = height.toInt()
        )
        .asImageBitmap()

    drawScope.drawImage(
        image = scaledBitmap,
        topLeft = Offset(
            coordinates.x * width,
            coordinates.y * height
        )
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun ShowPreview() {
    val state = GameViewState.Display(
        snake = Snake(listOf()),
        1,
        GameLevelConfig.Medium,
        bonusItems = listOf(BonusItem(Point(10, 1), BonusStatus.IncreaseScore)),
        blockItems = listOf(Point(10, 5)),
        gameStatus = GameStatus.Playing,
        boardSettings = GameBoardSize.SMALL,
        time = 0,
        sessionTime = "00:00",
        score = 0,
        gameRules = GameRules()
    )

    GameBoard(
        boardSettings = state.boardSettings,
        snake = state.snake,
        bonusItems = state.bonusItems,
        blockItems = state.blockItems
    )
}
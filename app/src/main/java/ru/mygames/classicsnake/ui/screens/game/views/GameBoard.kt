package ru.mygames.classicsnake.ui.screens.game.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.scale
import ru.mygames.classicsnake.R
import ru.mygames.classicsnake.data.local.datastore.GameBoardSize
import ru.mygames.classicsnake.domain.models.BonusItem
import ru.mygames.classicsnake.domain.models.BonusType
import ru.mygames.classicsnake.domain.models.Point
import ru.mygames.classicsnake.domain.models.Snake
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

@Composable
fun GameBoard(
    snake: Snake,
    bonusItems: List<BonusItem>,
    blockItems: List<Point>,
    boardSize: GameBoardSize,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val snakeHeadImage = remember {
        context.getDrawable(R.drawable.ic_snake_head)?.toBitmap()?.asImageBitmap()
    }
    val snakeTailImage = remember {
        context.getDrawable(R.drawable.ic_snake_tail)?.toBitmap()?.asImageBitmap()
    }
    val appleImage = remember {
        context.getDrawable(R.drawable.ic_apple)?.toBitmap()?.asImageBitmap()
    }
    val wallImage = remember {
        context.getDrawable(R.drawable.ic_wall)?.toBitmap()?.asImageBitmap()
    }

    BoxWithConstraints(
        modifier = modifier.border(
            6.dp,
            MaterialTheme.colorScheme.onTertiaryContainer.copy(0.35f),
            RoundedCornerShape(16.dp)
        ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .padding(6.dp)
                .requiredSize(this.maxWidth - 12.dp)
                .background(
                    MaterialTheme.colorScheme.onTertiaryContainer.copy(0.05f),
                    RoundedCornerShape(16.dp)
                )
        ) {
            val fieldWidth = this.size.width / boardSize.columns
            val fieldHeight = this.size.height / boardSize.rows

            if (appleImage != null) {
                bonusItems.forEach { item ->
                    drawObject(
                        drawScope = this,
                        imageBitmap = appleImage,
                        coordinates = item.position,
                        width = fieldWidth,
                        height = fieldHeight
                    )
                }
            }
            if (wallImage != null) {
                blockItems.forEach { item ->
                    drawObject(
                        drawScope = this,
                        imageBitmap = wallImage,
                        coordinates = item,
                        width = fieldWidth,
                        height = fieldHeight
                    )
                }
            }

            if (snakeHeadImage != null && snakeTailImage != null) {
                drawSnake(
                    drawScope = this,
                    snakeHead = snakeHeadImage,
                    snakeTail = snakeTailImage,
                    snake = snake,
                    width = fieldWidth,
                    height = fieldHeight
                )
            }
        }
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

@Preview
@Composable
private fun ShowPreview() {
    ClassicSnakeTheme {
        GameBoard(
            snake = Snake(listOf(Point(5, 5), Point(5, 6))),
            bonusItems = listOf(BonusItem(Point(4, 1), BonusType.IncreaseScore)),
            blockItems = listOf(Point(3, 2)),
            boardSize = GameBoardSize.Medium
        )
    }
}
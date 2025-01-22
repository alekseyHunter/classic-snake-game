package my.apple.snake.ui.screens.game.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.apple.snake.domain.models.Direction
import my.apple.snake.ui.screens.game.models.GameEvent
import my.apple.snake.ui.theme.AppleSnakeTheme
import my.apple.snake.ui.theme.Gray200
import my.apple.snake.ui.theme.components.JetIconButton


@Composable
fun GameBottomBar(
    snakeDirection: Direction,
    modifier: Modifier = Modifier,
    dispatcher: (GameEvent) -> Unit
) {
    Box(
        modifier = modifier
            .background(
                Gray200.copy(0.15f),
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 64.dp,
                    bottomEnd = 64.dp
                )
            )
    ) {

        JetIconButton(
            resId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_settings_24_filled,
            iconColor = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
                .height(48.dp)
        ) {

        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            JetIconButton(
                resId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_arrow_up_24_filled,
                iconColor = Color.Black,
                modifier = Modifier.size(72.dp, 48.dp),
                enabled = snakeDirection != Direction.DOWN
            ) {
                dispatcher.invoke(GameEvent.ChangeSnakeDirection(Direction.UP))
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                JetIconButton(
                    resId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_arrow_left_24_filled,
                    iconColor = Color.Black,
                    modifier = Modifier.size(72.dp, 48.dp),
                    enabled = snakeDirection != Direction.RIGHT
                ) {
                    dispatcher.invoke(GameEvent.ChangeSnakeDirection(Direction.LEFT))
                }

                JetIconButton(
                    resId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_arrow_down_24_filled,
                    iconColor = Color.Black,
                    modifier = Modifier.size(72.dp, 48.dp),
                    enabled = snakeDirection != Direction.UP
                ) {
                    dispatcher.invoke(GameEvent.ChangeSnakeDirection(Direction.DOWN))
                }

                JetIconButton(
                    resId = com.microsoft.fluent.mobile.icons.R.drawable.ic_fluent_arrow_right_24_filled,
                    iconColor = Color.Black,
                    modifier = Modifier.size(72.dp, 48.dp),
                    enabled = snakeDirection != Direction.LEFT
                ) {
                    dispatcher.invoke(GameEvent.ChangeSnakeDirection(Direction.RIGHT))
                }
            }
        }
    }
}


@Preview
@Composable
private fun ShowPreview() {
    AppleSnakeTheme {
        GameBottomBar(
            snakeDirection = Direction.LEFT,
            modifier = Modifier.fillMaxWidth()
        ) {

        }
    }
}
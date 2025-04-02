package ru.mygames.classicsnake.ui.screens.game.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.microsoft.fluent.mobile.icons.R
import ru.mygames.classicsnake.domain.models.Direction
import ru.mygames.classicsnake.ui.screens.game.models.GameEvent
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme
import ru.mygames.classicsnake.ui.theme.components.JetIconButton

@Composable
fun GameBottomBar(
    snakeDirection: Direction,
    modifier: Modifier = Modifier,
    dispatcher: (GameEvent) -> Unit
) {
    Box(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.onSecondaryContainer.copy(0.1f),
                RoundedCornerShape(16.dp, 16.dp, 64.dp, 64.dp)
            )
    ) {
        JetIconButton(
            resId = R.drawable.ic_fluent_settings_24_filled, iconColor = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp)
                .size(72.dp, 48.dp)
        ) {

        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp, top = 96.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            JetIconButton(
                resId = R.drawable.ic_fluent_arrow_up_24_filled,
                iconColor = Color.Black,
                modifier = Modifier.size(72.dp, 48.dp),
                enabled = snakeDirection != Direction.DOWN
            ) {
                dispatcher.invoke(GameEvent.ChangeSnakeDirection(Direction.UP))
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                JetIconButton(
                    resId = R.drawable.ic_fluent_arrow_left_24_filled,
                    iconColor = Color.Black,
                    modifier = Modifier.size(72.dp, 48.dp),
                    enabled = snakeDirection != Direction.RIGHT
                ) {
                    dispatcher.invoke(GameEvent.ChangeSnakeDirection(Direction.LEFT))
                }

                JetIconButton(
                    resId = R.drawable.ic_fluent_arrow_down_24_filled,
                    iconColor = Color.Black,
                    modifier = Modifier.size(72.dp, 48.dp),
                    enabled = snakeDirection != Direction.UP
                ) {
                    dispatcher.invoke(GameEvent.ChangeSnakeDirection(Direction.DOWN))
                }

                JetIconButton(
                    resId = R.drawable.ic_fluent_arrow_right_24_filled,
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
    ClassicSnakeTheme {
        GameBottomBar(modifier = Modifier.fillMaxWidth(), snakeDirection = Direction.UP) {

        }
    }
}
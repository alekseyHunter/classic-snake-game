package my.apple.snake.ui.screens.rating.table.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.apple.snake.data.local.datastore.GameLevel
import my.apple.snake.data.local.datastore.UserGameResult
import my.apple.snake.ui.screens.rating.table.models.RatingTableEvent
import my.apple.snake.ui.screens.rating.table.models.RatingTableViewState
import my.apple.snake.ui.theme.AppleSnakeTheme
import my.apple.snake.ui.theme.components.JetSection
import my.apple.snake.ui.theme.components.JetSwitch
import my.apple.snake.ui.theme.components.JetTextButton

@Composable
fun RatingTableViewDisplay(
    state: RatingTableViewState.Display,
    dispatcher: (RatingTableEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Рейтинговая таблица",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )

        JetSection(label = "Уровень сложности") {
            JetSwitch(
                items = listOf("Легко", "Нормально", "Сложно"),
                selectedItemId = state.gameLevel.ordinal,

                modifier = Modifier.fillMaxWidth()
            ) {
                dispatcher.invoke(RatingTableEvent.ChangeDifficultyLevel(it))
            }
        }

        JetSection(
            label = "Статистика по играм",
            modifier = Modifier.weight(1f)
        ) {
            RatingTableCard(
                results = state.gameResults,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        JetTextButton(text = "Вернуться", modifier = Modifier.fillMaxWidth(), onClick = {
            dispatcher.invoke(RatingTableEvent.CloseScreen)
        })
    }
}

@Preview
@Composable
private fun ShowPreview() {
    AppleSnakeTheme {
        RatingTableViewDisplay(
            state = RatingTableViewState.Display(
                GameLevel.Normal,
                listOf(
                    UserGameResult(
                        157,
                        "01:00"
                    ),
                    UserGameResult(
                        135,
                        "01:00"
                    ),
                    UserGameResult(
                        118,
                        "01:00"
                    ),
                    UserGameResult(
                        100,
                        "01:00"
                    )
                )
            )
        ) {

        }
    }
}
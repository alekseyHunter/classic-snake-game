package ru.mygames.classicsnake.ui.screens.rating.table.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mygames.classicsnake.data.local.datastore.GameLevel
import ru.mygames.classicsnake.ui.screens.rating.table.models.RatingTableEvent
import ru.mygames.classicsnake.ui.screens.rating.table.models.RatingTableViewState
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme
import ru.mygames.classicsnake.ui.theme.components.JetSection
import ru.mygames.classicsnake.ui.theme.components.JetSwitch
import ru.mygames.classicsnake.ui.theme.components.JetTextButton

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
            JetSwitch(items = listOf("Легко", "Нормально", "Сложно"), selectedItemId = state.gameLevel.ordinal) {
                dispatcher.invoke(RatingTableEvent.ChangeDifficultyLevel(it))
            }
        }

        JetSection(label = "Статистика по играм", modifier = Modifier.weight(1f)) {
            RatingTableCard(results = state.gameResults)
        }

        Spacer(modifier = Modifier)

        JetTextButton(text = "Вернуться", modifier = Modifier.fillMaxWidth()) {
            dispatcher.invoke(RatingTableEvent.CloseScreen)
        }
    }
}

@Preview
@Composable
private fun ShowPreview() {
    ClassicSnakeTheme {
        RatingTableViewDisplay(
            state = RatingTableViewState.Display(
                GameLevel.Normal,
                emptyList()
            )
        ) {

        }
    }
}
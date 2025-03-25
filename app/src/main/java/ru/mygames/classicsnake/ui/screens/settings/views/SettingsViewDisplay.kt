package ru.mygames.classicsnake.ui.screens.settings.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mygames.classicsnake.data.local.datastore.GameBoardSize
import ru.mygames.classicsnake.data.local.datastore.GameLevel
import ru.mygames.classicsnake.data.local.datastore.GameRules
import ru.mygames.classicsnake.data.local.datastore.SpawnChanceOfBonusItems
import ru.mygames.classicsnake.ui.screens.settings.models.GameRuleEvent
import ru.mygames.classicsnake.ui.screens.settings.models.SettingsEvent
import ru.mygames.classicsnake.ui.screens.settings.models.SettingsViewState
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme
import ru.mygames.classicsnake.ui.theme.components.JetCheckBox
import ru.mygames.classicsnake.ui.theme.components.JetSection
import ru.mygames.classicsnake.ui.theme.components.JetSwitch
import ru.mygames.classicsnake.ui.theme.components.JetTextButton

@Composable
fun SettingsViewDisplay(
    state: SettingsViewState.Display,
    dispatcher: (SettingsEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Настройки",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )

        JetSection(label = "Уровень сложности") {
            JetSwitch(
                items = listOf("Легко", "Нормально", "Сложно"),
                selectedItemId = state.gameLevel.ordinal
            ) {
                dispatcher.invoke(SettingsEvent.ChangeDifficultyLevel(it))
            }
        }

        JetSection(label = "Шанс появления бонусных предметов") {
            JetSwitch(
                items = listOf("Редко", "Часто", "Регулярно"),
                selectedItemId = state.spawnChanceOfBonusItems.ordinal
            ) {
                dispatcher.invoke(SettingsEvent.ChangeChance(it))
            }
        }

        JetSection(label = "Размер игровой карты") {
            JetSwitch(
                items = listOf("Маленькая", "Средняя", "Огромная"),
                selectedItemId = state.boardSize.ordinal
            ) {
                dispatcher.invoke(SettingsEvent.ChangeBoardSize(it))
            }
        }

        Text(
            text = "Правила игры",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelSmall
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.secondaryContainer,
                    RoundedCornerShape(32.dp)
                )
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(32.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            JetCheckBox(
                checked = state.gameRules.damageColliderEnabled,
                label = "Урон от столкновения со стенами"
            ) {
                dispatcher.invoke(SettingsEvent.ChangeRules(GameRuleEvent.ChangeDamageCollider(it)))
            }

            JetCheckBox(
                checked = state.gameRules.extraLivesEnabled,
                label = "Дополнительные жизни"
            ) {
                dispatcher.invoke(SettingsEvent.ChangeRules(GameRuleEvent.ChangeExtraLives(it)))
            }

            JetCheckBox(checked = state.gameRules.throwTailEnabled, label = "Отбрасывание хвоста") {
                dispatcher.invoke(SettingsEvent.ChangeRules(GameRuleEvent.ChangeThrowTail(it)))
            }

            JetCheckBox(
                checked = state.gameRules.randomWallsEnabled,
                label = "Случайные кирпичные стены"
            ) {
                dispatcher.invoke(SettingsEvent.ChangeRules(GameRuleEvent.ChangeRandomWalls(it)))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        JetTextButton(text = "Вернуться", modifier = Modifier.fillMaxWidth()) {
            dispatcher.invoke(SettingsEvent.CloseScreen)
        }
    }
}

@Preview
@Composable
private fun ShowPreview() {
    ClassicSnakeTheme {
        SettingsViewDisplay(
            state = SettingsViewState.Display(
                GameLevel.Normal,
                GameBoardSize.Medium,
                SpawnChanceOfBonusItems.Normal,
                GameRules()
            )
        ) {

        }
    }
}
package ru.mygames.classicsnake.ui.screens.game.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mygames.classicsnake.data.local.datastore.GameBoardSize
import ru.mygames.classicsnake.data.local.datastore.GameRules
import ru.mygames.classicsnake.domain.models.BonusItem
import ru.mygames.classicsnake.domain.models.BonusType
import ru.mygames.classicsnake.domain.models.GameLevelConfig
import ru.mygames.classicsnake.domain.models.GameStatus
import ru.mygames.classicsnake.domain.models.Point
import ru.mygames.classicsnake.domain.models.Snake
import ru.mygames.classicsnake.ui.screens.game.models.GameEvent
import ru.mygames.classicsnake.ui.screens.game.models.GameViewState
import ru.mygames.classicsnake.ui.theme.ClassicSnakeTheme

@Composable
fun GameViewDisplay(
    state: GameViewState.Display,
    dispatcher: (GameEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GameTopBar(
            sessionTime = state.sessionTime,
            score = state.score,
            currentSnakeLives = state.currentSnakeLives,
            maxSnakeLives = state.maxSnakeLives,
            modifier = Modifier.fillMaxWidth()
        )

        GameBoard(
            snake = state.snake,
            bonusItems = state.bonusItems,
            blockItems = state.blockItems,
            boardSize = state.boardSize,
            modifier = Modifier.fillMaxWidth()
        )

        GameBottomBar(
            snakeDirection = state.snake.direction,
            modifier = Modifier.fillMaxWidth(),
            dispatcher = dispatcher
        )
    }
}

@Preview
@Composable
private fun ShowPreview() {
    ClassicSnakeTheme {
        GameViewDisplay(
            state = GameViewState.Display(
                snake = Snake(listOf(Point(5,5))),
                currentSnakeLives = 1,
                maxSnakeLives = 4,
                time = 0L,
                score = 0,
                sessionTime = "00:00",
                gameRules = GameRules(),
                gameStatus = GameStatus.Playing,
                gameLevelConfig = GameLevelConfig.Hard,
                bonusItems = listOf(BonusItem(Point(4, 1), BonusType.IncreaseScore)),
                blockItems = listOf(Point(3, 2)),
                boardSize = GameBoardSize.Medium
            )
        ) {

        }
    }
}
package my.apple.snake.ui.screens.game.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.apple.snake.data.local.datastore.GameBoardSize
import my.apple.snake.data.local.datastore.GameRules
import my.apple.snake.domain.models.GameLevelConfig
import my.apple.snake.domain.models.GameStatus
import my.apple.snake.domain.models.Point
import my.apple.snake.domain.models.Snake
import my.apple.snake.ui.screens.game.models.GameEvent
import my.apple.snake.ui.screens.game.models.GameViewState
import my.apple.snake.ui.theme.AppleSnakeTheme

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
            maxSnakeLives = state.gameLevelConfig.maxSnakeLives,
            modifier = Modifier.fillMaxWidth()
        )

        GameBoard(
            boardSettings = state.boardSettings,
            snake = state.snake,
            bonusItems = state.bonusItems,
            blockItems = state.blockItems
        )

        GameBottomBar(
            snakeDirection = state.snake.direction,
            modifier = Modifier.fillMaxSize(),
            dispatcher = dispatcher
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun ShowPreview() {
    AppleSnakeTheme {
        GameViewDisplay(
            state = GameViewState.Display(
                Snake(listOf(Point(5, 5))),
                1, GameLevelConfig.Medium,
                listOf(),
                listOf(),
                GameStatus.Playing,
                GameRules(),
                GameBoardSize.SMALL,
                0,
                "00:00",
                100
            ),
            dispatcher = {

            }
        )
    }
}
package my.apple.snake.ui.screens.game

import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import my.apple.snake.domain.models.BonusItem
import my.apple.snake.domain.models.BonusStatus
import my.apple.snake.domain.models.Direction
import my.apple.snake.domain.models.GameStatus
import my.apple.snake.domain.models.Point
import my.apple.snake.ui.screens.game.models.GameAction
import my.apple.snake.ui.screens.game.models.GameEvent
import my.apple.snake.ui.screens.game.models.GameViewState
import my.apple.snake.domain.models.Snake
import my.apple.snake.domain.models.GameBoardSettings
import my.apple.snake.base.archirecture.BaseViewModel
import kotlin.random.Random

class GameViewModel : BaseViewModel<GameEvent, GameViewState, GameAction>(
    GameViewState.Loading
) {
    override fun obtainEvent(event: GameEvent) {
        when (val state = viewState) {
            is GameViewState.Loading -> event(state, event)
            is GameViewState.Display -> event(state, event)
        }
    }

    private fun event(state: GameViewState.Loading, event: GameEvent) {
        when (event) {
            is GameEvent.EnterScreen -> startGame()

            else -> {

            }
        }
    }

    private fun event(state: GameViewState.Display, event: GameEvent) {
        when (event) {
            is GameEvent.ChangeSnakeDirection -> changeSnakeDirection(state, event.direction)
            is GameEvent.ChangeBoardSize -> changeBoardSize(state, event.size)
            else -> {

            }
        }
    }

    private fun changeSnakeDirection(state: GameViewState.Display, value: Direction) {
        viewState = state.copy(snake = state.snake.copy(direction = value))
    }

    private fun changeBoardSize(state: GameViewState.Display, value: IntSize) {
        // = state.copy(boardSettings = GameBoardSettings(value.width / 20, value.height / 20))
        loadGame()
    }

    private fun startGame() {
        viewModelScope.launch {
            viewState = GameViewState.Display(
                snake = Snake(direction = Direction.UP),
                bonusItems = listOf(),
                gameStatus = GameStatus.Playing,
                boardSettings = GameBoardSettings(20, 20)
            )
        }
    }

    private fun generateBonusItem(state: GameViewState.Display): BonusItem {
        var randomRow = 0
        var randomColumn = 0
        do {
            randomRow = Random.nextInt(0, state.boardSettings.rows)
            randomColumn = Random.nextInt(0, state.boardSettings.columns)
        } while (state.bonusItems.any { it.position.x == randomRow && it.position.y == randomColumn })

        return BonusItem(Point(randomRow, randomColumn), BonusStatus.IncreaseScore)
    }

    private fun loadGame() {
        viewModelScope.launch {
            while (true) {
                when (val state = viewState) {
                    is GameViewState.Display -> {
                        if (
                            state.snake.checkCollision(
                                state.boardSettings.rows,
                                state.boardSettings.columns
                            )
                        ) {

                        } else {

                            val snake = state.snake.move(state.snake.direction)
                            viewState = state.copy(snake = snake)
                        }


                        /*if (viewState.snake.checkCollision(viewState.apple)) {
                            //apple.value.respawn()
                            viewState.snake.grow()
                        } */

                        delay(300)
                    }

                    GameViewState.Loading -> {

                    }
                }

            }
        }
    }
}
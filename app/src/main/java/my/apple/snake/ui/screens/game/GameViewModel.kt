package my.apple.snake.ui.screens.game

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import my.apple.snake.base.archirecture.BaseViewModel
import my.apple.snake.data.local.datastore.DataStoreManager
import my.apple.snake.data.local.datastore.GameLevel
import my.apple.snake.data.local.datastore.UserGameResult
import my.apple.snake.domain.models.BonusItem
import my.apple.snake.domain.models.BonusStatus
import my.apple.snake.domain.models.CollisionStatus
import my.apple.snake.domain.models.Direction
import my.apple.snake.domain.models.GameLevelConfig
import my.apple.snake.domain.models.GameStatus
import my.apple.snake.domain.models.Point
import my.apple.snake.domain.repository.SnakeController
import my.apple.snake.ui.screens.game.models.GameAction
import my.apple.snake.ui.screens.game.models.GameEvent
import my.apple.snake.ui.screens.game.models.GameViewState
import kotlin.random.Random

class GameViewModel(
    private val dataStoreManager: DataStoreManager,
    private val snakeController: SnakeController
) : BaseViewModel<GameEvent, GameViewState, GameAction>(
    GameViewState.Loading
) {
    override fun obtainEvent(event: GameEvent) {
        when (val state = viewState) {
            is GameViewState.Loading -> reduce(state, event)
            is GameViewState.Display -> reduce(state, event)
        }
    }

    private fun reduce(state: GameViewState.Loading, event: GameEvent) {
        when (event) {
            is GameEvent.EnterScreen -> loadGame()

            else -> {

            }
        }
    }

    private fun reduce(state: GameViewState.Display, event: GameEvent) {
        when (event) {
            is GameEvent.ChangeSnakeDirection -> changeSnakeDirection(state, event.direction)
            else -> {

            }
        }
    }

    private fun changeSnakeDirection(state: GameViewState.Display, newValue: Direction) {
        val snake = snakeController.rotate(state.snake, newValue)

        val gameStatus = if (state.gameStatus == GameStatus.Stopped) {
            GameStatus.Playing
        } else {
            state.gameStatus
        }

        viewState = state.copy(
            snake = snake,
            gameStatus = gameStatus
        )
    }

    private fun convertTimeToString(time: Long): String {
        val allSeconds = time / 1000
        val minutes = allSeconds / 60
        val seconds = allSeconds % 60
        val minutesStr = if (minutes < 10)
            "0$minutes"
        else
            "$minutes"
        val secondsStr = if (seconds < 10)
            "0$seconds"
        else
            "$seconds"
        return "$minutesStr:$secondsStr"
    }

    private fun findEmptyGameBoardCell(state: GameViewState.Display): Point {
        var randomRow: Int
        var randomColumn: Int

        do {
            randomRow = Random.nextInt(0, state.boardSettings.rows)
            randomColumn = Random.nextInt(0, state.boardSettings.columns)
        } while (
            state.bonusItems.any { it.position.x == randomRow && it.position.y == randomColumn }
            || state.snake.body.any { it.x == randomRow && it.y == randomColumn }
            || state.blockItems.any { it.x == randomRow && it.y == randomColumn }
        )

        return Point(randomRow, randomColumn)
    }

    private fun generateBonusItem(state: GameViewState.Display): BonusItem {
        val point = findEmptyGameBoardCell(state)

        return BonusItem(point, BonusStatus.IncreaseScore)
    }

    private fun generateWallItem(state: GameViewState.Display): Point {
        val point = findEmptyGameBoardCell(state)

        return point
    }

    private fun loadGame() {
        viewModelScope.launch {
            while (true) {
                when (val state = viewState) {
                    is GameViewState.Display -> {
                        var snake = state.snake
                        val bonusItems = state.bonusItems.toMutableList()
                        val blockItems = state.blockItems.toMutableList()
                        var score = state.score
                        var time = state.time
                        var currentSnakeLives = state.currentSnakeLives
                        var gameStatus = state.gameStatus

                        if (gameStatus == GameStatus.Stopped) {
                            delay(state.gameLevelConfig.snakeSpeed)
                            continue
                        }

                        val snakeCollisionStatus =
                            snakeController.checkCollision(
                                state.snake,
                                state.boardSettings,
                                state.bonusItems,
                                state.blockItems
                            )

                        when (snakeCollisionStatus) {
                            is CollisionStatus.Board -> {
                                if (state.gameRules.damageColliderEnabled) {
                                    currentSnakeLives--
                                }
                                snake = snakeController.discard(snake, 1)
                                gameStatus = GameStatus.Stopped
                            }

                            is CollisionStatus.Snake -> {
                                if (state.gameRules.throwTailEnabled) {
                                    snake =
                                        snakeController.throwTail(snake, snakeCollisionStatus.point)
                                } else {
                                    currentSnakeLives--
                                }
                            }

                            is CollisionStatus.BonusItem -> {
                                snake = snakeController.grow(snake)
                                score++
                                bonusItems.removeAt(bonusItems.lastIndex)
                            }

                            is CollisionStatus.BlockItem -> {
                                if (state.gameRules.damageColliderEnabled) {
                                    currentSnakeLives--
                                }
                                snake = snakeController.discard(snake, 1)
                                gameStatus = GameStatus.Stopped
                            }

                            is CollisionStatus.None -> {
                                snake = snakeController.move(snake)
                            }
                        }

                        if (bonusItems.isEmpty()) {
                            val bonusItem = generateBonusItem(state)
                            bonusItems.add(bonusItem)
                        }

                        if (state.gameRules.randomWallsEnabled) {
                            if (time % state.gameLevelConfig.blockItemRespawnTime == 0L) {
                                blockItems.add(
                                    generateWallItem(state)
                                )
                            }
                        }

                        if (currentSnakeLives <= 0) {
                            viewState = state.copy(
                                gameStatus = GameStatus.Finished
                            )
                            dataStoreManager.saveGameResult(
                                UserGameResult(
                                    score = state.score,
                                    time = state.sessionTime
                                )
                            )
                            viewAction = GameAction.CloseScreen
                            break
                        }

                        time += state.gameLevelConfig.snakeSpeed

                        viewState =
                            state.copy(
                                snake = snake,
                                currentSnakeLives = currentSnakeLives,
                                bonusItems = bonusItems,
                                blockItems = blockItems,
                                score = score,
                                time = time,
                                sessionTime = convertTimeToString(time),
                                gameStatus = gameStatus
                            )

                        delay(state.gameLevelConfig.snakeSpeed)
                    }

                    GameViewState.Loading -> {
                        val appSettings = dataStoreManager.appSettings.first()

                        val gameLevelConfig = when (appSettings.gameLevel) {
                            GameLevel.Easy -> GameLevelConfig.Easy
                            GameLevel.Normal -> GameLevelConfig.Medium
                            GameLevel.Hard -> GameLevelConfig.Hard
                        }

                        viewState = GameViewState.Display(
                            snake = snakeController.spawn(
                                Point(
                                    appSettings.boardSize.rows / 2,
                                    appSettings.boardSize.columns / 2
                                )
                            ),
                            currentSnakeLives = gameLevelConfig.startSnakeLives,
                            gameLevelConfig = gameLevelConfig,
                            bonusItems = listOf(),
                            blockItems = listOf(),
                            gameStatus = GameStatus.Playing,
                            boardSettings = appSettings.boardSize,
                            gameRules = appSettings.gameRules,
                            time = 0L,
                            sessionTime = convertTimeToString(0L),
                            score = 0
                        )
                    }
                }

            }
        }
    }
}
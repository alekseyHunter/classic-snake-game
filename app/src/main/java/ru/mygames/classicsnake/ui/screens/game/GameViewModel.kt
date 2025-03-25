package ru.mygames.classicsnake.ui.screens.game

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.mygames.classicsnake.data.local.datastore.DataStoreManager
import ru.mygames.classicsnake.data.local.datastore.GameLevel
import ru.mygames.classicsnake.domain.models.BonusItem
import ru.mygames.classicsnake.domain.models.BonusType
import ru.mygames.classicsnake.domain.models.CollisionStatus
import ru.mygames.classicsnake.domain.models.Direction
import ru.mygames.classicsnake.domain.models.GameLevelConfig
import ru.mygames.classicsnake.domain.models.GameStatus
import ru.mygames.classicsnake.domain.models.Point
import ru.mygames.classicsnake.domain.models.UserGameResult
import ru.mygames.classicsnake.domain.models.toDto
import ru.mygames.classicsnake.domain.repository.SnakeController
import ru.mygames.classicsnake.ui.base.architecture.BaseViewModel
import ru.mygames.classicsnake.ui.screens.game.models.GameAction
import ru.mygames.classicsnake.ui.screens.game.models.GameEvent
import ru.mygames.classicsnake.ui.screens.game.models.GameViewState
import kotlin.random.Random

class GameViewModel(
    private val dataStoreManager: DataStoreManager,
    private val snakeController: SnakeController
): BaseViewModel<GameEvent, GameViewState, GameAction>(
    GameViewState.Loading
) {
    override fun obtainEvent(event: GameEvent) {
        when(val state = viewState) {
            is GameViewState.Loading -> reduce(state, event)
            is GameViewState.Display -> reduce(state, event)
        }
    }

    private fun reduce(state: GameViewState.Loading, event: GameEvent) {
        when(event) {
            is GameEvent.EnterScreen -> loadData()
            else -> {

            }
        }
    }

    private fun reduce(state: GameViewState.Display, event: GameEvent) {
        when(event) {
            is GameEvent.ChangeSnakeDirection -> changeSnakeDirection(state, event.newValue)
            else -> {

            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            while (true) {
                when(val state = viewState) {
                    is GameViewState.Loading -> {
                        val appSettings = dataStoreManager.appSettings.first()

                        val gameLevelConfig = when(appSettings.gameLevel) {
                            GameLevel.Easy -> GameLevelConfig.Easy
                            GameLevel.Normal -> GameLevelConfig.Medium
                            GameLevel.Hard -> GameLevelConfig.Hard
                        }

                        viewState = GameViewState.Display(
                            snake = snakeController.spawn(
                                Point(
                                    appSettings.boardSize.columns / 2,
                                    appSettings.boardSize.rows / 2
                                )
                            ),
                            currentSnakeLives =  gameLevelConfig.startSnakeLives,
                            maxSnakeLives = gameLevelConfig.maxSnakeLives,
                            bonusItems = listOf(),
                            blockItems = listOf(),
                            gameStatus = GameStatus.Playing,
                            boardSize = appSettings.boardSize,
                            time = 0L,
                            sessionTime = covertTimeToString(0L),
                            score = 0,
                            gameRules = appSettings.gameRules,
                            gameLevelConfig = gameLevelConfig
                        )
                    }

                    is GameViewState.Display -> {
                        val bonusItems = state.bonusItems.toMutableList()
                        val blockItems = state.blockItems.toMutableList()
                        var snake = state.snake
                        var currentLivesSnake = state.currentSnakeLives
                        var gameStatus = state.gameStatus
                        var score = state.score
                        var time = state.time

                        if(state.gameStatus == GameStatus.Stopped) {
                            delay(state.gameLevelConfig.snakeSpeed)
                            continue
                        }

                        val snakeCollisionStatus = snakeController.checkCollision(
                            snake = state.snake,
                            boardSize = state.boardSize,
                            bonusItems = bonusItems,
                            blockItems = blockItems
                        )

                        when(snakeCollisionStatus) {
                            is CollisionStatus.Board -> {
                                if(state.gameRules.damageColliderEnabled) {
                                    currentLivesSnake--
                                }
                                snake = snakeController.discard(snake, 1)
                                gameStatus = GameStatus.Stopped
                            }
                            is CollisionStatus.Snake -> {
                                if(state.gameRules.throwTailEnabled) {
                                    snake = snakeController.throwTail(snake, snakeCollisionStatus.point)
                                } else {
                                    currentLivesSnake--
                                }
                            }
                            is CollisionStatus.BonusItem -> {
                                snake = snakeController.grow(snake)
                                score++
                                bonusItems.removeAt(bonusItems.lastIndex)
                            }
                            is CollisionStatus.BlockItem -> {
                                if(state.gameRules.damageColliderEnabled) {
                                    currentLivesSnake--
                                }
                                snake = snakeController.discard(snake, 1)
                                gameStatus = GameStatus.Stopped
                            }
                            is CollisionStatus.None -> {
                                snake = snakeController.move(snake)
                            }
                        }

                        if(bonusItems.isEmpty()) {
                            val bonusItem = generateBonusItem(state)
                            bonusItems.add(bonusItem)
                        }

                        if(state.gameRules.randomWallsEnabled) {
                            if(state.time % state.gameLevelConfig.blockItemRespawnTime == 0L) {
                                val blockItem = generateBlockItem(state)
                                blockItems.add(
                                    blockItem
                                )
                            }
                        }

                        if(currentLivesSnake <= 0) {
                            viewState = state.copy(
                                gameStatus = GameStatus.Finished
                            )
                            dataStoreManager.saveGameResult(
                                UserGameResult(
                                    score = score,
                                    time = state.sessionTime
                                ).toDto()
                            )
                            sideEffect(GameAction.CloseScreen)
                            break
                        }

                        time += state.gameLevelConfig.snakeSpeed

                        viewState = state.copy(
                            snake = snake,
                            currentSnakeLives = currentLivesSnake,
                            bonusItems = bonusItems,
                            blockItems = blockItems,
                            score = score,
                            time = time,
                            sessionTime = covertTimeToString(time),
                            gameStatus = gameStatus
                        )

                        delay(state.gameLevelConfig.snakeSpeed)
                    }
                }
            }
        }
    }


    private fun changeSnakeDirection(state: GameViewState.Display, newValue: Direction) {
        viewModelScope.launch {
            val snake = snakeController.rotate(state.snake, newValue)

            val gameStatus = if(state.gameStatus == GameStatus.Stopped) {
                GameStatus.Playing
            } else {
                state.gameStatus
            }

            viewState = state.copy(
                snake = snake,
                gameStatus = gameStatus
            )
        }
    }

    private fun covertTimeToString(time: Long): String {
        val allSeconds = time / 1000
        val minutes = allSeconds / 60
        val seconds = allSeconds % 60

        val convertedMinutes = if (minutes < 10)
            "0$minutes"
        else
            "$minutes"

        val convertedSeconds = if (seconds < 10)
            "0$seconds"
        else
            "$seconds"

        return "${convertedMinutes}:${convertedSeconds}"
    }

    private fun findEmptyGameBoardCell(state: GameViewState.Display): Point {
        var randomRow: Int
        var randomColumn: Int

        do {
            randomRow = Random.nextInt(0, state.boardSize.rows)
            randomColumn = Random.nextInt(0, state.boardSize.columns)
        } while (
            state.bonusItems.any { it.position.x == randomColumn && it.position.y == randomRow } ||
            state.snake.body.any { it.x == randomColumn && it.y == randomRow } ||
            state.blockItems.any { it.x == randomColumn && it.y == randomRow }
        )

        return Point(randomColumn, randomRow)
    }

    private fun generateBonusItem(state: GameViewState.Display): BonusItem {
        val point = findEmptyGameBoardCell(state)

        return BonusItem(point, BonusType.IncreaseScore)
    }

    private fun generateBlockItem(state: GameViewState.Display): Point {
        val point = findEmptyGameBoardCell(state)

        return point
    }
}
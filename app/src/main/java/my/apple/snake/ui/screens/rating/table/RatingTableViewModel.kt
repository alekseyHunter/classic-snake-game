package my.apple.snake.ui.screens.rating.table

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import my.apple.snake.base.archirecture.BaseViewModel
import my.apple.snake.data.local.datastore.DataStoreManager
import my.apple.snake.data.local.datastore.GameLevel
import my.apple.snake.ui.screens.rating.table.models.RatingTableAction
import my.apple.snake.ui.screens.rating.table.models.RatingTableEvent
import my.apple.snake.ui.screens.rating.table.models.RatingTableViewState

class RatingTableViewModel(
    private val dataStoreManager: DataStoreManager
) :
    BaseViewModel<RatingTableEvent, RatingTableViewState, RatingTableAction>(
        RatingTableViewState.Loading
    ) {
    override fun obtainEvent(event: RatingTableEvent) {
        when (val state = viewState) {
            is RatingTableViewState.Loading -> reduce(state, event)
            is RatingTableViewState.Display -> reduce(state, event)
        }
    }

    private fun reduce(state: RatingTableViewState.Loading, event: RatingTableEvent) {
        when (event) {
            is RatingTableEvent.EnterScreen -> loadData()
            else -> {

            }
        }
    }

    private fun reduce(state: RatingTableViewState.Display, event: RatingTableEvent) {
        when (event) {
            is RatingTableEvent.ChangeDifficultyLevel -> changeDifficultyLevel(event.number)
            else -> {

            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val settings = dataStoreManager.appSettings.first()

            val results = settings.gameResults.getOrDefault(settings.gameLevel, emptyList())
            viewState = RatingTableViewState.Display(settings.gameLevel, results)
        }
    }

    private fun changeDifficultyLevel(newValue: Int) {
        viewModelScope.launch {
            val gameLevel = GameLevel.entries[newValue]

            val settings = dataStoreManager.appSettings.first()
            val results = settings.gameResults.getOrDefault(gameLevel, emptyList())
            viewState = RatingTableViewState.Display(gameLevel, results)
        }
    }
}
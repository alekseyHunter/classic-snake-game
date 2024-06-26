package my.apple.snake.domain.models

data class BonusItem(
    val position: Point,
    val status: BonusStatus
)

sealed class BonusStatus {
    data object ExtraLife : BonusStatus()
    data object IncreaseSpeed : BonusStatus()
    data object IncreaseScore: BonusStatus()
}
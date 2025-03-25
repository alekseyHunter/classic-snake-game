package ru.mygames.classicsnake.domain.models

data class BonusItem(
    val position: Point,
    val type: BonusType
)

sealed interface BonusType {
    data object ExtraLife: BonusType
    data object IncreaseScore: BonusType
}

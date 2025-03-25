package ru.mygames.classicsnake.domain.models

enum class Direction(val dx: Int, val dy: Int) {
    NONE(0,0),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1)
}
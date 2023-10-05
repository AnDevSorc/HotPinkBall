package com.fberry.hotpinkball.screens.action

class Board(val width: Int, val height: Int) {
    val pins = listOf(
        Point(1, 13),
        Point(3, 13),
        Point(5, 13),
        Point(7, 13),
        Point(9, 13),
        Point(11, 13),
        Point(2, 11),
        Point(4, 11),
        Point(6, 11),
        Point(8, 11),
        Point(10, 11),
        Point(3, 9),
        Point(5, 9),
        Point(7, 9),
        Point(9, 9),
        Point(4, 7),
        Point(6, 7),
        Point(8, 7),
        Point(5, 5),
        Point(7, 5),
        Point(6, 3),
    )
    val baskets = listOf(
        Point(0, 14),
        Point(2, 14),
        Point(4, 14),
        Point(6, 14),
        Point(8, 14),
        Point(10, 14),
        Point(12, 14)
    )

    fun isPinAt(i: Int, j: Int): Boolean {
        return pins.any { it.x == j && it.y == i }
    }

    fun isBasketAt(i: Int, j: Int): Boolean {
        return baskets.any { it.x == j && it.y == i }
    }

    fun getNextMove(current: Point): Point {
        if (pins.contains(Point(current.x, current.y + 1))) {
            return if (Math.random() > 0.5) Point(
                current.x + 1,
                current.y
            ) else Point(current.x - 1, current.y)
        }
        return Point(current.x, current.y + 1)
    }
}
package br.com.gabryel.pong.game

import br.com.gabryel.pong.game.Direction.DOWN
import br.com.gabryel.pong.game.Direction.UP

data class Paddle(val width: Float, val height: Float, val center: Vector, val speed: Float) {

    val halfWidth = width / 2

    val halfHeight = height / 2

    fun move(direction: Direction): Paddle {
        val newCenter = when (direction) {
            UP -> center.y - speed
            DOWN -> center.y + speed
            else -> center.y
        }

        return copy(center = center.copy(y = newCenter.coerceIn(halfHeight, 1 - halfHeight)))
    }
}
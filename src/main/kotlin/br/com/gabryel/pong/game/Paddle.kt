package br.com.gabryel.pong.game

import br.com.gabryel.pong.game.Direction.DOWN
import br.com.gabryel.pong.game.Direction.UP

data class Paddle(
    override val width: Float,
    override val height: Float,
    override val position: Vector,
    val speed: Float
): Positioned {

    val halfHeight = height / 2

    fun move(direction: Direction): Paddle {
        val newCenter = when (direction) {
            UP -> position.y - speed
            DOWN -> position.y + speed
            else -> position.y
        }

        return copy(position = position.copy(y = newCenter.coerceIn(halfHeight, 1 - halfHeight)))
    }
}
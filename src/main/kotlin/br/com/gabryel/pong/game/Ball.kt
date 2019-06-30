package br.com.gabryel.pong.game

data class Ball(override val position: Vector, val speed: Vector, val diameter: Float): Positioned {

    override val height = diameter

    override val width = diameter

    fun move(): Ball {
        val newPosition = position + speed

        return copy(position = newPosition)
    }
}
package br.com.gabryel.pong.game

data class Ball(val position: Vector, val speed: Vector) {

    fun move(): Ball {
        val newPosition = position + speed

        return copy(position = newPosition)
    }
}
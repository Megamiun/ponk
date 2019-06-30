package br.com.gabryel.pong.game

interface Positioned {

    val position: Vector

    val height: Float

    val width: Float

    fun y() = position.y

    fun x() = position.x

    fun left() = position.x - (width / 2)

    fun right() = position.x + (width / 2)

    fun top() = position.y + (height / 2)

    fun bottom() = position.y - (height / 2)
}
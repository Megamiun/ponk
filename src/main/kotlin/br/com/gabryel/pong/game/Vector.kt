package br.com.gabryel.pong.game

data class Vector(val x: Float, val y: Float) {

    operator fun plus(other: Vector) = (x + other.x) by (y + other.y)
}

infix fun Number.by(y: Number) = Vector(toFloat(), y.toFloat())
package br.com.gabryel.pong.game

data class Vector(val x: Float, val y: Float) {

    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y)
}
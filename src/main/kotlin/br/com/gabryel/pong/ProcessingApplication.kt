package br.com.gabryel.pong

import br.com.gabryel.pong.game.Ball
import br.com.gabryel.pong.game.BallChasingStrategy
import br.com.gabryel.pong.game.Direction
import br.com.gabryel.pong.game.Direction.NONE
import br.com.gabryel.pong.game.Paddle
import br.com.gabryel.pong.game.Strategy
import br.com.gabryel.pong.game.Vector
import br.com.gabryel.pong.game.World
import br.com.gabryel.pong.game.by
import processing.core.PApplet
import java.awt.event.KeyEvent.VK_DOWN
import java.awt.event.KeyEvent.VK_SPACE
import java.awt.event.KeyEvent.VK_UP
import kotlin.concurrent.fixedRateTimer

class Pong(private var currentWorld: World): PApplet() {

    private val scale = 800

    private val scaleFloat = scale.toFloat()

    private var currentDirection: Direction = NONE

    override fun settings() {
        size(scale, scale)
    }

    override fun setup() {
        frameRate(60F)

        fixedRateTimer(period = 30) {
            if (looping) {
                currentWorld = currentWorld
                    .move(currentDirection)
                    .moveOpponent()
                    .moveBall()
            }
        }
    }

    override fun draw() {
        clear()
        currentWorld.render()
    }

    override fun keyPressed() {
        when (keyCode) {
            VK_DOWN -> currentDirection = Direction.DOWN
            VK_UP -> currentDirection = Direction.UP
            VK_SPACE -> togglePause()
        }
    }

    override fun keyReleased() {
        currentDirection = NONE
    }

    private fun togglePause() = if (looping) noLoop() else loop()

    private fun World.render() {
        rectMode(CENTER)
        fill(255)

        ball.render()
        playerPaddle.render()
        opponentPaddle.render()
    }

    private fun Ball.render() {
        ellipse(
            position.x * scaleFloat,
            position.y * scaleFloat,
            diameter * scaleFloat,
            diameter * scaleFloat
        )
    }

    private fun Paddle.render() {
        rect(center.x * scaleFloat, center.y * scaleFloat, width * scaleFloat, height * scaleFloat)
    }
}

fun main() {
    val pong = Pong(initialize(opponentStrategy = BallChasingStrategy()))

    PApplet.runSketch(arrayOf("br.com.gabryel.pong.Pong"), pong)
}

fun initialize(
    paddleWidth: Float = 0.015F,
    paddleHeight: Float = 0.15F,
    paddleSpeed: Float = 0.01F,
    opponentStrategy: Strategy
): World {
    val playerPaddle = Paddle(paddleWidth, paddleHeight, Vector(1 - paddleWidth / 2, 0.5F), paddleSpeed)
    val opponentPaddle = playerPaddle.copy(center = (paddleWidth / 2) by 0.5)
    val ball = Ball(0.5 by 0.5, 0.005 by 0.005, 0.02F)

    return World(ball, playerPaddle, opponentPaddle, opponentStrategy)
}
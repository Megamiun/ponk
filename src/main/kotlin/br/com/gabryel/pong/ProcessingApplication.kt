package br.com.gabryel.pong

import br.com.gabryel.pong.game.*
import processing.core.PApplet
import java.awt.event.KeyEvent
import kotlin.concurrent.fixedRateTimer

class Pong(private var currentWorld: World): PApplet() {

    private val scale = 800

    private val scaleFloat = scale.toFloat()

    private var currentDirection: Direction = Direction.NONE

    override fun settings() {
        size(scale, scale)
    }

    override fun setup() {
        frameRate(60F)

        fixedRateTimer(period = 60) {
            if (looping) {
                currentWorld = currentWorld
                    .move(currentDirection)
                    .moveOpponent()
            }
        }
    }

    override fun draw() {
        clear()
        currentWorld.render()
    }

    override fun keyPressed() {
        when (keyCode) {
            KeyEvent.VK_DOWN -> currentDirection = Direction.DOWN
            KeyEvent.VK_UP -> currentDirection = Direction.UP
            KeyEvent.VK_SPACE -> togglePause()
        }
    }

    override fun keyReleased() {
        currentDirection = Direction.NONE
    }

    private fun togglePause() = if (looping) noLoop() else loop()

    private fun World.render() {
        fill(255)

        ball.render()
        playerPaddle.render()
        opponentPaddle.render()
    }

    private fun Ball.render() {
        rect(position.x * scaleFloat, position.y * scaleFloat, 1F, 1F)
    }

    private fun Paddle.render() {
        rect(
            (center.x - halfWidth) * scaleFloat,
            (center.y - halfHeight) * scaleFloat,
            width * scaleFloat,
            height * scaleFloat
        )
    }

}

fun main() {
    val pong = Pong(initialize(opponentStrategy = BallChasingStrategy()))

    PApplet.runSketch(arrayOf("br.com.gabryel.pong.Pong"), pong)
}

fun initialize(
    paddleWidth: Float = 0.02F,
    paddleHeight: Float = 0.15F,
    paddleSpeed: Float = 0.05F,
    opponentStrategy: Strategy
): World {
    val playerPaddle = Paddle(paddleWidth, paddleHeight, Vector(1 - paddleWidth / 2, 0.5F), paddleSpeed)
    val opponentPaddle = playerPaddle.copy(center = Vector(paddleWidth / 2, 0.5F))
    val ball = Ball(Vector(0.5F, 0.5F), Vector(0.01F, 0.01F))

    return World(ball, playerPaddle, opponentPaddle, opponentStrategy)
}
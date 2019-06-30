package br.com.gabryel.pong.game

import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

data class World(
    val ball: Ball,
    val playerPaddle: Paddle,
    val opponentPaddle: Paddle,
    val opponentStrategy: Strategy
) {

    fun move(direction: Direction) = copy(playerPaddle = playerPaddle.move(direction))

    fun moveOpponent(): World {
        val direction = opponentStrategy.act(this)

        return copy(opponentPaddle = opponentPaddle.move(direction))
    }

    fun moveBall(): World {
        val newBall = ball.move().correctBoundaries()

        return copy(ball = newBall)
    }

    private fun Ball.correctBoundaries() =
        checkUpperBound()
            .checkLowerBound()
            .checkPaddle(playerPaddle)
            .checkPaddle(opponentPaddle)

    private fun Ball.checkUpperBound() = if (top() >= 1) copy(speed = speed.invertY()) else this

    private fun Ball.checkLowerBound() = if (bottom() <= 0) copy(speed = speed.invertY()) else this

    private fun Ball.checkPaddle(paddle: Paddle): Ball {
        val deltaX = x() - max(paddle.left(), min(x(), paddle.right()))
        val deltaY = y() - max(paddle.bottom(), min(y(), paddle.top()))

        if (deltaX.pow(2) + deltaY.pow(2) > diameter.pow(2)) {
            return this
        }

        return ball.copy(speed = speed.invertX())
    }
}

package br.com.gabryel.pong.game

import br.com.gabryel.pong.game.Direction.*

interface Strategy {
    fun act(world: World): Direction
}

class WaitingStrategy: Strategy {
    override fun act(world: World) = NONE
}

class BallChasingStrategy: Strategy {
    override fun act(world: World): Direction {
        val ball = world.ball.position.y
        val paddleY = world.opponentPaddle.center.y
        val halfHeight = world.opponentPaddle.halfHeight

        return when {
            paddleY - halfHeight > ball -> UP
            paddleY + halfHeight < ball -> DOWN
            else -> NONE
        }
    }
}
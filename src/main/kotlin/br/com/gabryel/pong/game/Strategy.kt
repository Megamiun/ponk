package br.com.gabryel.pong.game

interface Strategy {
    fun act(world: World): Direction
}

class WaitingStrategy: Strategy {
    override fun act(world: World) = Direction.NONE
}

class BallChasingStrategy: Strategy {
    override fun act(world: World): Direction {
        return if (world.opponentPaddle.center.y < world.ball.position.y) {
            Direction.DOWN
        } else if (world.opponentPaddle.center.y > world.ball.position.y) {
            Direction.UP
        } else {
            Direction.NONE
        }
    }
}
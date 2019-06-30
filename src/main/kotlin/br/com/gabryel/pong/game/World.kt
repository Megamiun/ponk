package br.com.gabryel.pong.game

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
        val newBall = ball.move()

        return copy(ball = newBall)
    }
}

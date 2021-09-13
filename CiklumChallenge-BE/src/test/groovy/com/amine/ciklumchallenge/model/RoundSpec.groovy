package com.amine.ciklumchallenge.model

import spock.lang.Specification
import spock.lang.Unroll

import static com.amine.ciklumchallenge.model.Move.*
import static com.amine.ciklumchallenge.model.RoundResult.*

class RoundSpec extends Specification {

    Round round = new Round()

    @Unroll("When we play a round with #move1 Vs #move2, the round result is #result")
    def "When we evaluate a round of two movement, the result is expected one"() {

        given:
        round.movePlayerOne = move1
        round.movePlayerTwo = move2

        when:
        round.evaluateRound()

        then:
        round.getRoundResult() == result

        where:
        move1    | move2    | result
        ROCK     | SCISSORS | PLAYER_ONE_WINS
        ROCK     | ROCK     | DRAW
        ROCK     | PAPER    | PLAYER_TWO_WINS
        SCISSORS | SCISSORS | DRAW
        SCISSORS | ROCK     | PLAYER_TWO_WINS
        SCISSORS | PAPER    | PLAYER_ONE_WINS
        PAPER    | SCISSORS | PLAYER_TWO_WINS
        PAPER    | ROCK     | PLAYER_ONE_WINS
        PAPER    | PAPER    | DRAW

    }

    def "When we play a round, player's two movement is Rock"() {

        when:
        round.playRound();

        then:
        round.getMovePlayerTwo() == ROCK
    }
}
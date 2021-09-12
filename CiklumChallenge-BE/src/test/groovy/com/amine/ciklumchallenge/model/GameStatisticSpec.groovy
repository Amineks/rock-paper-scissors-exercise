package com.amine.ciklumchallenge.model

import spock.lang.Specification
import spock.lang.Unroll

import static com.amine.ciklumchallenge.model.RoundResult.*

class GameStatisticSpec extends Specification {

    @Unroll("When the result of a round is #roundResult, the game statistics will be updated as follow: the total played: #totalPlayed, the totalDraws: #totalDraws, the total won by player1: #totalPlayer1, the total won by player2: #totalPlayer2 ")
    def "Checking the correct updated of Game Statistics"() {
        given:
        GameStatistic gameStatistic = new GameStatistic()
        gameStatistic.updateStatistics(roundResult)

        expect:
        with(gameStatistic) {
            getTotalDraws().intValue() == totalDraws
            getTotalPlayed().intValue() == totalPlayed
            getTotalFirstPlayerWins().intValue() == totalPlayer1
            getTotalSecondPlayerWins().intValue() == totalPlayer2
        }

        where:
        roundResult     | totalPlayed | totalDraws | totalPlayer1 | totalPlayer2
        DRAW            | 1           | 1          | 0            | 0
        PLAYER_ONE_WINS | 1           | 0          | 1            | 0
        PLAYER_TWO_WINS | 1           | 0          | 0            | 1
    }
}

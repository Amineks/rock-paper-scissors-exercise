package com.amine.ciklumchallenge.model

import spock.lang.Specification
import spock.lang.Unroll

import static com.amine.ciklumchallenge.model.Move.*

class MoveSpec extends Specification {

    @Unroll("When it's #move1 Vs #move2, the result is #result")
    def "Checking the correct result of all possible combinations"() {

        expect:
        move1.isWinner(move2) == result

        where:
        move1    | move2    | result
        ROCK     | ROCK     | false
        ROCK     | PAPER    | false
        SCISSORS | SCISSORS | false
        SCISSORS | ROCK     | false
        SCISSORS | PAPER    | true
        PAPER    | SCISSORS | false
        PAPER    | ROCK     | true
        PAPER    | PAPER    | false
        ROCK     | SCISSORS | true
    }

    def "When asked for a Rock movement, the returned movement is Rock"() {

        when:
        def result = getRockMove()

        then:
        result == ROCK
    }

    def "When asked for a random movement, the returned movement can be rock, paper or scissors"() {

        when:
        def result = getRockMove()

        then:
        result == ROCK || result == SCISSORS || result == PAPER
    }
}
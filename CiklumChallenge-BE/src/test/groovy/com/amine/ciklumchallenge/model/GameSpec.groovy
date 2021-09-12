package com.amine.ciklumchallenge.model

import spock.lang.Specification

class GameSpec extends Specification {


    def "When we play a round, the round is added to the game "() {
        given:
        def game = new Game()
        def rounds = game.getRounds()

        expect:
        rounds == null

        when:
        game.addRoundToGame(Mock(Round))

        then:
        game.getRounds() != null
        game.getRounds().size() == 1
    }


}
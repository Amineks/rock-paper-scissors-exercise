package com.amine.ciklumchallenge.service

import spock.lang.Specification

class GameServiceSpec extends Specification {

    IGameService gameService = new GameService();

    def "When we start a game, a game session is initialized for that session ID and without any rounds "() {
        given:
        def sessionId = "123"

        when:
        def game = gameService.startGame(sessionId)

        then:
        game != null
        game.getRounds() == null
    }

    def "When we ask for all the games, w obtain all the already started ones"() {
        given:
        def sessionId_1 = "1234"
        def sessionId_2 = "5678"
        gameService.startGame(sessionId_1)
        gameService.startGame(sessionId_2)

        when:
        def games = gameService.getAllGames()

        then:
        games.size() == 2
        games.get(sessionId_1) != null
        games.get(sessionId_2) != null
    }

    def "When we start a game with the same session ID as an already started game, no new games is created"() {
        given:
        def sessionId = "1234"
        gameService.startGame(sessionId)
        gameService.startGame(sessionId)

        when:
        def games = gameService.getAllGames()

        then:
        games.size() == 1
        games.get(sessionId) != null

    }

    def "When we play a round related to a game already started, the round is added to the existing rounds"() {
        given:
        String sessionId = "1234"
        gameService.startGame(sessionId)

        when:
        gameService.playRound(sessionId)
        def game  = gameService.playRound(sessionId)

        then:
        game.isPresent()
        game.get().rounds != null
        game.get().rounds.size() == 2
    }

    def "When two different players are playing, the game summary is considering the rounds of both them"() {
        given:
        def sessionId_1 = "1234"
        def sessionId_2 = "5678"
        gameService.startGame(sessionId_1)
        gameService.startGame(sessionId_2)

        when:
        gameService.playRound(sessionId_1)
        gameService.playRound(sessionId_1)
        gameService.playRound(sessionId_1)
        gameService.playRound(sessionId_2)
        gameService.playRound(sessionId_2)

        then:
        gameService.getGameSummary().getTotalPlayed().intValue() == 5
    }

    def "When we play a round without a started game, then an empty object is returned"() {
        given:
        def sessionId = "1234"

        when:
        def optionalGame = gameService.playRound(sessionId)

        then:
        !optionalGame.isPresent()
    }

}

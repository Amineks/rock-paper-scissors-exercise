package com.amine.ciklumchallenge.controller

import com.amine.ciklumchallenge.model.Game
import com.amine.ciklumchallenge.model.GameStatistic
import com.amine.ciklumchallenge.model.Round
import com.amine.ciklumchallenge.model.RoundResult
import com.amine.ciklumchallenge.service.GameService
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import spock.lang.Specification

import javax.servlet.http.HttpSession

class GameControllerSpec extends Specification {

    static final String SESSION_ID_1 = "123"
    static final String SESSION_ID_2 = "1234"
    static final String EMPTY_SESSION_ID = ""

    def "When we call start a game with a session ID, a new game is started"() {
        given: "A request session info"
        def mockHttpSession = Mock(HttpSession) {
            getId() >> SESSION_ID_1
        }

        and: "A game service"
        def mockGameService = Mock(GameService) {
            startGame(SESSION_ID_1) >> new Game()
        }

        when: "Game is started"
        def gameResponseEntity = new GameController(mockGameService).startGame(mockHttpSession)

        then: "A game is created with no rounds yet"
        with(gameResponseEntity) {
            body.getRounds() == null
            getStatusCode() == HttpStatus.OK
        }
    }

    def "When we call getAllGames, all the already played games are returned"() {
        given: "Two request session info"

        def mockGameService = Mock(GameService) {
            getAllGames() >> "build two games"(SESSION_ID_1, SESSION_ID_2)
        }

        when: "We get all games"
        def games = new GameController(mockGameService).getAllGames()

        then: "We have two already added games with the OK status"
        with(games) {
            body != null
            body.size() == 2
            getStatusCode() == HttpStatus.OK
        }
    }

    def "When we ask for a game by its session id, the game is returned"() {
        given: "A request session info"
        def mockHttpSession = Mock(HttpSession) {
            getId() >> SESSION_ID_1
        }

        def mockGameService = Mock(GameService) {
            obtainGame(SESSION_ID_1) >> Optional.of(new Game())
        }

        when: "We call obtain game"
        def game = new GameController(mockGameService).obtainGame(mockHttpSession)

        then: "We obtain the game with the OK status"
        with(game) {
            body != null
            getStatusCode() == HttpStatus.OK
        }
    }

    def "Should play a round"() {
        given: "A request session info"
        def mockHttpSession = Mock(HttpSession) {
            getId() >> SESSION_ID_1
        }

        and: "A game service"
        def mockGameService = Mock(GameService) {
            playRound(SESSION_ID_1) >> "build optional game with rounds"()
        }

        when: "A round is played"
        def gameResponseEntity = new GameController(mockGameService).playRound(mockHttpSession)

        then: "A game with a not empty round list is returned with OK status"
        with(gameResponseEntity) {
            body.getRounds() != null
            getStatusCode() == HttpStatus.OK
        }
    }

    def "When we asked for a game summary, the game statistics are provided"() {
        given: "A game service"
        def mockGameService = Mock(GameService) {
            getGameSummary() >> "build game summary"()
        }

        when: "We ask for the game summary"
        def gameSummaryResponseEntity = new GameController(mockGameService).getGameSummary()

        then: "Summary is provided with the right values"
        with(gameSummaryResponseEntity) {
            body.totalPlayed.intValue() == 1
            body.totalFirstPlayerWins.intValue() == 1
            body.totalSecondPlayerWins.intValue() == 0
            body.totalDraws.intValue() == 0
        }
    }

    def "When we call start a game with a not valid session id, an exception is thrown"() {
        given: "A request session info"
        def mockHttpSession = Mock(HttpSession) {
            getId() >> EMPTY_SESSION_ID
        }

        and: "A game service"
        def mockGameService = Mock(GameService) {
            startGame(EMPTY_SESSION_ID) >> new Game()
        }

        when: "Game is started"
        new GameController(mockGameService).startGame(mockHttpSession)

        then: "An exception is thrown"
        thrown ResponseStatusException.class
    }

    def "When we ask for a game which session id does not exist previously, then an exception is thrown"() {
        given: "A request session info"
        def mockHttpSession = Mock(HttpSession) {
            getId() >> SESSION_ID_1
        }

        def mockGameService = Mock(GameService) {
            obtainGame(SESSION_ID_1) >> Optional.empty()
        }

        when: "We call obtain game"
        def game = new GameController(mockGameService).obtainGame(mockHttpSession)

        then: "An exception is thrown"
        game == null
        thrown ResponseStatusException.class
    }

    def "build game summary"() {
        def gameStatistic = new GameStatistic()
        def roundResult = RoundResult.PLAYER_ONE_WINS
        gameStatistic.updateStatistics(roundResult)
        gameStatistic
    }

    def "build optional game with rounds"() {
        def game = new Game()
        def round = new Round()
        round.playRound()
        game.addRoundToGame(round)
        Optional.of(game)
    }

    def "build two games"(sessionId_1, sessionId_2) {
        def Map<String, Game> gamesMap = new HashMap<>()
        gamesMap.put(sessionId_1, new Game())
        gamesMap.put(sessionId_2, new Game())
        gamesMap
    }

}
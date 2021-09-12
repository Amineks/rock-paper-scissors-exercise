package com.amine.ciklumchallenge.controller;

import com.amine.ciklumchallenge.model.Game;
import com.amine.ciklumchallenge.model.GameStatistic;
import com.amine.ciklumchallenge.service.IGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@RequestMapping(value = "/api/game", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@Slf4j
public class GameController {

    private final IGameService gameService;

    @Autowired
    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/start-game")
    public ResponseEntity<Game> startGame(HttpSession session) {
        validateSession(session);
        final String sessionId = session.getId();
        log.info("A new game for the session Id {} could be started", sessionId);
        return ResponseEntity.ok(gameService.startGame(sessionId));
    }

    @GetMapping("/all-games")
    public ResponseEntity<Map<String, Game>> getAllGames() {
        log.info("All games information will be retrieved");
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/game-summary")
    public ResponseEntity<GameStatistic> getGameSummary() {
        log.info("Game summary information will be retrieved");
        return ResponseEntity.ok(gameService.getGameSummary());
    }

    @GetMapping("/play-round")
    public ResponseEntity<Game> playRound(HttpSession session) {
        validateSession(session);
        final String sessionId = session.getId();
        log.info("A new round will be played for the player with session id {}", sessionId);
        final Optional<Game> game = gameService.playRound(sessionId);
        return ResponseEntity.ok(game.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No active game were found for this player!!")));
    }

    @GetMapping("/obtain-game")
    public ResponseEntity<Game> obtainGame(HttpSession session) {
        validateSession(session);
        final String sessionId = session.getId();
        log.info("The game with the session Id {} will be obtained", sessionId);
        final Optional<Game> game = gameService.obtainGame(sessionId);
        return ResponseEntity.ok(game.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No active game were found for this player!!")));
    }

    @GetMapping("/")
    public String showWelcome() {
        return "Hello!! You are welcome to play the rock-paper-scissor game using API Rest. Please, run the client project and start enjoying the game";
    }

    private void validateSession(HttpSession session) {
        if (session != null && isEmpty(session.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request: Invalid session parameter!!");
        }
    }

}

package com.amine.ciklumchallenge.service;

import com.amine.ciklumchallenge.exception.GameException;
import com.amine.ciklumchallenge.model.Game;
import com.amine.ciklumchallenge.model.GameStatistic;
import com.amine.ciklumchallenge.model.Round;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class GameService implements IGameService {

    private final Map<String, Game> gamesMap = new ConcurrentHashMap<>();
    private final GameStatistic gameStatistic = new GameStatistic();

    @Override
    public Game startGame(String sessionId) {
        try {
            Game game = new Game();
            gamesMap.put(sessionId, game);
            log.info("A new game had been restarted for the session Id {}", sessionId);
            return game;
        } catch (Exception ex) {
            log.error("Game could not be started for the session Id {}", sessionId);
            throw new GameException("Unknown error had happened while trying to start a game!!", ex);
        }
    }

    @Override
    public Map<String, Game> getAllGames() {
        return gamesMap;
    }

    @Override
    public GameStatistic getGameSummary() {
        return gameStatistic;
    }

    @Override
    public Optional<Game> playRound(String sessionId) {
        try {
            if (!gamesMap.containsKey(sessionId)) {
                log.error("There is no active game for the session Id {}", sessionId);
                return Optional.empty();
            }
            Game game = gamesMap.get(sessionId);
            Round round = new Round();
            round.playRound();
            game.addRoundToGame(round);
            gameStatistic.updateStatistics(round.getRoundResult());
            gamesMap.put(sessionId, game);
            log.info("The played round is {}", round);
            return Optional.of(game);
        } catch (Exception ex) {
            log.error("Unknown error had happened while playing a game round for the session Id {}", sessionId);
            throw new GameException("Unknown error had happened while playing a game round!!", ex);
        }
    }

    @Override
    public Optional<Game> obtainGame(String sessionId) {
        if (!gamesMap.containsKey(sessionId)) {
            log.error("There is no active game for the session Id {}", sessionId);
            return Optional.empty();
        }
        return Optional.of(gamesMap.get(sessionId));
    }

}
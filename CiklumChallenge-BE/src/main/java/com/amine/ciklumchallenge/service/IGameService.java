package com.amine.ciklumchallenge.service;

import com.amine.ciklumchallenge.model.Game;
import com.amine.ciklumchallenge.model.GameStatistic;

import java.util.Map;
import java.util.Optional;

public interface IGameService {

    Game startGame(String sessionId);

    Map<String, Game> getAllGames();

    GameStatistic getGameSummary();

    Optional<Game> playRound(String sessionId);

    Optional<Game> obtainGame(String sessionId);
}

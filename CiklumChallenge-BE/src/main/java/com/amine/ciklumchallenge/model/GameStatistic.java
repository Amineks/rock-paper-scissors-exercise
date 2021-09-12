package com.amine.ciklumchallenge.model;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
public class GameStatistic implements Serializable {

    private final AtomicInteger totalPlayed = new AtomicInteger(0);
    private final AtomicInteger totalFirstPlayerWins = new AtomicInteger(0);
    private final AtomicInteger totalSecondPlayerWins = new AtomicInteger(0);
    private final AtomicInteger totalDraws = new AtomicInteger(0);

    public void updateStatistics(RoundResult roundResult) {
        totalPlayed.incrementAndGet();
        switch (roundResult) {
            case PLAYER_ONE_WINS:
                totalFirstPlayerWins.incrementAndGet();
                break;
            case PLAYER_TWO_WINS:
                totalSecondPlayerWins.incrementAndGet();
                break;
            case DRAW:
                totalDraws.incrementAndGet();
                break;
        }
    }

}

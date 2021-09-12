package com.amine.ciklumchallenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@NoArgsConstructor
public class Round implements Serializable {

    private Move movePlayerOne;
    private Move movePlayerTwo;
    private RoundResult roundResult;

    public void playRound() {
        movePlayerOne = Move.getNewRandomMove();
        movePlayerTwo = Move.getRockMove();
        evaluateRound();
    }

    private void evaluateRound() {
        roundResult = RoundResult.DRAW;
        if (movePlayerOne.isWinner(movePlayerTwo)) {
            roundResult = RoundResult.PLAYER_ONE_WINS;
        } else if (movePlayerTwo.isWinner(movePlayerOne)) {
            roundResult = RoundResult.PLAYER_TWO_WINS;
        }
    }
}
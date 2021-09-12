package com.amine.ciklumchallenge.model;

import java.util.Random;

public enum Move {
    ROCK {
        @Override
        public boolean isWinner(Move move) {
            return (SCISSORS == move);
        }
    },
    PAPER {
        @Override
        public boolean isWinner(Move move) {
            return (ROCK == move);
        }
    },
    SCISSORS {
        @Override
        public boolean isWinner(Move move) {
            return (PAPER == move);
        }
    };

    public abstract boolean isWinner(Move move);

    public static Move getRockMove() {
        return Move.ROCK;
    }

    public static Move getNewRandomMove() {
        Move[] moveValues = Move.values();
        return moveValues[new Random().nextInt(moveValues.length)];
    }
}

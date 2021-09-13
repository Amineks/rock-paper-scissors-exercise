package com.amine.ciklumchallenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class Game implements Serializable {

    private List<Round> rounds;

    public void addRoundToGame(Round round) {
        if (rounds == null) {
            rounds = new ArrayList<>();
        }
        rounds.add(round);
    }
}
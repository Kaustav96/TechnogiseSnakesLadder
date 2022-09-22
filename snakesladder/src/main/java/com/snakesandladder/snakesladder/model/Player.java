package com.snakesandladder.snakesladder.model;

import java.util.UUID;

public class Player {

    private String name;
    private String playerId;

    public Player(String name) {
        this.name = name;
        this.playerId = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getPlayerId() {
        return playerId;
    }
}

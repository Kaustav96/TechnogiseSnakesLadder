package com.snakesandladder.snakesladder.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnakeAndLadderBoard {

    private int boardSize;
    private List<Snake> snakeList;
    private List<Ladder> ladderList;
    private Map<String, Integer> playerPosition;

    public SnakeAndLadderBoard(int boardSize) {
        this.boardSize = boardSize;
        this.ladderList = new ArrayList<>();
        this.snakeList = new ArrayList<>();
        this.playerPosition = new HashMap<>();
    }

    public int getBoardSize() {
        return boardSize;
    }

    public List<Snake> getSnakeList() {
        return snakeList;
    }

    public void setSnakeList(List<Snake> snakeList) {
        this.snakeList = snakeList;
    }

    public List<Ladder> getLadderList() {
        return ladderList;
    }

    public void setLadderList(List<Ladder> ladderList) {
        this.ladderList = ladderList;
    }

    public Map<String, Integer> getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Map<String, Integer> playerPosition) {
        this.playerPosition = playerPosition;
    }
}


package com.snakesandladder.snakesladder.service;

import java.util.Random;

public class DiceService {

    private static int MAX_DICE_VALUE;
    private static int MIN_DICE_VALUE = 0;

    public DiceService(int MAX_DICE_VALUE) {
        this.MAX_DICE_VALUE = MAX_DICE_VALUE;
    }

    public static int getDiceValue(){
        return new Random().nextInt(MAX_DICE_VALUE)+MIN_DICE_VALUE;
    }

    public static int getMaxDiceValue() {
        return MAX_DICE_VALUE;
    }

    public static int getMinDiceValue() {
        return MIN_DICE_VALUE;
    }
}

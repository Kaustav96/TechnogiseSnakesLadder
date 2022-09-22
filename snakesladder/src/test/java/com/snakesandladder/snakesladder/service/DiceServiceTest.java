package com.snakesandladder.snakesladder.service;

import com.snakesandladder.snakesladder.constants.Constant;
import org.mockito.InjectMocks;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DiceServiceTest {

    @InjectMocks
    private DiceService diceService;

    // check if value is between 1 & 6
    @BeforeMethod
    public void beforeMethod(){
        int noOfDices = Constant.DEFAULT_NO_OF_DICES;
        diceService = new DiceService(noOfDices*6);
    }

    @Test
    void testValueWithinRange(){
        int minDiceValue = DiceService.getMinDiceValue();
        int maxDiceValue = DiceService.getMaxDiceValue();
        int position = DiceService.getDiceValue();
        Assert.assertTrue(position>=minDiceValue && position<=maxDiceValue,"In Range");
    }
}

package com.snakesandladder.snakesladder.service;

import com.snakesandladder.snakesladder.model.Ladder;
import com.snakesandladder.snakesladder.model.Player;
import com.snakesandladder.snakesladder.model.Snake;
import lombok.extern.slf4j.Slf4j;
import org.mockito.InjectMocks;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SnakeAndLadderServiceTest {
    @InjectMocks
    private SnakeAndLadderService snakeAndLadderService;
    private List<Snake> snakeList;
    private List<Ladder> ladderList;
    private List<Player> playerList;

    @BeforeMethod
    public void beforeMethod(){
        snakeAndLadderService = new SnakeAndLadderService();
        playerList = new ArrayList<>();
        snakeList = new ArrayList<>();
        ladderList = new ArrayList<>();
    }
    @Test
    public void testStartGameWithOneDiceOnePlayer() throws IOException {
        getFileData("src/main/resources/inputOneDiceOnePlayer.txt");
        snakeAndLadderService.startGame();
    }
    @Test
    public void testStartGameWithOneDiceTwoPlayer() throws IOException {
        getFileData("src/main/resources/inputOneDiceTwoPlayer.txt");
        snakeAndLadderService.startGame();
    }
    @Test
    public void testStartGameWithOneDiceMultiplePlayer() throws IOException {
        getFileData("src/main/resources/inputOneDiceMultiplePlayer.txt");
        snakeAndLadderService.startGame();
    }
    @Test
    public void testStartGameWithTwoDiceOnePlayer() throws IOException {
        getFileData("src/main/resources/inputTwoDiceOnePlayer.txt");
        snakeAndLadderService.startGame();
    }
    @Test
    public void testStartGameWithTwoDiceTwoPlayer() throws IOException {
        getFileData("src/main/resources/inputTwoDiceTwoPlayer.txt");
        snakeAndLadderService.startGame();
    }
    @Test
    public void testStartGameWithTwoDiceMultiplePlayer() throws IOException {
        getFileData("src/main/resources/inputTwoDiceMultiplePlayer.txt");
        snakeAndLadderService.startGame();
    }
    private void getFileData(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String data;
        while((data = br.readLine())!=null){
            if(data.equalsIgnoreCase("dice")){
                // update the no of dices in the game
                int noOfDice = Integer.parseInt(br.readLine());
                log.info("Number of dices in game : {} ", noOfDice);
                snakeAndLadderService.setNoOfDices(noOfDice);
            }
            // update the snakes & ladders on the board
            else if(data.equalsIgnoreCase("snake")){
                int noOfSnakes = Integer.parseInt(br.readLine());
                log.info("Number of snakes on the board : {} ",noOfSnakes);
                for(int i=0;i<noOfSnakes;i++) {
                    String[] value = br.readLine().split(" ");
                    snakeList.add(new Snake(Integer.parseInt(value[0]),Integer.parseInt(value[1])));
                }
                snakeAndLadderService.setSnake(snakeList);
            }
            else if(data.equalsIgnoreCase("ladder")){
                int noOfLadder = Integer.parseInt(br.readLine());
                log.info("Number of ladders on the board : {} ",noOfLadder);
                for(int i=0;i<noOfLadder;i++) {
                    String[] value = br.readLine().split(" ");
                    ladderList.add(new Ladder(Integer.parseInt(value[0]), Integer.parseInt(value[1])));
                }
                snakeAndLadderService.setLadder(ladderList);
            }
            else if(data.equalsIgnoreCase("player")){
                // get number of players
                int noOfPlayers = Integer.parseInt(br.readLine());
                log.info("Number of players playing are : {} ", noOfPlayers);
                for(int i=0;i<noOfPlayers;i++) {
                    String playerName = br.readLine();
                    playerList.add(new Player(playerName));
                }
                snakeAndLadderService.setPlayers(playerList);
                if(noOfPlayers>2){
                    snakeAndLadderService.setGameToBePlayedTillLastPlayer(true);
                }
            }
        }
    }
}

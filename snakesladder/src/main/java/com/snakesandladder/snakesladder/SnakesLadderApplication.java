package com.snakesandladder.snakesladder;

import com.snakesandladder.snakesladder.model.Ladder;
import com.snakesandladder.snakesladder.model.Player;
import com.snakesandladder.snakesladder.model.Snake;
import com.snakesandladder.snakesladder.service.SnakeAndLadderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class SnakesLadderApplication {
	/**
	 *
	 * @param args
	 * @throws IOException
	 * read input from file
	 * assumptions -> 4 snakes & ladders
	 * board size = 100
	 * dice = 1
	 * if more than 1 dice is played added the functionality
	 * if more than 2 players play then continue till last player is left.
	 *
	 */

	public static void main(String[] args) throws IOException {

		SpringApplication.run(SnakesLadderApplication.class, args);
		SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService(); //initialising the board size
		Scanner sc = new Scanner(System.in);
		File file = new File("src/main/resources/inputOneDiceTwoPlayer.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String data;
		while((data=br.readLine())!=null){
			if(data.equalsIgnoreCase("dice")){
				// update the no of dices in the game
				int noOfDices = Integer.valueOf(br.readLine());
				log.info("Number of dices in game : {} ",noOfDices);
				snakeAndLadderService.setNoOfDices(noOfDices);
			}
			// update the snakes & ladders on the board
			else if(data.equalsIgnoreCase("snake")){
				int noOfSnakes = Integer.valueOf(br.readLine());
				log.info("Number of snakes on the board : {} ",noOfSnakes);
				List<Snake> snakeList = new ArrayList<>();
				for(int i=0;i<noOfSnakes;i++) {
					String[] value = br.readLine().split(" ");
					snakeList.add(new Snake(Integer.valueOf(value[0]),Integer.valueOf(value[1])));
				}
				snakeAndLadderService.setSnake(snakeList);
			}
			else if(data.equalsIgnoreCase("ladder")){
				int noOfLadder = Integer.valueOf(br.readLine());
				List<Ladder> ladderList = new ArrayList<>();
				log.info("Number of ladders on the board : {} ",noOfLadder);
				for(int i=0;i<noOfLadder;i++){
					String[] value = br.readLine().split(" ");
					ladderList.add(new Ladder(Integer.valueOf(value[0]),Integer.valueOf(value[1])));
				}
				snakeAndLadderService.setLadder(ladderList);
			}
			else if(data.equalsIgnoreCase("player")){
				// get number of players
				int noOfPlayers = Integer.valueOf(br.readLine());
				log.info("Number of players playing are : {} ", noOfPlayers);
				List<Player> playerList = new ArrayList<>();
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

		// start the game by calling the services
		snakeAndLadderService.startGame();

	}
}

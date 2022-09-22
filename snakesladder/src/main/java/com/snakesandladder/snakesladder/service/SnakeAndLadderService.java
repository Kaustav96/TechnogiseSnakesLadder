package com.snakesandladder.snakesladder.service;

import com.snakesandladder.snakesladder.constants.Constant;
import com.snakesandladder.snakesladder.model.Ladder;
import com.snakesandladder.snakesladder.model.Player;
import com.snakesandladder.snakesladder.model.Snake;
import com.snakesandladder.snakesladder.model.SnakeAndLadderBoard;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

@Slf4j
public class SnakeAndLadderService {

    // add players
    // set board size
    // update the snakes & ladder positions
    // update board
    // update player position
    // player movement to new position. -> check if position is not >100
    // check player won or not.
    // start the game here

    private int initialPlayersToPlay;
    private SnakeAndLadderBoard snakeAndLadderBoard;
    private Queue<Player> players; // keeping player in game service as specific to game not board, pieces can be kept in board
    private int noOfDices; // what if we want to play with more than 1 dice
    private boolean gameToBePlayedTillLastPlayer;
    public SnakeAndLadderService(int initialBoardSize) {
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(initialBoardSize);
        this.players = new LinkedList<>();
        this.noOfDices = Constant.DEFAULT_NO_OF_DICES;
    }

    public SnakeAndLadderService() {
        this(Constant.DEFAULT_BOARD_SIZE);
    }

    /**
     * Setter methods to make code/ game more adaptable to changes
     */
    public void setNoOfDices(int noOfDices) {
        this.noOfDices = noOfDices;
        DiceService diceService = new DiceService(noOfDices*6);
    }

    public void setGameToBePlayedTillLastPlayer(boolean gameToBePlayedTillLastPlayer) {
        this.gameToBePlayedTillLastPlayer = gameToBePlayedTillLastPlayer;
    }

    /**
     * board initialize
     * 1. with player position at 0
     * 2. with snakes & ladders
     */
    /**
     *
     * @param players
     * set players at position 0
     */
    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<>();
        this.initialPlayersToPlay = players.size();
        Map<String, Integer> playerPositions = new HashMap<>();

        for(Player player: players){
            this.players.add(player);
            playerPositions.put(player.getPlayerId(),0); // player is placed outside board at position 0
        }
        snakeAndLadderBoard.setPlayerPosition(playerPositions); // update the pieces to board
    }

    /**
     *
     * @param snakeList
     * set snakes in the board
     */
    public void setSnake(List<Snake> snakeList){
        snakeAndLadderBoard.setSnakeList(snakeList);
    }

    /**
     *
     * @param ladderList
     * set ladders in the board
     */
    public void setLadder(List<Ladder> ladderList){
        snakeAndLadderBoard.setLadderList(ladderList);
    }
    /**
     * implementing the game logic
     */

    /**
     *
     * @param name
     * @param position
     * @return get latest position of player on board
     */
    private int getLatestPositionAfterGoingThroughSnakeLadder(String name, int position){
        // store the previous position of player
        int previousPosition = -1;

        do{
            previousPosition = position;
            for(Snake snake : snakeAndLadderBoard.getSnakeList()){
                // if snake head then move down to the tail location
                if(snake.getHead()==position){
                    position = snake.getTail();
                    log.info("{} Found snake at {} & goes down to {}", name,snake.getHead(),position);
                }
            }
            // if ladder then climb the ladder
            for(Ladder ladder: snakeAndLadderBoard.getLadderList()){
                if(ladder.getStart()==position){
                    position = ladder.getEnd();
                    log.info("{} Found ladder at {} & goes up to {}", name,ladder.getStart(),position);
                }
            }
        }while(position!=previousPosition);
        return position;
    }

    /**
     *
     * @param player
     * @param position
     * move player to new position & check whether path has snakes or ladders and also check if inside board or not
     */
    private void movePlayer(Player player,int position){
        int oldPosition = snakeAndLadderBoard.getPlayerPosition().get(player.getPlayerId());
        int newPosition = oldPosition+position;

        int boardSize = snakeAndLadderBoard.getBoardSize();

        // check if current position is inside board or not
        if(newPosition>boardSize){
            newPosition = oldPosition;
        }
        else{
            newPosition = getLatestPositionAfterGoingThroughSnakeLadder(player.getName(),newPosition);
        }
        // update the player position in board
        snakeAndLadderBoard.getPlayerPosition().put(player.getPlayerId(), newPosition);

        log.info("{} rolled a {} to move from old position : {} to new position : {}",player.getName(),position,oldPosition,newPosition);
    }

    /**
     *
     * @return new value after dice roll
     */
    private int getTotalValueAfterDiceRoll(){
        // can use the no of dices & should roll be allowed on 6 here
        int diceValue = 0;
        if(noOfDices==1) {
            return (DiceService.getDiceValue())==0?1:DiceService.getDiceValue();
        }
        else{
            for(int i=0;i<noOfDices;i++){
                diceValue+=(DiceService.getDiceValue())==0?1:DiceService.getDiceValue();
            }
            return diceValue;
        }
    }

    /**
     *
     * @param player
     * @return player won or not
     */
    private boolean hasPlayerWon(Player player){
        int playerPosition = snakeAndLadderBoard.getPlayerPosition().get(player.getPlayerId());
        int winningPosition = snakeAndLadderBoard.getBoardSize();

        return playerPosition==winningPosition;
    }

    /**
     *
     * @return check game complete or not
     * also check if more than 1 players are present then continue the game
     */
    private boolean isGameCompleted(){
        // change logic if game is played till last player ( more than 1 players )
        int currentPlayerLeft = players.size();
        if(gameToBePlayedTillLastPlayer){
            return currentPlayerLeft<=1;
        }else{
            return currentPlayerLeft<initialPlayersToPlay;
        }

    }
    /**
     * starting the game logic
     * @return
     */
    public void startGame() {
        while (!isGameCompleted()) {
            int totalDiceValue = getTotalValueAfterDiceRoll(); // Each player rolls the dice when their turn comes.
            Player currentPlayer = players.poll();
            movePlayer(currentPlayer, totalDiceValue);
            if (hasPlayerWon(currentPlayer)) {
                if(initialPlayersToPlay>1){
                    log.info("{} wins the game at position {}",currentPlayer.getName(),Constant.CURR_POSITION++);
                }else{
                    log.info("{} wins the game",currentPlayer.getName(),Constant.CURR_POSITION++);
                }
                snakeAndLadderBoard.getPlayerPosition().remove(currentPlayer.getPlayerId());
                if(players.size()==1){
                    log.info("{} comes at position {}",players.poll().getName(),Constant.CURR_POSITION);
                    return;
                }
            } else {
                players.add(currentPlayer);
            }
        }
    }
}

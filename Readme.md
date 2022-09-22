* You are required to create a program, which simulates the playing of a Snakes & Ladders game.
* Board: There are 100 cells on a Snakes & Ladders board from 01, 02... all the way to 100. 
* However, your starting position is 00, which is outside the board.
Assumptions:
* Assume that the game has 4 snakes and 4 ladders of varying lengths dispersed on the board.
* You can choose to initialize / define where these snakes and ladders will be on the board and how long each of them
are. For e.g, a ladder could be at cell 07 and it could take you ahead to 33. Or a snake could be at 87 and bring you
back to 32. It's your choice.
* Inputs and Outputs for your program:
* The input to your program will be any number between 1 to 6 (...the numbers on a dice...), and your current position on the board
* The output of your program will be your new position on the board.
* End of game: The game ends as soon as you reach exactly 100. However, if the output comes out to be more than
  100, then the piece will remain in the current position.


Rules:

1. The players will roll for their turn

2. You must roll on the finishing square to win, if you get higher, you will be moved back by
   (Winning point - (roll))

3. If you land on a ladder, you will be moved up.

4. If you land on a snake, you will be moved down.

Class design:
1. For my class design I have decided to create separate models for players, ladder, snake.
2. Players class has the details of the players so that I can get the player name & id.
3. Snake class has the data for where the snake is placed on the board.
4. Ladder class has the data where the ladder is placed on board.
5. For board class I decided to make a separate class which involves the board. 
Board class contains the board-size for the game & also List of snakes & ladders,
& also store the player position on the board.

Service Design:
1. I have created a separate service for the board & dice.
2. Dice service is basically getting the random dice value between 1 & 6 when it is the players turn.
3. Snake&Ladder service is having the main game logic.
   1. in this service I am checking if the player has won the game or not.
   2. getting the total dice value if the number of dice is > 1.
   3. most importantly moving the player into its new position & also checking if the
player is not going beyond the winning point
   4. when a player moves I am checking if at current board position they have found any snake/ladder.
   5. also applied a check to see if the game has completed which will give true only if 1 player is left when multiple
players are playing.

Main Class:
1. I have created a separate main class which will be reading input from file.
   1. Taking a file because it would be easy to change the values at one place rather than always 
taking i/p from console.
2. all the values for snakes ladders number of player & number of dices are being set here.
3. once all the values are in place we start the game.

Test Classes:
1. created 2 test class.
   1. Dice Service Test -> it is testing if the value is in range of min value -1 
      to max value 6*(number of dice).
   2. Snake & ladder service test -> there are 6 tests here
      1. testOneDiceMultiplePlayer
      2. testOneDiceOnePlayer
      3. testOneDiceTwoPlayer
      4. testTwoDiceTwoPlayer
      5. testTwoDiceOnePlayer
      6. testTwoDiceMultiplePlayer

Data Structures:

For this game I have used List, Map & Queue

1. For list i used it to organise the board,snakes & ladders.
2. I used queues to store the player details. Queue being a FIFO DS, it helped me switch 
   between player turns conveniently since the player who played the last turn gets added at the end of queue.
3. Map I used to store the player positions based on Player id as key and player position as value.
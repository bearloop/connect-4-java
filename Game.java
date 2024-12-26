import java.util.ArrayList;

public class Game {

  private static final int MAX_COLUMNS = 7;
  private static final int MAX_ROWS = 6;
  private static final int MIN_COLUMNS = 1;
  private static final String COLUMNS = "columns";

  private ArrayList<Player> playersList = new ArrayList<Player>();
  private Board board;
  private int lastCommand;
  private int computers;
  private int humans;

  public Game(int numOfTokens, int numOfComputers, int numOfHumans) {

    // Initialise last move to 'exit'
    lastCommand = InputValidation.getExitValue();

    // Create the game's board
    board = new Board(MAX_ROWS, MAX_COLUMNS, numOfTokens);

    // Add human players
    this.humans = numOfHumans;
    for (int playerId = 0; playerId < numOfHumans; playerId++) {
      this.playersList.add(new Human(playerId + 1, Token.getHumanToken(playerId)));
    }

    // Add computer players
    this.computers = numOfComputers;
    for (int playerId = 0; playerId < numOfComputers; playerId++) {
      this.playersList.add(new Computer(playerId + 1, Token.getComputerToken(playerId)));
    }

    // Start the game
    playSingleGame(false);

  }

  // Game logic
  private void playSingleGame(boolean gameOver) {
    printWelcomeMessage();
    Display.drawBoard(board.getGrid());

    while (!gameOver) {
      for (Player player : playersList) {
        // Checks if grid is full so as to end the game
        if (board.isGridFull()) {
          System.out.println("The grid has no available moves left. No player wins!!!");
          printGameOver();
          gameOver = true;
          lastCommand = InputValidation.getNewGameValue();// set command to new game
          // No need to continue with the rest of the players
          break;
        }

        // Play the round
        printWhoseTurn(player);
        gameOver = gameRound(player, gameOver);

        // Print the board
        Display.drawBoard(board.getGrid());

        // Check if the game has ended
        if (gameOver) {
          // Check if the user did not ask for a new game or exited
          if (!InputValidation.checkInputCommand(lastCommand)) {
            System.out.println(player.getName() + " has won!!!");
          }
          printGameOver();
          // No need to continue with the rest of the players
          break;
        }
      }

    }

    // Update last command
    lastCommand = (lastCommand == InputValidation.getExitValue() ? InputValidation.getExitValue()
        : InputValidation.getNewGameValue());
  }

  // Game round logic
  private Boolean gameRound(Player currentPlayer, Boolean gameOver) {

    // If the computer is playing, then randomly select column,
    // otherwise wait for user input
    int move = currentPlayer.playerInput(MIN_COLUMNS, MAX_COLUMNS, COLUMNS);

    // Checks if the user typed exit or new game
    if (InputValidation.checkInputCommand(move)) {
      lastCommand = move; // update last command
      return true;
    }

    // Checks if the token was placed or the column was full
    Boolean isPlaced = board.placeToken(currentPlayer.getToken(), move);

    // If it was placed check whether the player has won
    if (isPlaced) {
      if (currentPlayer.hasWon(board.getGrid(), board.getTokens())) {
        lastCommand = move; // update last command
        return true;
      }
      // Continue with the game
      return false;

      // Otherwise the column is full and we need to re-run the current round
    } else {
      printFullColumn(currentPlayer);
      return gameRound(currentPlayer, false);
    }
  }

  // Prints welcome info
  private void printWelcomeMessage() {
    System.out.println("\n" + "------ USER INFO ------");
    System.out.println("1. Type '" + InputValidation.getExitText() + "' to quit the game");
    System.out.println("2. Type '" + InputValidation.getNewGameText() + "' to start a new game");
    System.out.println("------");
    System.out.println("Welcome to Connect" + board.getTokens());
    System.out.println("To play the game type in the number of the column you want to drop your token in");
    System.out.println("A player wins by connecting " + board.getTokens()
        + " tokens in a row - vertically, horizontally or diagonally");
    System.out.println("------");
    System.out.println("There are " + playersList.size() + " players in the current game");
    System.out.println(humans + " human and " + computers + " computer players" + "\n");
  }

  // Prints feedback while the user is playing
  private void printWhoseTurn(Player currentPlayer) {
    if (currentPlayer.isComputerPlayer()) {
      System.out.println("\n" + currentPlayer.getName() + " is thinking.." + "\n");
    } else {
      System.out.println("\n" + currentPlayer.getName() + " is playing.." + "\n");
    }
  }

  // Prints out some feedback to let the user know if the column is full
  private void printFullColumn(Player currentPlayer) {
    if (!currentPlayer.isComputerPlayer()) {
      System.out.println("-----");
      System.out.println("The column you are trying to update is full");
      System.out.println("Please select a different column");
    } else {
      System.out.println("...");
    }
  }

  // Prints out game over
  private void printGameOver() {
    System.out.println("------ GAME OVER ------");
  }

  // Getter for last move
  public int getLastCommand() {
    return lastCommand;
  }
}
/* 
* Player abstract class
*/
public abstract class Player {
  private char playerToken;
  private String name;
  private Boolean isComputer;

  public Player(String name, char playerToken, Boolean isComputer) {
    this.playerToken = playerToken;
    this.name = name;
    this.isComputer = isComputer;
  }

  public String getName() {
    return this.name;
  }

  public Boolean isComputerPlayer() {
    return this.isComputer;
  }

  public char getToken() {
    return this.playerToken;
  }

  /*
   * -----------------------------------------------------------------------
   * Determine whether the player has won after a round is played
   */

  // Checks whether the player has won
  public Boolean hasWon(char[][] grid, int tokensToWin) {

    if (hasWonHorizontally(grid, tokensToWin)) {
      return true;
    } else if (hasWonVertically(grid, tokensToWin)) {
      return true;
    } else if (hasWonDiagonally(grid, tokensToWin)) {
      return true;
    } else {
      return false;
    }
  }

  // Checks if a horizontal string of tokens exists
  private Boolean hasWonHorizontally(char[][] grid, int tokensToWin) {

    // Token count starts at 0
    int count = 0;

    // Iterate each row (grid.length = number of rows)
    for (int row = 0; row < grid.length; row++) {

      // Iterate each column (grid[row].length = number of columns for given row)
      for (int col = 0; col < grid[row].length; col++) {

        // Estimate count
        count = estimateCount(row, col, count, grid);

        // Return true and break the loop as soon as the condition is met
        if (count == tokensToWin) {
          // System.out.println("Col/row Horizontal win is established: " + col + "|"+
          // row);
          return true;
        }
      }
      count = 0;
    }
    return false;
  }

  // Checks if a vertical string of tokens exists
  private Boolean hasWonVertically(char[][] grid, int tokensToWin) {

    // Token count starts at 0
    int count = 0;

    // Iterate each column
    for (int col = 0; col < grid[0].length; col++) {

      // Iterate each row
      for (int row = 0; row < grid.length; row++) {

        // Estimate count
        count = estimateCount(row, col, count, grid);

        // Return true and break the loop as soon as the condition is met
        if (count == tokensToWin) {
          // System.out.println("Col/row Vertical win is established: " + col + "|"+ row);
          return true;
        }
      }
      count = 0;
    }
    return false;
  }

  // Checks if a diagonal string of tokens exists
  private Boolean hasWonDiagonally(char[][] grid, int tokensToWin) {

    // int count = 0;

    // Iterate each column starting at the left most columnn
    for (int col = 0; col < grid[0].length; col++) {

      // Iterate every row starting at the top row
      for (int row = 0; row < grid.length; row++) {

        // Diagonal Left to Right and next row
        if (determineDiagonalWin(true, 0, row, col, grid, tokensToWin)) {
          return true;
        }
        // Diagonal Left to Right and previous row
        else if (determineDiagonalWin(false, 0, row, col, grid, tokensToWin)) {
          return true;
        }
      }

    }
    return false;

  }

  // Looks forward / backward N rows to determine whether there is a sequence of N
  // tokens
  private Boolean determineDiagonalWin(Boolean nextRow, int count, int row, int col, char[][] grid, int tokensToWin) {

    for (int lookAhead = 0; lookAhead < tokensToWin; lookAhead++) {

      try {
        // Estimate count
        int whichRow = (nextRow ? row + lookAhead : row - lookAhead);
        count = estimateCount(whichRow, col + lookAhead, count, grid);

        // Return true and break the loop as soon as the condition is met
        if (count == tokensToWin) {

          return true;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        count = 0;

        break;
      }
    }
    return false;

  }

  // Estimate current count value
  private int estimateCount(int row, int col, int count, char[][] grid) {

    // If the player token is in the current position
    if (grid[row][col] == playerToken) {
      count = count + 1;
    } else {
      count = 0;
    }
    return count;
  }

  /*
   * -----------------------------------------------------------------------
   * Player input abstract method
   */

  public abstract int playerInput(int minAllowed, int maxAllowed, String whatToValidate);

}
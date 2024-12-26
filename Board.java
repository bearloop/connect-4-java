/*
* Board class that creates a grid of N rows and M columns
*/
public class Board {

    // Grid variables
    private char[][] grid;

    // Required tokens
    private Integer tokens;

    // Null character to check for cell capacity
    private static final char CHAR_NULL = '\0';

    // constructor
    public Board(Integer rows, Integer columns, Integer tokens) {

        this.grid = new char[rows][columns];
        this.tokens = tokens;
    }

    // Returns current grid
    public char[][] getGrid() {
        return this.grid;
    }

    // Returns game tokens
    public int getTokens() {
        return this.tokens;
    }

    // Adds player token to the passed column (if there's capacity) and returns a
    // Boolean
    public Boolean placeToken(char playerToken, int position) {

        boolean placed = false;

        // Iterate each row
        for (int row = grid.length - 1; row >= 0; row--) {
            // Check if the cell is "empty"
            if (grid[row][position - 1] == CHAR_NULL) {
                // Set value to the player's token
                grid[row][position - 1] = playerToken;
                placed = true;
                break;
            }
        }
        return placed;
    }


    // Check if grid is full
    public Boolean isGridFull(){

        // Iterate over rows and column to check if at least one is empty
        for(int row=grid.length-1; row>=0; row--){
            for (int col=grid[0].length-1; col>=0; col--){
                if (grid[row][col] == CHAR_NULL){
                    return false;
                }
            }
        }
        return true;   
    }
}

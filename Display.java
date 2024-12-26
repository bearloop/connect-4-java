/* 
* Print current board
*/
public class Display {

    // Draw the game board
    public static void drawBoard(char[][] grid) {
        drawLine(grid);
        System.out.println("Game Board:");
        drawLine(grid);

        // Iterate rows
        for (int row = 0; row < grid.length; row++) {

            // Iterate columns
            for (int col = 0; col < grid[row].length; col++) {

                // Print token emoji for given grid char
                drawEmoji(grid[row][col]);
            }
            // need this extra bit to complete the right side of the grid
            System.out.println("|");
        }
        drawLine(grid);
        drawColNumber(grid);
    }

    // Print a sequence of numbers
    private static void drawColNumber(char[][] grid) {

        // Iterate over each column and print the next number (index+1)
        System.out.print(" ");
        for (int col = 0; col < grid[0].length; col++) {
            System.out.print("  " + (col + 1) + "  ");
        }
        System.out.println("");
    }

    // Print a separating line
    private static void drawLine(char[][] grid) {

        for (int col = 0; col < grid[0].length; col++) {
            System.out.print("-----");
        }
        System.out.println("-");
    }

    // Print token colours
    private static void drawEmoji(char token) {
        if (token=='\0'){
            System.out.print("|    ");
        }
        else{
            String emoji = Token.tokensEmojisMapping().get(String.valueOf(token));
            if (emoji==null){
                emoji = String.valueOf(token)+" ";
            }
            System.out.print("| "+emoji+" ");
        }

    }

}
import java.lang.Thread;

/* 
* Computer player class
*/
public class Computer extends Player {

  public Computer(int id, char playerToken) {
    super("Computer " + id, playerToken, true);
  }

  // Select random column to emulate computer game
  @Override
  public int playerInput(int minAllowed, int maxAllowed, String whatToValidate) {

    // delay computer input to give the illusion of a thinking machine
    try {
      Thread.sleep(500);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
    // returns random * (max - min) + min
    // in this case max equals columns+1 to include the rightmost column and min = 1
    return (int) (Math.random() * (maxAllowed + 1 - minAllowed) + 1);

  }
}

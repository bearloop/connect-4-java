/* 
* Human player class
*/
public class Human extends Player {
  private InputValidation humanPlayerInput;

  public Human(int id, char playerToken) {
    super("Human " + id, playerToken, false);
    humanPlayerInput = new InputValidation();
  }

  // Get user input
  @Override
  public int playerInput(int minAllowed, int maxAllowed, String whatToValidate) {

    return humanPlayerInput.validateInput(minAllowed, maxAllowed, whatToValidate);
  }

}
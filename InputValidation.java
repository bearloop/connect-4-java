import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputValidation {
    private static final String INVALID_INPUT = "INVALID_INPUT";
    private static final String EXIT = "exit";
    private static final String NEW_GAME = "ngame";
    private static final int EXIT_VALUE = -1;
    private static final int NGAME_VALUE = 0;
    private BufferedReader input;

    public InputValidation() {
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    // User input validation during the course of the program
    public int validateInput(int minAllowed, int maxAllowed, String whatToValidate) {

        String userInput = getUserInput();

        // If an error occurs ask for user Input again
        if (userInput.equals(INVALID_INPUT)) {
            printUnknownError();
            return validateInput(minAllowed, maxAllowed, whatToValidate);
        } else {
            return askForInput(userInput, minAllowed, maxAllowed, whatToValidate);
        }
    }

    // Command line arguments validation (before the program starts)
    public int validateCommandLineArgs(String[] commandLineInput, int minAllowed, int maxAllowed, String whatToValidate) {
        if (commandLineInput.length == 0) {
            System.out.println("Please pass the number of tokens as an argument");
            return validateInput(minAllowed, maxAllowed, whatToValidate);
        } else {
            return askForInput(commandLineInput[0], minAllowed, maxAllowed, whatToValidate);
        }
    }

    // Recursively ask for input till we get one valid number
    private int askForInput(String userInput, int minAllowed, int maxAllowed, String whatToValidate){
        if (userInput.equals(EXIT)){
            return EXIT_VALUE;
        }
        else if (userInput.equals(NEW_GAME)){
            return NGAME_VALUE;
        }
        else if (isAnInt(userInput) && isWithinRange(Integer.parseInt(userInput), minAllowed, maxAllowed)) {
            return Integer.parseInt(userInput);
        } else {
            printInvalidInput(minAllowed, maxAllowed, whatToValidate);
            return validateInput(minAllowed, maxAllowed, whatToValidate);
        }
    }

    // Read user input
    private String getUserInput() {
        try {
            return this.input.readLine();
        } catch (Exception e) {
            return INVALID_INPUT;
        }

    }

    // Checks if column or token count is within range
    private Boolean isWithinRange(int count, int minAllowed, int maxAllowed) {
        if (count >= minAllowed && count <= maxAllowed) {
            return true;
        }
        return false;
    }

    // Checks if input can be parsed as int
    private Boolean isAnInt(String userInput) {
        try {
            Integer.parseInt(userInput);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    // Print info for invalid input
    private void printInvalidInput(int minAllowed, int maxAllowed, String whatToValidate) {
        System.out.println("------");
        System.out.println("Please provide a valid number of " +
                whatToValidate + " (" + minAllowed + " - " + maxAllowed + ")");
    }

    // Print info for unknown error
    private void printUnknownError() {
        System.out.println("------");
        System.out.println("An error occured, please provide your input again");
    }

    // Getters without the need to instantiate the class
    public static int getExitValue(){
        return EXIT_VALUE;
    }

    public static int getNewGameValue(){
        return NGAME_VALUE;
    }

    public static String getExitText(){
        return EXIT;
    }

    public static String getNewGameText(){
        return NEW_GAME;
    }

    // Checks if the user command is either exit or new game
    public static Boolean checkInputCommand(int command) {
    if (command == EXIT_VALUE || command == NGAME_VALUE) {
      return true;
    }
    return false;
  }

}

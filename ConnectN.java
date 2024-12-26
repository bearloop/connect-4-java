public class ConnectN {

    private static final String TOKENS = "tokens";
    private static final String COMP_PLAYERS = "computer players";
    private static final String HUMAN_PLAYERS = "human players";

    private static final int MAX_TOKENS = 6;
    private static final int MAX_COMPUTERS = Token.getNumOfComputerTokens();
    private static final int MAX_HUMANS = Token.getNumOfHumanTokens();

    private static final int MIN_TOKENS = 3;
    private static final int MIN_COMPUTERS = 1;
    private static final int MIN_HUMANS = 1;

    private InputValidation input;
    private int userCommand;

    public ConnectN(String[] args) {

        // Input validation is a wrapper class of BufferedReader that validates user
        // input
        input = new InputValidation();

        // Initialise user input to new game
        userCommand = InputValidation.getNewGameValue();

        // Run the game
        startGame(args);

        // Exiting the program
        printExit();

    }

    private void startGame(String[] args) {
        Boolean isCLInput = true;
        int tokens = MIN_TOKENS;
        int computers = MIN_COMPUTERS;
        int humans = MIN_HUMANS;

        // Run while the user input is not 'exit'
        while (userCommand != InputValidation.getExitValue()) {

            // Start a new game if the user input is 'ngame'
            if (userCommand == InputValidation.getNewGameValue()) {

                // Hanlde tokens
                if (isCLInput) {
                    // Validate command line input (because tokens are passed as a command line
                    // argument the first time)
                    tokens = input.validateCommandLineArgs(args, MIN_TOKENS, MAX_TOKENS, TOKENS);
                    isCLInput = false;
                } else {
                    // Request user input for tokens
                    printStartNew();
                    tokens = input.validateInput(MIN_TOKENS, MAX_TOKENS, TOKENS);
                }
                // Update last command based on tokens
                userCommand = tokens;

                // Request input for computers opponents
                if (!InputValidation.checkInputCommand(userCommand)) {
                    // Ask for user input re computer players
                    printSelectComputers();
                    computers = input.validateInput(MIN_COMPUTERS, MAX_COMPUTERS, COMP_PLAYERS);
                    // Update last command based on computers
                    userCommand = computers;
                }

                // Request input for human opponents
                if (!InputValidation.checkInputCommand(userCommand)) {
                    // Ask for user input re human players
                    printSelectHumans();
                    humans = input.validateInput(MIN_HUMANS, MAX_HUMANS, HUMAN_PLAYERS);
                    // Update last command based on humans
                    userCommand = humans;
                }

            }
            // Play the game
            else {
                Game currentGame = new Game(tokens, computers, humans);
                userCommand = currentGame.getLastCommand();
            }
        }
    }

    private void printStartNew() {
        System.out.println("\n" + "Starting a new game...");
        System.out.println("Please select the number of tokens or type 'exit' to quit the game");
    }

    private void printSelectComputers() {
        System.out.println("\n" + "Please select the number of computers to play against (" + MIN_COMPUTERS + " - "
                + MAX_COMPUTERS + ")");
    }

    private void printSelectHumans() {
        System.out.println("\n" + "Please select the number of humans to play against (" + MIN_HUMANS + " - "
                + MAX_HUMANS + ")");
    }

    private void printExit() {
        System.out.println("\n" + "Exiting the game... Goodbye!" + "\n");
        System.exit(0);
    } 

}
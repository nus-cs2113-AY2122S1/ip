package ui;

/**
 * Helps to format and print all the output to the console.
 */

public class Ui {
    private final int noOfPlus = 120;

    /**
     * Prints out a statement with borders.
     *
     * @param statement Statement to be printed.
     */
    public void customPrint(String statement) {
        System.out.println("+".repeat(noOfPlus));
        System.out.println(statement.trim()); // Remove any trailing spaces
        System.out.println("+".repeat(noOfPlus));
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Prints welcome message
        String welcomeMessage = logo;
        welcomeMessage += "Hello! I'm Duke\n";
        welcomeMessage += "What can I do for you?";
        customPrint(welcomeMessage);
    }

    /**
     * Prints the invalid command message.
     */
    public void printInvalidCommandMessage() {
        customPrint("Sorry! I did not understand your command.");
    }

}

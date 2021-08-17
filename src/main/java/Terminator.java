import java.util.Scanner;

public class Terminator {

    public static final int TERMINATOR_FORMATTING = 0;
    public static final int USER_FORMATTING = 1;
    public static Boolean toContinue = true;

    /**
     * Prints Goodbye message to user
     */
    public static void printGoodByeMessage(){
        System.out.println(formatWithHeading("Hasta la vista.", TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading("I will be back.", TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading("Program Terminating in...", TERMINATOR_FORMATTING));
        // Stops at 2 intentionally
        for (int i = 5; i > 1; --i){
            System.out.println(formatWithHeading(Integer.toString(i), TERMINATOR_FORMATTING));
        }
    }

    /**
     * Format printed messages with the appropriate headings.
     * If option TERMINATOR_FORMATTING is selected, [The Terminator] will prepend the msg.
     * If option USER_FORMATTING is selected, [User] will prepend the msg.
     * @param msg Message to be printed.
     * @param option TERMINATOR_FORMATTING or USER_FORMATTING.
     * @return String with prepended heading.
     */
    public static String formatWithHeading(String msg, Integer option){
        String prepend = "";
        switch (option){
        case TERMINATOR_FORMATTING:
            prepend = "[The Terminator]";
            break;
        case USER_FORMATTING:
            prepend = "[User]";
            break;
        default:
            break;
        }
        return String.format("%s: %s", prepend, msg);
    }

    /**
     * Prints the welcome message to the user.
     */
    public static void printHelloMessage(){
        // @@author ObASCII
        // Reused from https://www.asciiart.eu/computers/computers
        // with minor modifications
        String logo = "              ,---------------------------,\n"
                + "              |  /---------------------\\  |\n"
                + "              | |                       | |\n"
                + "              | |     404               | |\n"
                + "              | |      Send             | |\n"
                + "              | |       Help            | |\n"
                + "              | |        Pls            | |\n"
                + "              |  \\_____________________/  |\n"
                + "              |___________________________|\n"
                + "            ,---\\_____     []     _______/------,\n"
                + "          /         /______________\\           /|\n"
                + "        /___________________________________ /  | ___\n"
                + "        |                                   |   |    )\n"
                + "        |  _ _ _                 [-------]  |   |   (\n"
                + "        |  o o o                 [-------]  |  /    _)_\n"
                + "        |__________________________________ |/     /  /\n"
                + "    /-------------------------------------/|      ( )/\n"
                + "  /-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /\n"
                + "/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/-/ /\n"
                + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(logo);
        System.out.println(formatWithHeading("Hola Amigos, I am the Terminator.", TERMINATOR_FORMATTING));
        System.out.println(formatWithHeading("What would you like me to do?", TERMINATOR_FORMATTING));
        System.out.println("[*] Type \"bye\" if you want to leave!");
    }

    /**
     * Main Function that is called upon program execution
     * @param args System Arguments added to the program
     */
    public static void main(String[] args) {
        // Prints opening message
        printHelloMessage();
        Scanner scanObject = new Scanner(System.in);

        // Continue Running Loop until bye is called
        while(toContinue){
            // Gets user input
            System.out.print(formatWithHeading("", USER_FORMATTING));
            String userInput = scanObject.nextLine();

            // If bye is called, stop loop and print Goodbye message
            if (userInput.equalsIgnoreCase("Bye")){
                toContinue = false;
                printGoodByeMessage();
            } else {
                // Echo back normal input
                System.out.println(formatWithHeading(userInput, TERMINATOR_FORMATTING));
            }
        }
    }
}

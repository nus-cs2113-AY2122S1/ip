package unker.ui;

import java.util.Scanner;
import unker.command.InvalidCommandException;

public class UI {

    private final Scanner scanner;

    private static final UI UI_INSTANCE = new UI();

    private UI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets the user input.
     *
     * @return The user input.
     */
    public String getUserInput() {
        System.out.print("> ");
        String command = scanner.nextLine();
        System.out.println();
        return command;
    }

    /**
     * Prints the welcome banner.
     */
    public void printBanner() {
        String logo =  "  _    _       _             \n"
                + " | |  | |     | |            \n"
                + " | |  | |_ __ | | _____ _ __ \n"
                + " | |  | | '_ \\| |/ / _ \\ '__|\n"
                + " | |__| | | | |   <  __/ |   \n"
                + "  \\____/|_| |_|_|\\_\\___|_|   \n";
        System.out.println("Loading your digital\n" + logo);
        printSection("Harlo, you can call me Unker.", "What can Unker do for you today?");
    }

    /**
     * Prints the bye message.
     */
    public void printByeMessage() {
        printSection("Bye bye, see you soon again arh!");
    }

    /**
     * Prints the message that asks the user for more input.
     */
    public void printRequestMoreCommandsMessage() {
        System.out.println("--");
        System.out.println("Anything else you wan Unker to help you with?");
    }

    /**
     * Prints the error message based on the exception.
     * 
     * @param ex The exception that occurred when executing the command.
     */
    public void printInvalidCommandErrorMessage(InvalidCommandException ex) {
        if (ex.getCommand() != null) {
            printSection(ex.getMessage(), "Format: " + ex.getCommand().getFormat());
        } else {
            printSection(ex.getMessage());
        }
    }

    /**
     * Prints the bot name and given data provided.
     * If no data is provided, it will print a horizontal line with the bot name.
     *
     * @param data The string(s) to print to console.
     */
    public void printSection(String ...data) {
        System.out.println("--// Unker //----------------------------------------------");
        for (String s : data) {
            System.out.println(s);
        }
    }

    /**
     * Returns a singleton instance of UI.
     *
     * @return A singleton instance of UI
     */
    public static UI getUiInstance() {
        return UI_INSTANCE;
    }
}

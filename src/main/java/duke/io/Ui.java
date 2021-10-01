package duke.io;

import duke.TaskHandler;
import duke.exceptions.InvalidCommandException;

import java.util.Scanner;

/**
 * Deals with interactions with user. Takes in user input from the standard
 * input and outputs formatted text to the standard output.
 */
public class Ui {

    Scanner scanner;
    Parser parser;

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.parser = new Parser();
    }

    /** Prints a greeting message to the standard output. */
    public void sayHello() {
        System.out.println("Greetings from" + System.lineSeparator() + logo);
        System.out.println("What can I do for you, my liege?");
    }

    /** Prints a farewell message to the standard output. */
    public void sayBye() {
        System.out.println("Farewell, my liege. Happy hunting!");
        Formatter.printOutputSeparator();
    }

    /**
     * Reads user input from the standard input.
     *
     * @return user input from the standard input
     */
    public String readCommand() {
        Formatter.printInputStart();
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Prints the formatted output to the standard output.
     *
     * @param output the output to be formatted and printed
     */
    public void show(String output) {
        Formatter.printFormattedOutput(output);
    }

    /**
     * Prints a formatted line to the standard output.
     */
    public void showLine() {
        Formatter.printOutputSeparator();
    }

    /**
     * Finds if the user has input an exit command.
     * @param userInput the user input
     * @return true if the input is an exit command and false otherwise
     */
    public boolean isExit(String userInput) {
        return parser.inputIsBye(userInput);
    }

    /**
     * Extracts the command from the user input and acts upon it.
     * For valid commands, handles the tasks and displays the output of the commmand.
     * For invalid commands, displays an invalid command message.
     * @param taskHandler the TaskHandler to operate on the command
     * @param userInput the user input to be operated on
     * @throws IllegalArgumentException if the parameters are missing or invalid
     * @throws InvalidCommandException if the command is not recognised
     */
    public void handleUserInput(TaskHandler taskHandler, String userInput)
            throws IllegalArgumentException, InvalidCommandException {
        Command command = parser.returnCommand(userInput);
        if (command.equals(Command.INVALID)) {
            show("I do not comprehend, my liege.");
            return;
        }
        String output = taskHandler.handleTasks(userInput);
        show(output);
    }
}

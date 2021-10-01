package duke;

import java.util.Scanner;
import duke.Formatter;

/**
 * Deals with interactions with user.
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

    public void sayHello() {
        System.out.println("Greetings from" + System.lineSeparator() + logo);
        System.out.println("What can I do for you, my liege?");
    }

    public void sayBye() {
        Formatter.printFormattedOutput("Farewell, my liege. Happy hunting!");
    }

    public String readCommand() {
        Formatter.printInputStart();
        String userInput = scanner.nextLine();
        return userInput;
    }

    public void show(String output) {
        Formatter.printFormattedOutput(output);
    }

    public void showLine() {
        Formatter.printOutputSeparator();
    }

    public boolean isExit(String userInput) {
        return parser.inputIsBye(userInput);
    }

    public void handleUserInput(TaskHandler taskHandler, String userInput) throws IllegalArgumentException, DukeException {
        Command command = parser.returnCommand(userInput);
        if (command.equals(Command.INVALID)) {
            show("I do not comprehend, my liege.");
            return;
        }
        String output = taskHandler.handleTasks(userInput);
        show(output);
    }
}

package duke;

import java.util.Scanner;
import duke.Formatter;

/**
 * Deals with interactions with user.
 */

public class Ui {
    Scanner scanner;

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void sayHello() {
        System.out.println("Hello from" + System.lineSeparator() + logo);
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
}

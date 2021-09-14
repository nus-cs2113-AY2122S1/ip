package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner inputScanner = new Scanner(System.in);
    private static final String LINE = "  ────────────────────────────────────────────────";

    /**
     * Gets user input and returns it as a String.
     *
     * @return the String containing the user input.
     */
    public String readInput() {
        String line;
        line = inputScanner.nextLine();
        return line;
    }

    /**
     * Prints the output message to the terminal
     *
     * @param outputMessage the output message to be printed
     */
    public void printOutput(String outputMessage) {
        System.out.println(LINE);
        System.out.print(outputMessage);
        System.out.println(LINE);
    }
}

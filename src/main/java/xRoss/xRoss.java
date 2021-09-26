package xRoss;

import xRoss.parser.Parser;
import xRoss.ui.Ui;

import java.util.Scanner;

/**
 * Represents implementation of xRoss chat bot.
 */
public class xRoss {

    private static Ui ui;
    private static Parser parser;

    /**
     * Main function to execute xRoss chat bot.
     *
     * @param args
     */
    public static void main(String[] args) {
        ui = new Ui();
        parser = new Parser();

        ui.printWelcomeMessage();

        TaskManager taskManager = new TaskManager();
        taskManager.readFromFile();

        Scanner in = new Scanner(System.in);

        boolean commandLoop = true;

        while (commandLoop) {
            String inputLine = in.nextLine();
            commandLoop = parser.parseCommand(taskManager, inputLine);
        }
        ui.printExitMessage();
    }
}

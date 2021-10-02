package xRoss;

import xRoss.parser.Parser;
import xRoss.ui.Ui;

import java.util.Scanner;

/**
 * Represents implementation of xRoss chat bot.
 */
public class xRoss {

    /**
     * ui           Ui class instance used to invoke the relevant actions by chat bot.
     * parser       Parser class instance used to parse scanned user input.
     * taskManager  TaskManager class instance used to manage current task list.
     */
    private static Ui ui;
    private static Parser parser;
    private static TaskManager taskManager;

    /**
     * Main function to execute xRoss chat bot.
     *
     * @param args
     */
    public static void main(String[] args) {
        ui = new Ui();
        parser = new Parser();
        taskManager = new TaskManager();

        Scanner in = new Scanner(System.in);

        ui.printWelcomeMessage();

        taskManager.readFromFile();

        boolean commandLoop = true;

        while (commandLoop) {
            String inputLine = in.nextLine();
            commandLoop = parser.parseCommand(taskManager, inputLine);
        }
        ui.printExitMessage();
    }
}

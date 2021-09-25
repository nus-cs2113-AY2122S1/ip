package itachi;

import itachi.command.Command;
import itachi.exception.ItachiException;

import java.util.Scanner;

/**
 * To initialize the key objects needed and run the program
 */
public class Itachi {
    private Storage storage;
    private final TaskList taskList;

    public Itachi() {
        taskList = new TaskList();

        try {
            storage = new Storage();
            storage.loadData();
        } catch (ItachiException e) {
            Ui.printErrorMessage(e);
        }
    }

    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        new Itachi().run();
        Ui.printByeMessage();
    }

    /**
     * Runs the program until user enters the exit command (bye).
     */
    private void run() {
        Scanner in = new Scanner(System.in);

        while (!Command.isExit()) {
            String input = Parser.getUserInput(in);
            String command = Parser.getFirstWordFromCommand(input);

            try {
                TaskManager.parseUserCommand(command, input, taskList, storage);
            } catch (ItachiException e) {
                Ui.printErrorMessage(e);
            }
        }
    }
}
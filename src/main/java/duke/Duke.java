package duke;

import java.io.IOException;

import duke.commands.Command;
import duke.components.ExceptionHandler;
import duke.components.Parser;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exceptions.DukeException;

import java.util.Scanner;

public class Duke {

    private static Ui ui = new Ui();
    private static ExceptionHandler exceptionHandler = new ExceptionHandler();
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();
    private static TaskList tasks = new TaskList();

    /**
     * Class constructor.
     * Initialize tasks with the data from the storage.
     */
    public Duke() {
        try {
            tasks.setTasks(storage.readDataFromFile());
        } catch (IOException e) {
            exceptionHandler.handleOtherExceptions(e);
        }
    }

    /**
     * Reads and executes command from user inputs.
     */
    private static void run() {
        ui.printGreeting();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = ui.getUserInput(in);
            try {
                Command c = parser.parseCommand(userInput);
                c.execute(tasks, ui, storage);
                ui.printLine();
            } catch (DukeException e) {
                exceptionHandler.handleDukeExceptions(e);
            } catch (Exception e) {
                exceptionHandler.handleOtherExceptions(e);
            }
        }
    }

    /**
     * Main entry method to run Duke.
     *
     * @param args Unused params.
     */
    public static void main(String[] args) {
        new Duke();
        Duke.run();
    }

}

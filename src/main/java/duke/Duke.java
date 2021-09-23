package duke;

import duke.command.Command;
import java.util.Scanner;

/**
 * <h1>Duke</h1>
 * The Duke program implements an application that allows users to create a task list using the command line interface.
 * The task list created can store 3 different kinds of tasks: deadline, event and todos
 *
 * @author Jonathan Mui
 * @version 1.0
 * @since 2021-09-21
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Duke initialises the User Interface, Task list and Storage for the app whenever an instance of
     * Duke is created.
     */
    public Duke() {
        ui = new Ui();
        taskList = new TaskList(ui);
        try {
            storage = new Storage();
            storage.loadData(taskList);
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
    }

    /**
     * Runs the program
     */
    public void run() {
        ui.printHelloMessage();
        processUserInput();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Processes the User's Input through the Command Line Interface
     */
    private void processUserInput() {
        boolean isProcessing = true;
        Scanner input = new Scanner(System.in);
        while (isProcessing) {
            String userInput = input.nextLine().stripLeading();
            try {
                Command command = Parser.processCommand(userInput);
                command.executeCommand(taskList, storage);
                isProcessing = !Command.getIsExit();
            } catch (DukeException e) {
               ui.printErrorMessage(e);
            }
        }
        ui.printByeMessage();
    }
}

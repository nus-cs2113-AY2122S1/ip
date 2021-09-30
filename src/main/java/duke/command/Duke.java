package duke.command;

import duke.Ui;
import duke.Parser;
import duke.task.Storage;
import duke.task.TaskList;

import java.io.IOException;


/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    TaskList taskList;

    /**
     * Constructor to initialise the program.
     *
     * @param filePath The path of the storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load(), storage);
        } catch (IOException e) {
            ui.showLoadingError(e);
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String[] parsedFullCommand = Parser.parse(fullCommand);
                execute(taskList, parsedFullCommand);
                isExit = checkIsExit(parsedFullCommand[0]);
            } catch (Exception e) {
                ui.showLoadingError(e);
            }
        }
        ui.showBye();
    }

    /**
     * Checks whether the command is bye and changes the exit condition correspondingly.
     *
     * @param commandWord Command word of the input by the user.
     * @return Exit status of the application.
     */
    public boolean checkIsExit(String commandWord) {
        if (commandWord.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Executes the corresponding command to the input with the correct format by the user.
     *
     * @param taskList Task list that manages all the tasks.
     * @param parsedFullCommand The parsed command that can be fed into the TaskList functions.
     */
    public void execute(TaskList taskList, String[] parsedFullCommand) {
        String commandWord = parsedFullCommand[0];
        switch (commandWord) {
        case "list":
                taskList.listAllTasks();
            break;
        case "done":
            if (parsedFullCommand.length == 2) {
                String indexToBeMarkedDone = parsedFullCommand[1];
                taskList.markAsDone(indexToBeMarkedDone);
            } else {
                ui.showLoadingError();
            }
            break;
        case "todo":
        case "deadline":
        case "event":
            taskList.addTask(parsedFullCommand);
            break;
        case "delete":
            if (parsedFullCommand.length == 2) {
                String indexToBeDeleted = parsedFullCommand[1];
                taskList.deleteTask(indexToBeDeleted);
            } else {
                ui.showLoadingError();
            }
            break;
        case "find":
            taskList.filterTasksByString(parsedFullCommand[1]);
            break;
        case "help" :
            ui.showHelp();
            break;
        case "bye":
            break;
        default:
            ui.showLoadingError();
            break;
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
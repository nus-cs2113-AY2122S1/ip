package duke;

import duke.data.task.TaskList;
import duke.logic.commands.ByeCommand;
import duke.logic.commands.Command;
import duke.logic.commands.CommandResult;
import duke.logic.parser.Parser;
import duke.storage.Storage;
import duke.storage.exceptions.CannotReadFromFileException;
import duke.storage.exceptions.UnableToWriteToFileException;
import duke.ui.Ui;

/**
 * Main class of the Dude bot.
 * Initialises the application and starts user interaction.
 */
public class Main {
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;


    /**
     * Initialises the application by creating the required objects (Ui, Tasks, Storage) and loading data from the
     * storage file, then showing the welcome message.
     */
    private void start() {
        try {
            this.ui = new Ui();
            this.storage = new Storage();
            ui.showWelcome();
            this.tasks = storage.loadTasksFromFile();
            ui.showTasksLoaded(tasks);
        } catch (CannotReadFromFileException | UnableToWriteToFileException e) {
            ui.showMessageFramedWithDivider(e.toString());
        }
    }

    /** Shows the Goodbye message and exits the application.*/
    private void exit() {
        ui.showBye();
        System.exit(0);
    }

    /**
     * Executes the given Command and calls for storage operation if required.
     * @param command Command to be executed
     * @return CommandResult representing result of execution of the command
     */
    private CommandResult executeCommand(Command command) {
        command.setTasks(this.tasks);
        CommandResult result = command.execute();
        if (Command.requiresStorageRewrite(command)) {
            try {
                storage.rewriteTaskListToFile(tasks);
            } catch (UnableToWriteToFileException e) {
                ui.showMessageFramedWithDivider(e.toString());
            }
        }
        return result;
    }

    /**
     * Reads the user input and executes appropriate command.
     * Runs indefinitely until user inputs the Bye command.
     */
    private void enterTaskModeUntilByeCommand() {
        Command command;
        do {
            String userInput = ui.getUserInput();
            command = new Parser().parseCommand(userInput);
            CommandResult result = executeCommand(command);
            ui.showMessageFramedWithDivider(result.toString());
        } while (!ByeCommand.isBye(command));
    }

    /** Entry point of the application. */
    public static void main(String[] args) {
        new Main().run(args);
    }

    /** Runs the application until command is given to exit it. **/
    private void run(String[] args) {
        start();
        enterTaskModeUntilByeCommand();
        exit();
    }

}

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

import static duke.logic.commands.Command.requiresStorageRewrite;

/**
 * Main class of the Dude bot
 */
public class Main {
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;


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

    private void exit() {
        ui.showBye();
        System.exit(0);
    }

    private CommandResult executeCommand(Command command) {
        command.setTasks(this.tasks);
        CommandResult result = command.execute();
        if (requiresStorageRewrite(command)) {
            try {
                storage.rewriteTaskListToFile(tasks);
            } catch (UnableToWriteToFileException e) {
                ui.showMessageFramedWithDivider(e.toString());
            }
        }
        return result;
    }

    private void enterTaskModeUntilByeCommand() {
        Command command;
        do {
            String userInput = ui.getUserInput();
            command = new Parser().parseCommand(userInput);
            CommandResult result = executeCommand(command);
            ui.showMessageFramedWithDivider(result.toString());
        } while (!ByeCommand.isBye(command));
    }

    /**
     * Entry point of the application.
     * Shows welcome message and enters task mode, which handles the user interaction
     */
    public static void main(String[] args) {
        new Main().run(args);
    }

    private void run(String[] args) {
        start();
        enterTaskModeUntilByeCommand();
        exit();
    }

}

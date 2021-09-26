package duke;

import duke.data.task.TaskList;
import duke.logic.commands.ByeCommand;
import duke.logic.commands.Command;
import duke.logic.commands.CommandResult;
import duke.logic.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Main class of the Dude bot
 */
public class Main {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;


    private void start() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        ui.showWelcome();
    }

    private void exit() {
        ui.showBye();
        System.exit(0);
    }

    private CommandResult executeCommand(Command command) {
        command.setTasks(tasks);
        return command.execute();
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

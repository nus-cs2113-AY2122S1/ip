package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * This class is the parent class to all the different command classes, based on the capabilities of
 * the application.
 */
public abstract class Command {
    private String command;

    /**
     * Specifies the type of command by storing the type of command as a string
     *
     * @param command It is a string that represents that command type
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Based on the type of command, different command classes will require the task list, ui and storage
     * to perform different operations
     *
     * @param tasks the task list that stores task for the application which is manipulated based on the command
     * @param ui the user interface that interacts with users which prints messages based on the different commands
     * @param storage the area which data can be read from and written to, to save task list data permanently
     * @throws IOException is thrown when storage fail to store task list data in the files
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Checks if the terminate command is activated. When the terminate command 'bye' is inputted, the program terminates
     *
     * @return true if the command input is 'bye' else return false
     */
    public boolean isExit() {
        return CommandType.isBye(command);
    }
}

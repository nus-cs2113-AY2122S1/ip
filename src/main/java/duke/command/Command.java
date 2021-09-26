package duke.command;

import duke.data.Storage;
import duke.data.TaskList;

import java.io.IOException;

/**
 * Command to provide basic functions to be overwritten by other specific commands.
 */
public abstract class Command {
    private CommandPrefix commandPrefix;
    private boolean isExit = false;

    public Command(CommandPrefix prefix) {
        this.commandPrefix = prefix;
    }

    /**
     * Saves given TaskList to a save file, with filePath represented in Storage.
     * @param tasks TaskList to be saved.
     */
    public void saveListAndPrintDone(TaskList tasks) {
        try {
            Storage.saveList(tasks);
            System.out.print("finished ");
        } catch (IOException e) {
            System.out.println("IOException, please try again!");
        }
    }

    /**
     * Returns true if user enters <code>stop</code> command.
     * Note that this is only applicable for commands with variable user input,
     *  rather than commands that only take in the prefix.
     *
     * e.g <code>Add</code> command can take in multiple <code>Task</code> input,
     *  however <code>List</code> command takes in just the prefix 'list'.
     * @return isExit STOP command input by user.
     */
    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList tasks) {
    }

}

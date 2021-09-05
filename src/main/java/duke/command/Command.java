package duke.command;

import duke.DukeException;
import duke.TaskManager;

public abstract class Command {
    /**
     * Returns if the command type is the exitCommand
     *
     * @return boolean value of whether the command is the exitCommand
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Abstract method that executes the command
     *
     * @param taskManager the taskManager that will be modified (by most Command subclasses)
     * @throws DukeException exception that will be thrown by some Command subclasses
     */
    public abstract void execute(TaskManager taskManager) throws DukeException;
}

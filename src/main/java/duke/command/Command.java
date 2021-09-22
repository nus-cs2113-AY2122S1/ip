package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

public abstract class Command {
    protected static boolean isOver = false;

    /**
     * Provides condition of the loop in Duke class which is later updated in the ExitCommand subclass
     *
     * @return the boolean value to check if the program is over
     */
    public static boolean isExit() {
        return isOver;
    }

    /**
     * Abstract method to execute user commands
     *
     * @param taskList is an object of TaskList that consists of task operations
     * @param storage is an object of Storage that saves current list of tasks
     * @throws DukeException if the subclasses have any exceptions
     */
    public abstract void executeUserCommand(TaskList taskList, Storage storage) throws DukeException;
}

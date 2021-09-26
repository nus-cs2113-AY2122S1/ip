package itachi.command;

import itachi.Storage;
import itachi.TaskList;

/**
 * Used to exit the program
 */
public class ExitCommand extends Command {

    /**
     * Function which makes the condition to exit the program true
     *
     * @return the boolean value to end the program
     */
    public static boolean isExit() {
        return isOver = true;
    }

    /**
     * Makes no changes in the task list or storage
     *
     * @param taskList is an object of TaskList that consists of task operations
     * @param storage  is an object of Storage that saves current list of tasks
     */
    @Override
    public void executeUserCommand(TaskList taskList, Storage storage) {
    }
}

package Duke.Commands;

import Duke.DukeException;
import Duke.Task.Todo;
import Duke.UI;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private static String taskName;

    /**
     * Initialises the name of the task from the input command.
     *
     * @param input The entire line of command entered by the user.
     * @throws DukeException If the name of the task is empty.
     */
    public TodoCommand(String input) throws DukeException {
        taskName = getTodoTaskName(input);
    }

    /**
     * Returns the name of the task from the input given by the user.
     *
     * @param input The entire line of command entered by the user.
     * @return Returns name of the task in String format.
     * @throws DukeException If the name of the task if empty.
     */
    private static String getTodoTaskName(String input) throws DukeException {
        int spaceIndex = input.indexOf(' ');
        String taskName = input.substring(spaceIndex + 1);
        if (taskName.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo task cannot be empty.");
        }
        return taskName;
    }

    /**
     * Adds a todo task to the tasks list.
     * Prints a message to notify the user of the new todo task added.
     */
    @Override
    public void execute() {
        taskList.addTask(new Todo(taskName));
        UI.printNewTaskMsg(taskList.getEntireList());
    }

    /**
     * Adds a todo task to the tasks list without notifying the user.
     */
    @Override
    public void executeFromFile() {
        taskList.addTask(new Todo(taskName));
    }

}

package Duke.Commands;

import Duke.DukeException;
import Duke.Task.Task;
import Duke.Task.Todo;
import Duke.UI;

import java.util.ArrayList;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private static String taskName;

    public TodoCommand(String input) throws DukeException {
        taskName = getTodoTaskName(input);
    }

    private static String getTodoTaskName(String input) throws DukeException {
        int spaceIndex = input.indexOf(' ');
        String taskName = input.substring(spaceIndex + 1);
        if (taskName.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo task cannot be empty.");
        }
        return taskName;
    }

    @Override
    public void execute(ArrayList<Task> tasksList) {
        tasksList.add(new Todo(taskName));
        UI.printNewTaskMsg(tasksList);
    }

    @Override
    public void executeFromFile(ArrayList<Task> tasksList) {
        tasksList.add(new Todo(taskName));
    }

}

package Duke.Commands;

import Duke.DukeException;
import Duke.Task.Todo;
import Duke.UI;

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
    public void execute() {
        taskList.addTask(new Todo(taskName));
        UI.printNewTaskMsg(taskList.getEntireList());
    }

    @Override
    public void executeFromFile() {
        taskList.addTask(new Todo(taskName));
    }

}

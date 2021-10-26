package commands;

import exceptions.TodoException;
import processors.TaskList;
import processors.UI;
import tasks.Todo;

public class TodoCommand extends Command {
    private static final Integer TASK_DATE_DIVIDER = 4;
    private static final Integer TODO_DIVIDER = 5;

    public static UI ui = new UI();

    /**
     * Function saves a task into the list of tasks
     * @param taskList the list of tasks
     * @param line the input string from the user
     * @throws TodoException when the input string is invalid
     */
    public void execute(TaskList taskList, String line) throws TodoException{
        if (line.length() == TASK_DATE_DIVIDER) {
            throw new TodoException("Todo Request Does Not Contain A Description");
        }
        String description = line.substring(TODO_DIVIDER);

        taskList.add(new Todo(description));

        ui.printTaskMessage(taskList.getTask(taskList.getLastIndex()), taskList.size());
    }
}

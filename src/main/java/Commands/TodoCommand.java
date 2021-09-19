package Commands;

import Duke.TaskList;
import Tasks.Todo;

/**
 * Adds a todo task to the task list
 */
public class TodoCommand extends Command {

    public String description;
    public static final String SUCCESS_MESSAGE = "The following todo task has been added\n";

    public TodoCommand(String input){
        description = input;
    }

    @Override
    public String execute(TaskList taskList){
        taskList.add(new Todo(description, false));
        return SUCCESS_MESSAGE + taskList.printTask(-1) + "\n";
    }

}

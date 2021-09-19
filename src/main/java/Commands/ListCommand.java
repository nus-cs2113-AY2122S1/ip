package Commands;

import Duke.TaskList;

/**
 * Displays all tasks currently in the task list
 */
public class ListCommand extends Command{

    public static final String SUCCESS_MESSAGE = "List of Tasks:\n";

    public ListCommand(){
    }

    @Override
    public String execute(TaskList taskList){
        return SUCCESS_MESSAGE + taskList.printAllTasks();
    }
}

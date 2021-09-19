package Commands;

import Tasks.TaskList;

/**
 * Deletes the task with the specified index
 */
public class DeleteCommand extends Command{

    public static int indexToDelete;
    public static final String SUCCESS_MESSAGE = "The following task has been deleted:\n";

    public DeleteCommand(int index){
        indexToDelete = index;
    }

    @Override
    public String execute(TaskList taskList){
        return SUCCESS_MESSAGE + taskList.deleteTask(indexToDelete) + "\n";
    }
}

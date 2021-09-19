package Commands;

import Duke.TaskList;

/**
 * Marks the task at the specified index as complete
 */
public class DoneCommand extends Command{

    public static int indexToMarkAsDone;
    public static final String SUCCESS_MESSAGE = "The following task has been deleted:\n";

    public DoneCommand(int index){
        indexToMarkAsDone = index;
    }

    @Override
    public String execute(TaskList taskList){
        return SUCCESS_MESSAGE + taskList.markTaskAsDone(indexToMarkAsDone) + "\n";
    }
}

package Commands;

import Duke.TaskList;

/**
 * Marks the task at the specified index as complete
 */
public class DoneCommand extends Command{

    public static int indexToMarkAsDone;
    public static final String SUCCESS_MESSAGE = "The following task has been marked as:\n";
    public static final String NO_TASK_BY_THAT_INDEX_MESSAGE = "There is no task by that number.\n";

    public DoneCommand(int index){
        indexToMarkAsDone = index;
    }

    @Override
    public String execute(TaskList taskList){
        try {
            return SUCCESS_MESSAGE + taskList.markTaskAsDone(indexToMarkAsDone) + "\n";
        } catch (IndexOutOfBoundsException e) {
            return NO_TASK_BY_THAT_INDEX_MESSAGE;
        }

    }
}

package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represent a command that remove a task from list
 */
public class DeleteCommand extends Command{

    private final TaskList listManager;

    /**
     * Constructor for DeleteCommand
     *
     * @param userInput Command input by user to process
     * @param listManager Tasklist to interact with list of task
     */
    public DeleteCommand(String userInput, TaskList listManager){
        super(userInput);
        this.listManager = listManager;
    }

    /**
     * Handle removing multiple task from list
     * Extract index of task separated with ",' and put into a list
     * Convert index of task into int by parsing
     * Sort int list into decreasing order so that the correct task will be deleted
     * Removal of task is handled in Tasklist and according to the order by Sorted int list
     *
     * @throws CommandException if task to delete is empty or if deleting a task that non-existent
     */
    @Override
    public void executeCommand()throws CommandException {
        String removeCommand = taskInput.replaceFirst(COMMAND_DELETE,EMPTY_STRING).trim();
        if(removeCommand.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_DELETE_TASK_EMPTY);
        }
        String[] taskDoneArray = removeCommand.split(SEPARATOR);
        ArrayList<Integer> intArray = new ArrayList<>();
        for (String s: taskDoneArray){
            int taskDoneIndex = Integer.parseInt(s) - 1;
            intArray.add(taskDoneIndex);
            if(taskDoneIndex > listManager.getListSize() || taskDoneIndex < 0){
                throw new CommandException(ErrorStaticString.ERROR_DELETE_TASK);
            }
        }
        Collections.sort(intArray, Collections.reverseOrder());
        for (Integer i: intArray) {
            listManager.deleteTask(i);
        }
    }
}

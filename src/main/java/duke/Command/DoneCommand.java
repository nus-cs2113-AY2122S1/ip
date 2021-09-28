package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;
import java.util.ArrayList;

/**
 * Represent a command that set task as completed
 */
public class DoneCommand extends Command{

    private final TaskList listManager;

    /**
     * Constructor for DoneCommand
     *
     * @param userInput Command input by user to process
     * @param listManager Tasklist to interact with list of task
     */
    public DoneCommand(String userInput, TaskList listManager){
        super(userInput);
        this.listManager = listManager;
    }

    /**
     * Handle setting multiple tasks as completed
     * Filter out Command and split index of task into array of string by ","
     * Convert index of task in string into integer
     * Set each task as done in TaskList class
     *
     * @throws CommandException if task to set as complete is empty or if task to set as complete does not exist
     */
    @Override
    public void executeCommand() throws CommandException{
        String removeCommand = taskInput.replaceFirst(COMMAND_COMPLETE_TASK,EMPTY_STRING).trim();
        if(removeCommand.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_DONE_TASK_EMPTY);
        }
        String[] taskDoneArray = removeCommand.split(SEPARATOR);
        System.out.println(MESSAGE_TASK_COMPLETE);
        ArrayList<Integer> intArray = new ArrayList<>();
        for (String s: taskDoneArray) {
            int taskDoneIndex = Integer.parseInt(s) - 1;
            intArray.add(taskDoneIndex);
            if(taskDoneIndex > listManager.getListSize() || taskDoneIndex < 0){
                throw new CommandException(ErrorStaticString.ERROR_DONE_TASK_NOT_IN_LIST);
            }
        }
        for(Integer i: intArray){
            listManager.completeTask(i, false);
        }
    }
}

package duke.Command;

import duke.TaskList.TaskList;

/**
 * Represent a command that clears all task in list
 */
public class ClearCommand extends Command{

    private final TaskList taskList;

    /**
     * Constructor for ClearCommand
     * @param taskInput Command input by user to process
     * @param taskList Tasklist to interact with list of task
     */
    public ClearCommand(String taskInput, TaskList taskList){
        super(taskInput);
        this.taskList = taskList;
    }

    /**
     * Clear list in Tasklist
     */
    @Override
    public void executeCommand(){
        taskList.clearTask();
    }
}

package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;

/**
 * Represent a command to add deadline task
 */
public class AddDeadlineCommand extends Command{

    private final TaskList listManager;

    /**
     * Constructor for Add Deadline Command
     *
     * @param taskInput Command input by user to process
     * @param listManager Tasklist to interact with list of task
     */
    public AddDeadlineCommand(String taskInput, TaskList listManager){
        super(taskInput);
        this.listManager = listManager;
    }

    /**
     * Filter out deadline command, description and date and time of deadline
     * Add deadline task to list via TaskList
     *
     * @throws CommandException if date and time of deadline is empty or if description is empty
     */
    @Override
    public void executeCommand() throws CommandException {
        String taskDescription = taskInput.replaceFirst(COMMAND_ADD_DEADLINE, EMPTY_STRING).trim();
        if(!taskDescription.contains(DEADLINE_DATE)){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_DEADLINE_TIME);
        }
        int startOfDeadlineDate = taskDescription.indexOf(DEADLINE_DATE);
        String deadlineDescription = taskDescription.substring(START_OF_STRING,startOfDeadlineDate).trim();
        if(deadlineDescription.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_DEADLINE_INPUT);
        }
        String inputWithoutDescription = taskDescription.replaceFirst(deadlineDescription, EMPTY_STRING).replaceFirst(DEADLINE_DATE,EMPTY_STRING).strip();
        listManager.addDeadline(deadlineDescription, inputWithoutDescription, false);
    }
}

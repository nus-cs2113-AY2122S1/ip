package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;

/**
 * Represent a command to add Event task
 */
public class AddEventCommand extends Command{

    private final TaskList listManager;

    /**
     * Constructor for Add Deadline Command
     *
     * @param taskInput Command input by user to process
     * @param listManager Tasklist to interact with list of task
     */
    public AddEventCommand(String taskInput, TaskList listManager){
        super(taskInput);
        this.listManager = listManager;
    }

    /**
     * Filter out event command, description and date and time of event
     * Add event task to list via TaskList
     *
     * @throws CommandException if date and time of event is empty or if description is empty
     */
    @Override
    public void executeCommand() throws CommandException {
        String taskDescription = taskInput.replaceFirst(COMMAND_ADD_EVENT, EMPTY_STRING).trim();
        if(!taskDescription.contains(EVENT_TIME)){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_EVENT_TIME);
        }
        int startOfEventTime = taskDescription.indexOf(EVENT_TIME);
        String eventDescription = taskDescription.substring(START_OF_STRING,startOfEventTime).trim();
        if(eventDescription.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_EVENT_INPUT);
        }
        String inputWithoutDescription = taskDescription.replaceFirst(eventDescription, EMPTY_STRING).replaceFirst(EVENT_TIME,EMPTY_STRING).strip();
        listManager.addEvent(eventDescription, inputWithoutDescription,false);
    }
}

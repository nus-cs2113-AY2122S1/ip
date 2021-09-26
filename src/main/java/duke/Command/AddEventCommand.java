package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;

public class AddEventCommand extends Command{

    private final TaskList listManager;

    public AddEventCommand(String taskInput, TaskList listManager){
        super(taskInput);
        this.listManager = listManager;
    }

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

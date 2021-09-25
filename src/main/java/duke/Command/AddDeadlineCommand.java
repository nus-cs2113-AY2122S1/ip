package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.Storage.Storage;
import duke.TaskList.TaskList;

public class AddDeadlineCommand extends Command{

    private final TaskList listManager;

    public AddDeadlineCommand(String taskInput, TaskList listManager){
        super(taskInput);
        this.listManager = listManager;
    }

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
        String deadlineDate = taskDescription.replaceFirst(deadlineDescription, EMPTY_STRING).replaceFirst(DEADLINE_DATE,EMPTY_STRING).strip();
        listManager.addDeadline(deadlineDescription,deadlineDate, false);
    }
}

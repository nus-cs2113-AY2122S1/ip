package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;

public class AddToDoCommand extends Command{

    private final TaskList listManager;

    public AddToDoCommand(String taskInput, TaskList listManager){
        super(taskInput);
        this.listManager = listManager;
    }

    @Override
    public void executeCommand() throws CommandException{
        String removeCommand = taskInput.replaceFirst(COMMAND_ADD_TODO, EMPTY_STRING).trim();
        if(removeCommand.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_TODO_INPUT);
        }
        listManager.addTodo(removeCommand,false);
    }
}

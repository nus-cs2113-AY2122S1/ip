package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;

/**
 * Represent a command to add ToDo task
 */
public class AddToDoCommand extends Command{

    private final TaskList listManager;

    /**
     * Constructor for Add ToDo Command
     *
     * @param taskInput Command input by user to process
     * @param listManager Tasklist to interact with list of task
     */
    public AddToDoCommand(String taskInput, TaskList listManager){
        super(taskInput);
        this.listManager = listManager;
    }

    /**
     * Filter out ToDo command and description
     * @throws CommandException if description is empty
     */
    @Override
    public void executeCommand() throws CommandException{
        String removeCommand = taskInput.replaceFirst(COMMAND_ADD_TODO, EMPTY_STRING).trim();
        if(removeCommand.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_TODO_INPUT);
        }
        listManager.addTodo(removeCommand,false);
    }
}

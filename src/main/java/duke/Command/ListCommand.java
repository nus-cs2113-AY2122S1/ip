package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;

/**
 * Represent a command that handle showing all tasks to user
 */
public class ListCommand extends Command{

    private final TaskList listManager;

    /**
     * Constructor for ListCommand
     * @param userInput Command Input by user to process
     * @param listManager Tasklist to interact with list of task
     */
    public ListCommand(String userInput, TaskList listManager){
        super(userInput);
        this.listManager = listManager;
    }

    /**
     * Process list input to print whole list or list of a certain type
     * Remove the command by replacing command with empty string
     * Remove the need for using separators
     *
     * @throws CommandException if list is empty
     */
    public void executeCommand() throws CommandException {
        if(listManager.getListSize() == 0){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_LIST);
        }
        String removeCommand = taskInput.replaceFirst(COMMAND_VIEW_LIST,EMPTY_STRING).trim();
        if(removeCommand.contains(COMMAND_ADD_TODO)){
            try {
                listManager.printToDo();
            }catch (CommandException e){
                e.handleException();
            }
        }else if(removeCommand.contains(COMMAND_ADD_EVENT)) {
            try {
                listManager.printEvent();
            }catch (CommandException e){
                e.handleException();
            }
        }else if(removeCommand.contains(COMMAND_ADD_DEADLINE)){
            try {
                listManager.printDeadline();
            }catch (CommandException e){
                e.handleException();
            }
        }else {
            listManager.printList();
        }
    }
}

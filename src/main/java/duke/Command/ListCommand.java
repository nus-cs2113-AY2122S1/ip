package duke.Command;

import duke.ArtBot.Logo;
import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;

public class ListCommand extends Command{

    private final TaskList listManager;

    public ListCommand(String userInput, TaskList listManager){
        super(userInput);
        this.listManager = listManager;
    }

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

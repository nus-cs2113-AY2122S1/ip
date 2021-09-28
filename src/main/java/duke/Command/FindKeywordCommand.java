package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;

public class FindKeywordCommand extends Command{

    private final TaskList taskList;

    public FindKeywordCommand(String taskInput, TaskList taskList){
        super(taskInput);
        this.taskList = taskList;
    }

    @Override
    public void executeCommand() throws CommandException{
        if(taskList.getListSize() == 0){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_LIST);
        }
        taskInput = taskInput.replaceFirst(COMMAND_FIND_WORD,EMPTY_STRING).trim();
        if(taskInput.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_LIST);
        }
        taskList.printWord(taskInput);
    }
}

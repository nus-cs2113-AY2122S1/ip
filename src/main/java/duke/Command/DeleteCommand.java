package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;
import java.util.ArrayList;
import java.util.Collections;

public class DeleteCommand extends Command{

    private final TaskList listManager;

    public DeleteCommand(String userInput, TaskList listManager){
        super(userInput);
        this.listManager = listManager;
    }

    @Override
    public void executeCommand()throws CommandException {
        String removeCommand = taskInput.replaceFirst(COMMAND_DELETE,EMPTY_STRING).trim();
        if(removeCommand.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_DELETE_TASK_EMPTY);
        }
        String[] taskDoneArray = removeCommand.split(SEPARATOR);
        ArrayList<Integer> intArray = new ArrayList<>();
        for (String s: taskDoneArray){
            int taskDoneIndex = Integer.parseInt(s) - 1;
            intArray.add(taskDoneIndex);
            if(taskDoneIndex > listManager.getListSize() || taskDoneIndex < 0){
                throw new CommandException(ErrorStaticString.ERROR_DELETE_TASK);
            }
        }
        Collections.sort(intArray, Collections.reverseOrder());
        for (Integer i: intArray) {
            listManager.deleteTask(i);
        }
    }
}

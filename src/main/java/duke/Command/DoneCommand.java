package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;

import java.util.ArrayList;

public class DoneCommand extends Command{

    private final TaskList listManager;

    public DoneCommand(String userInput, TaskList listManager){
        super(userInput);
        this.listManager = listManager;
    }

    @Override
    public void executeCommand() throws CommandException{
        String removeCommand = taskInput.replaceFirst(COMMAND_COMPLETE_TASK,EMPTY_STRING).trim();
        if(removeCommand.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_DONE_TASK_EMPTY);
        }
        String[] taskDoneArray = removeCommand.split(SEPARATOR);
        System.out.println(MESSAGE_TASK_COMPLETE);
        ArrayList<Integer> intArray = new ArrayList<>();
        for (String s: taskDoneArray) {
            int taskDoneIndex = Integer.parseInt(s) - 1;
            intArray.add(taskDoneIndex);
            if(taskDoneIndex > listManager.getListSize() || taskDoneIndex < 0){
                throw new CommandException(ErrorStaticString.ERROR_DONE_TASK_NOT_IN_LIST);
            }
        }
        for(Integer i: intArray){
            listManager.completeTask(i, false);
        }
    }
}

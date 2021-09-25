package duke.Command;

import duke.ArtBot.Logo;
import duke.ErrorHandling.CommandException;
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
        String[] taskDoneArray = removeCommand.split(SEPARATOR);
        ArrayList<Integer> intArray = new ArrayList<Integer>();
        for (String s: taskDoneArray){
            int taskDoneIndex = Integer.parseInt(s);
            intArray.add(taskDoneIndex - 1);
        }
        Collections.sort(intArray, Collections.reverseOrder());
        for (Integer i: intArray) {
            try {
                listManager.deleteTask(i);
            }catch (CommandException e){
                e.handleException();
            }
        }
    }
}

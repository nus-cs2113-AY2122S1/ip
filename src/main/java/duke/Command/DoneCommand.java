package duke.Command;

import duke.ArtBot.Logo;
import duke.ErrorHandling.CommandException;
import duke.TaskList.TaskList;

public class DoneCommand extends Command{

    private final TaskList listManager;

    public DoneCommand(String userInput, TaskList listManager){
        super(userInput);
        this.listManager = listManager;
    }

    @Override
    public void executeCommand(){
        String removeCommand = taskInput.replaceFirst(COMMAND_COMPLETE_TASK,EMPTY_STRING).trim();
        String[] taskDoneArray = removeCommand.split(SEPARATOR);
        System.out.println(MESSAGE_TASK_COMPLETE);
        for (String s: taskDoneArray) {
            int taskDoneIndex = Integer.parseInt(s);
            try {
                listManager.completeTask(taskDoneIndex - 1, false);
            }catch (CommandException e){
                e.handleException();
            }
        }
    }
}

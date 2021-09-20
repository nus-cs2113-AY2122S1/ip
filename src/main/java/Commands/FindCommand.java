package Commands;

import Tasks.TaskList;

public class FindCommand extends Command{

    public static String stringToFind;
    public static final String SUCCESS_MESSAGE = "The following task have been found:\n";

    public FindCommand(String text){
        stringToFind = text;
    }

    @Override
    public String execute(TaskList taskList){
        return SUCCESS_MESSAGE + taskList.findTasksContaining(stringToFind);
    }
}

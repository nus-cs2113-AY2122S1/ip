package Commands;

import Tasks.TaskList;

/**
 * Displays the help message
 */
public class HelpCommand extends Command{

    public static final String SUCCESS_MESSAGE = "The following is the list of commands: \n" +
            "list \nDisplays the current tasks. \n" +
            "todo <task> \nAdds <task> to the task list \n" +
            "deadline <task> /by <date>\nAdds <task> to task list with deadline <date> \n" +
            "event <task> /at <time>\nAdds <task> to task list with time <time> \n" +
            "done <task index>\nMarks task number <task index> as done. <task index> should be an integer\n" +
            "exit\nExits the program\n";
    public HelpCommand(){
    }

    @Override
    public String execute(TaskList taskList){
        return SUCCESS_MESSAGE;
    }
}

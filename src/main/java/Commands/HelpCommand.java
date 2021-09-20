package Commands;

import Tasks.TaskList;

/**
 * Displays the help message
 */
public class HelpCommand extends Command{

    public static final String SUCCESS_MESSAGE = "The following is the list of commands: \n" +
            "list \nDisplays the current tasks. \n" +
            "todo <task> \nAdds <task> to the task list \n" +
            "deadline <task> /by <YYYY-MM-DD>T<HH:MM:SS>\nAdds <task> to task list with deadline <datetime> \n" +
            "event <task> /at <YYYY-MM-DD>T<HH:MM:SS>\nAdds <task> to task list with time <datetime> \n" +
            "done <task index>\nMarks task number <task index> as done. <task index> should be an integer\n" +
            "find <text to search>\nSearches for Tasks containing the given text string\n" +
            "exit\nExits the program\n";
    public HelpCommand(){
    }

    @Override
    public String execute(TaskList taskList){
        return SUCCESS_MESSAGE;
    }
}

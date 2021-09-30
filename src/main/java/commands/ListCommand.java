package commands;

import TextUi.TextUi;

public class ListCommand extends Command{

    public static final String COMMAND_WORD = "list";

    public static final String COMMAND_DESC = "Displays all tasks as a list with index numbers.";

    public void execute() {
        TextUi.showTasksListView(taskList);
    }
}

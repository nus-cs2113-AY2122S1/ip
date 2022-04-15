package alfred.command;

import alfred.ui.TextUi;

public class ListTasksCommand extends Command {
    /**
     * This method lists out the Tasks in the TaskList.
     */
    public void execute() {
        TextUi.listTasks(taskList);
    }
}

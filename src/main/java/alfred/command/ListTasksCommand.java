package alfred.command;

import alfred.ui.TextUi;

public class ListTasksCommand extends Command {
    public void execute() {
        TextUi.listTasks(taskList);
    }
}

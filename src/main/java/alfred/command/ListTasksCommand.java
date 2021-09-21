package alfred.command;

import alfred.ui.TextUi;

public class ListTasksCommand extends Command {
    private final int numberOfTasks;

    public ListTasksCommand(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void execute() {
        TextUi.listTasks(numberOfTasks, taskList);
    }
}

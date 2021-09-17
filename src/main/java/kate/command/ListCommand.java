package kate.command;

import kate.common.Message;
import kate.storage.Storage;
import kate.task.Task;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

public class ListCommand extends Command {

    public ListCommand() {
        this.isExit = false;
    }

    @Override
    public void execute(KateUI ui, Storage storage, TaskList tasks) {
        StringBuilder allTasks = new StringBuilder();
        String taskHeading = Message.TEXT_INDENTATION + "Here are the tasks in your list:\n";
        allTasks.append(taskHeading);
        for (int i = 0; i < tasks.getTaskSize(); ++i) {
            int numberedBullets = i + 1;
            Task curTask = tasks.getCurrentTask(i);
            String taskRow = Message.TEXT_INDENTATION + numberedBullets + ". " + curTask.getTaskInfo() + "\n";
            allTasks.append(taskRow);
        }
        ui.printMessage(String.valueOf(allTasks));
    }
}

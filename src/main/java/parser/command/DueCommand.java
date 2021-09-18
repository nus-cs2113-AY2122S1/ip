package parser.command;

import storage.Storage;
import task.Task;
import task.TaskManager;
import task.TimedTask;
import ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DueCommand extends Command{
    public DueCommand(HashMap<String, String> params) {
        super(params);
    }

    @Override
    public void execute(Storage storage, TaskManager taskMgr, Ui ui) {
        LocalDateTime now = LocalDateTime.now();

        ArrayList<Task> overdueTasks = new ArrayList<>();
        Iterator<Task> it = taskMgr.getAllTasks();

        while (it.hasNext()) {
            Task task = it.next();
            if (task instanceof TimedTask) {
                LocalDateTime taskDueTime = ((TimedTask) task).getTime();
                if (!task.getDone() && taskDueTime.isBefore(now)) {
                    overdueTasks.add(task);
                }
            }
        }

        ui.printMessage(taskMgr.listTasks(overdueTasks));
    }
}

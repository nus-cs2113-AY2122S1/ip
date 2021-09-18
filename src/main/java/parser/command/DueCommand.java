package parser.command;

import storage.Storage;
import task.Task;
import task.TaskManager;
import ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class DueCommand extends Command{
    public DueCommand(HashMap<String, String> params) {
        super(params);
    }

    @Override
    public void execute(Storage storage, TaskManager taskMgr, Ui ui) {
        LocalDateTime now = LocalDateTime.now();
        String overdueTasksString = taskMgr.getOverdueTasks(now);
        ui.printMessage(overdueTasksString);
    }
}

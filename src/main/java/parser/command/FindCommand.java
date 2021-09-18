package parser.command;

import storage.Storage;
import task.Task;
import task.TaskManager;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FindCommand extends Command{
    public FindCommand(HashMap<String, String> params) {
        super(params);
    }

    @Override
    public void execute(Storage storage, TaskManager taskMgr, Ui ui) {
        Iterator<Task> allTasksIt = taskMgr.getAllTasks();
        ArrayList<Task> matchedTasks = new ArrayList<>();
        String keyword = params.get("main");

        if(keyword == null) {
            ui.printErrorMessage("No keyword specified");
            return;
        }

        while(allTasksIt.hasNext()) {
            Task task = allTasksIt.next();
            String taskDesc = task.getName();

            if(taskDesc.contains(keyword)) {
                matchedTasks.add(task);
            }
        }

        if(matchedTasks.size() == 0) {
            ui.printMessage("No matching tasks");
            return;
        }

        StringBuilder sb = new StringBuilder();
        Iterator<Task> it = matchedTasks.iterator();
        int counter = 1;

        while(it.hasNext()) {
            Task currTask = it.next();
            sb.append(String.format("%d. %s\n",
                    counter++,
                    currTask));
        }

        ui.printSuccessMessage("Here are the matching tasks in your list");
        ui.printMessage(sb.toString());
    }
}

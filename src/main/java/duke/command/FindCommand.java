package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Task;

public class FindCommand extends Command{
    
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        boolean isFound = false;
        Task task;
        for (int i = 0; i < list.size(); i += 1) {
            task = list.getList().get(i);
            if (task.getName().toLowerCase().contains(keyword)) {
                if (!isFound) {
                    ui.printFoundTaskMessage();
                    isFound = true;
                }
                ui.printTask(task, i+1);
            }
        }
        if (!isFound) {
            ui.printNoTaskFoundMessage();
        } else {
            ui.printLine();
        }
     }

    @Override
    public boolean isExit() {
        return false;
    }
}
package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class ListCommand extends Command {

    public ListCommand() {
        this.isExit = false;
        this.fullCommand = "";
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        int taskNumber = 1;
        for (Task task : tasks.getTasks()) {
            System.out.println((taskNumber) + "." + task);
            taskNumber++;
        }
    }

}

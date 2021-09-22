package duke.command;

import Type.Task;
import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

import java.io.IOException;

public abstract class Command {
    private CommandPrefix commandPrefix;
    private boolean isExit = false;

    public Command(CommandPrefix prefix) {
        this.commandPrefix = prefix;
    }

    public void saveListAndPrintDone(TaskList tasks) {
        try {
            Storage.saveList(tasks);
            System.out.println("finished ");
        } catch (IOException e) {
            System.out.println("IOException, please try again!");
        }
    }

    public boolean isExit() {
        return isExit;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {
    }

}

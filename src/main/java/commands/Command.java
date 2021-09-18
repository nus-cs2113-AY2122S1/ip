package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public abstract class Command {
    public static final String addTaskMessage = "Got it. I've added this task:\n%1$s\nNow you have"
            + " %2$o tasks in the list.";

    String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract String help();

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public abstract void execute(Ui ui, ArrayList<Task> tasks, Storage storage);
}

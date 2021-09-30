package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents the generic command. Helps to declare the abstract methods. It is inherited by all
 * other commands.
 */

public abstract class Command {
    public static final String addTaskMessage = "Got it. I've added this task:\n%1$s\nNow you have"
            + " %2$o tasks in the list.";

    String command;
    boolean isExit;

    public Command(String command) {
        this.command = command;
        this.isExit = false;
    }

    // Return null as default as not all commands needs a help function.
    public String help() {
        return null;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public abstract void execute(Ui ui, ArrayList<Task> tasks, Storage storage);
}

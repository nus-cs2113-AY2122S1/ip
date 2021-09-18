package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

public class ByeCommand extends Command {

    public ByeCommand(String command) {
        super(command);
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public void execute(Ui ui, ArrayList<Task> tasks, Storage storage) {
        storage.saveData(ui, tasks);
        ui.customPrint("Bye. Hope to see you again soon!");
    }
}

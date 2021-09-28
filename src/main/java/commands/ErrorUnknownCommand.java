package commands;
import storage.Storage;
import task.type.TaskList;
import ui.UI;

public class ErrorUnknownCommand extends Command {
    String description;

    public ErrorUnknownCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasksList, Storage storage) {
        UI.printErrorUnknownCommandMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

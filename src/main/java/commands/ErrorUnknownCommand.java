package commands;
import storage.Storage;
import task.type.TaskList;
import ui.UI;

public class ErrorUnknownCommand extends Command {
    String description;


    public ErrorUnknownCommand(String description) {
        this.description = description;
    }

    /**
     * Displays error message for a command which cannot be recognized.
     *
     * @param tasksList Object of TaskList.
     * @param storage Object of Storage.
     */
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        UI.printErrorUnknownCommandMessage();
    }

    /**
     * To check whether to exit the application
     *
     * @return false To take input from user again.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

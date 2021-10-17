package commands;
import storage.Storage;
import task.type.TaskList;
import ui.UI;
import java.io.IOException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasksList, Storage storage) {
        try {
            Storage.saveData(tasksList.getTasks());
            UI.printBye();
        } catch (IOException e) {
            UI.printErrorMessageFailedToWrite();
        }
    }

    /**
     * To check whether to exit application.
     *
     * @return true Application is to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

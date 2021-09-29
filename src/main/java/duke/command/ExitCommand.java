package duke.command;

import duke.UI.Message;
import duke.exception.DoneListIndexError;
import duke.tasks.TaskList;

import static duke.database.Database.saveFile;

public class ExitCommand extends Command{

    /**
     * Executes ExitCommand
     *
     * @param tasks TaskList
     * @return appropriate message to be sent to user
     */
    @Override
    public String execute(TaskList tasks) {
        isBye = true;
        returnString = Message.printExit();
        saveFile(tasks);
        return returnString;
    }
}

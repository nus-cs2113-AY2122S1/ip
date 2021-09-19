package duke.command;

import duke.UI.Message;
import duke.tasks.TaskList;

import static duke.database.Database.saveFile;

public class ExitCommand extends Command{

    @Override
    public String execute(TaskList tasks) {
        isBye = true;
        returnString = Message.printExit();
        saveFile(tasks);
        return returnString;
    }
}

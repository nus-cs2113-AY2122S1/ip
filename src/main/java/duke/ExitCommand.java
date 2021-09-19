package duke;

import duke.tasks.Task;
import java.util.ArrayList;

import static duke.Database.saveFile;

public class ExitCommand extends Command{

    @Override
    public String execute(TaskList tasks) {
        isBye = true;
        returnString = Message.printExit();
        saveFile(tasks);
        return returnString;
    }
}

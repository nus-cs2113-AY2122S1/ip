package duke.control.command;

import duke.control.Storage;
import duke.control.TaskList;

public class DateCommand extends Command {
    @Override
    public void executeCommand(TaskList list, String input, Storage storage) {
        list.printTasksOnDate(input);
    }
}

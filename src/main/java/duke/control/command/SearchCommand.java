package duke.control.command;

import duke.control.Storage;
import duke.control.TaskList;

public class SearchCommand extends Command {

    @Override
    public void executeCommand(TaskList list, String input, Storage storage) {
        list.printSearchList(input);
    }
}

package duke.control.command;

import duke.control.Storage;
import duke.control.TaskList;

public abstract class Command {
    public void executeCommand(TaskList list, String input, Storage storage){};
}

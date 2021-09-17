package duke.control.command;

import duke.control.TaskList;

public abstract class Command {
    public void executeCommand(TaskList list, String input){};
}

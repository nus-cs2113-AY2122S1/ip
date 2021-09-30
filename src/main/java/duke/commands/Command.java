package duke.commands;

import duke.TaskList;
import duke.Ui;

public abstract class Command {

    /**
     * Constructor for Command
     * Represents a Command made.
     */

    public void execute(TaskList tasks, Ui ui){};

    public abstract boolean isExit();
}

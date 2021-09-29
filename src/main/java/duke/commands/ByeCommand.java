package duke.commands;

import duke.tasklist.TaskList;

/**
 * Terminates the program.
 */
public class ByeCommand extends Command {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "bye";

    private static final String MESSAGE_FAREWELL = "Bye. Hope to see you again soon!";

    @Override
    public String execute(TaskList tasks) {
        return MESSAGE_FAREWELL;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

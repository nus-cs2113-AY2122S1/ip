package duke.commands;

import duke.task.Deadline;

public class DeadlineCommand extends AddTaskCommand {
    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String description, String by) {
        toAdd = new Deadline(description, by);
    }
}

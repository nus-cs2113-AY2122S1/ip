package duke.commands;

import duke.data.task.Deadline;

public class DeadlineCommand extends Command{

    public static final String COMMAND_WORD = "deadline";

    protected String description;
    protected String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute() {

    }
}
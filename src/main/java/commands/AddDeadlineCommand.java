package commands;

import task.Deadline;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "deadline: Adds a deadline to the task list.\n"
            + "Parameters: DESCRIPTION d/DATE t/TIME\n"
            + "Example: deadline homework /d2020-12-31 /t09:00\n";
    public static final String MESSAGE_SUCCESS = "New deadline added: %1$s";
    private final Deadline toAdd;

    public AddDeadlineCommand(String description, boolean isDone, LocalDateTime dateTime) {
        this.toAdd = new Deadline(description, isDone, dateTime);
    }

    @Override
    public CommandResult execute() {
        taskManager.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
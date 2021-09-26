package commands;

import task.Event;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = "event: Adds an event to the task list. \n"
            + "Parameters: DESCRIPTION d/DATE t/TIME\n"
            + "Example: event Lecture /d2020-12-31 /t09:00\n";
    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    private final Event toAdd;

    public AddEventCommand(String description, boolean isDone, LocalDateTime dateTime) {
        this.toAdd = new Event(description, isDone, dateTime);
    }

    @Override
    public CommandResult execute() {
        taskManager.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}

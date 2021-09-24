package commands;

import task.Event;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String KEYWORD = "/at";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the task list. \n"
            + "Example: " + COMMAND_WORD + " {DESCRIPTION}" + KEYWORD + " {TIME}";
    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public final Event toAdd;

    public AddEventCommand(String description, boolean isDone, String date) {
        this.toAdd = new Event(description, isDone, date);
    }

    @Override
    public CommandResult execute() {
        taskManager.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}

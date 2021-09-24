package commands;

import task.Deadline;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String KEYWORD = "/by";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to the task list. \n"
            + "Example: " + COMMAND_WORD + " {DESCRIPTION}" + KEYWORD + " {TIME}";
    public static final String MESSAGE_SUCCESS = "New deadline added: %1$s";
    public final Deadline toAdd;

    public AddDeadlineCommand(String description, boolean isDone, String date) {
        this.toAdd = new Deadline(description, isDone, date);
    }

    @Override
    public CommandResult execute() {
        taskManager.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
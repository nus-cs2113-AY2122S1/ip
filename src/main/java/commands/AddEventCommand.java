package commands;

import task.Event;

import java.time.LocalDateTime;

import static parser.Parser.DATE_KEYWORD;
import static parser.Parser.TIME_KEYWORD;

public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the task list. \n"
            + "Example: " + COMMAND_WORD + " {DESCRIPTION}" + DATE_KEYWORD + " {yyyy-mm-dd}" + TIME_KEYWORD + "{hh:mm}";
    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public final Event toAdd;

    public AddEventCommand(String description, boolean isDone, LocalDateTime dateTime) {
        this.toAdd = new Event(description, isDone, dateTime);
    }

    @Override
    public CommandResult execute() {
        taskManager.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}

package commands;

import task.Deadline;

import java.time.LocalDateTime;

import static parser.Parser.DATE_KEYWORD;
import static parser.Parser.TIME_KEYWORD;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "\ndeadline: Adds a deadline to the task list. \n"
            + "\tFormat: deadline {DESCRIPTION}" + DATE_KEYWORD + " {yyyy-mm-dd}" + TIME_KEYWORD + "{hh:mm}\n"
            + "\tExample: deadline homework /d2020-12-31 /t09:00\n";
    public static final String MESSAGE_SUCCESS = "New deadline added: %1$s";
    public final Deadline toAdd;

    public AddDeadlineCommand(String description, boolean isDone, LocalDateTime dateTime) {
        this.toAdd = new Deadline(description, isDone, dateTime);
    }

    @Override
    public CommandResult execute() {
        taskManager.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
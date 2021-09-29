package duke.logic.commands;

import duke.data.task.Event;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 *  Represents the command that when executed, adds a new Event to the TaskList
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + " X /at " + Ui.DATE_TIME_FORMAT + Ui.QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Add event X with date and time details";
    public static final String MESSAGE_INVALID_FORMAT =  "Invalid format! Please input a date and time, "
            + Ui.LS + "in the format " + MESSAGE_COMMAND_FORMAT + ", where X is the event!";
    public static final String COMMAND_PREFIX = "at";
    private static final String MESSAGE_SUCCESS = "Added to list: %1$s " + Ui.LS + "Current number of tasks: %2$d";

    private Event toAdd;

    public AddEventCommand(String description, LocalDateTime dateAndTime) {
        this.toAdd = new Event(description, dateAndTime);
    }

    @Override
    public CommandResult execute() {
        super.tasks.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, super.tasks.getNumTasks()));
    }

}

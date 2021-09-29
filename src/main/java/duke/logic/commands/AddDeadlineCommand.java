package duke.logic.commands;

import duke.data.task.Deadline;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 *  Represents the command that when executed, adds a new Deadline to the TaskList
 */
public class AddDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_COMMAND_FORMAT = Ui.QUOTATION + COMMAND_WORD + " X /by " + Ui.DATE_TIME_FORMAT + Ui.QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Add deadline X with date and time details";
    public static final String MESSAGE_INVALID_FORMAT =   "Invalid format! Please input a deadline, "
            + Ui.LS + "in the format " + MESSAGE_COMMAND_FORMAT + ", where X is the task!";
    public static final String COMMAND_PREFIX = "by";
    private static final String MESSAGE_SUCCESS = "Added to list: %1$s " + Ui.LS + "Current number of tasks: %2$d";

    private Deadline toAdd;

    public AddDeadlineCommand(String description, LocalDateTime dateAndTime) {
        this.toAdd = new Deadline(description, dateAndTime);
    }

    @Override
    public CommandResult execute() {
        super.tasks.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.toString(), super.tasks.getNumTasks()));
    }

}

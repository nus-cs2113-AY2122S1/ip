package duke.logic.commands;

import duke.data.task.Deadline;

import java.time.LocalDateTime;

import static duke.ui.Ui.DATE_TIME_FORMAT;
import static duke.ui.Ui.LS;
import static duke.ui.Ui.QUOTATION;

public class AddDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_COMMAND_FORMAT = QUOTATION + COMMAND_WORD + " X /by " + DATE_TIME_FORMAT + QUOTATION;
    public static final String MESSAGE_COMMAND_DESCRIPTION = MESSAGE_COMMAND_FORMAT + " : Add task X with deadline Y";
    public static final String MESSAGE_INVALID_FORMAT =   "Invalid format! Please input a deadline, "
            + LS + "in the format " + MESSAGE_COMMAND_FORMAT + ", where X is the task!";
    public static final String COMMAND_PREFIX = "by";
    private static final String MESSAGE_SUCCESS = "Added to list: %1$s " + LS + "Current number of tasks: %2$d";

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

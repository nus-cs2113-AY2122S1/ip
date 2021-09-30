package duke.commands;

import java.time.LocalDateTime;

import duke.task.Deadline;

/**
 * Adds a {@link Deadline} item to the task list.
 */
public class DeadlineCommand extends AddTaskCommand {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "deadline";

    /**
     * Instantiates a {@link Deadline} item with the specified {@code description} and deadline.
     *
     * @param description Description of the task.
     * @param by Deadline for the task.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        toAdd = new Deadline(description, by);
    }
}

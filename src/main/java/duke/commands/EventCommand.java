package duke.commands;

import java.time.LocalDateTime;

import duke.task.Event;

/**
 * Adds an {@link Event} item to the task list.
 */
public class EventCommand extends AddTaskCommand {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "event";

    /**
     * Instantiates an {@link Event} item with the specified {@code description} and event date/time.
     *
     * @param description Description of the task.
     * @param at Date and time of the event.
     */
    public EventCommand(String description, LocalDateTime at) {
        toAdd = new Event(description, at);
    }
}

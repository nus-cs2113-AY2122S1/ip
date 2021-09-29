package duke.commands;

import duke.task.ToDo;

/**
 * Adds a {@link ToDo} item to the task list.
 */
public class ToDoCommand extends AddTaskCommand {
    /** Unique word associated with the command. */
    public static final String COMMAND_WORD = "todo";

    /**
     * Instantiates a {@link ToDo} item with the specified {@code description}.
     *
     * @param description Description of the task.
     */
    public ToDoCommand(String description) {
        toAdd = new ToDo(description);
    }
}

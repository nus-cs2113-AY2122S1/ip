package duke.commands;

import duke.task.ToDo;

public class ToDoCommand extends AddTaskCommand {
    public static final String COMMAND_WORD = "todo";

    public ToDoCommand(String description) {
        toAdd = new ToDo(description);
    }
}

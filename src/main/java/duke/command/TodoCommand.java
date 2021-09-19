package duke.command;

import duke.DukeException;
import duke.TaskManager;

public class TodoCommand implements Command {
    public static final CommandType type = CommandType.TODO;

    private final String todo;

    public TodoCommand(String todo) {
        this.todo = todo;
    }

    @Override
    public void run() throws DukeException {
        TaskManager.addTodo(todo);
    }

    @Override
    public CommandType getType() {
        return type;
    }
}

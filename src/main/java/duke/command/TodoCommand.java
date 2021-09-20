package duke.command;

import duke.DukeException;
import duke.TaskManager;
import duke.task.Task;

public class TodoCommand implements Command {
    public static final CommandType type = CommandType.TODO;

    private final String todo;

    public TodoCommand(String todo) {
        this.todo = todo;
    }

    @Override
    public void run(boolean printMessage) throws DukeException {
        Task task = TaskManager.addTodo(todo);
        if (printMessage) {
            System.out.print("Got it. I've added this task:\n");
            System.out.printf("   %s\n", task);
            System.out.printf("Now you have %d tasks in the list.\n", TaskManager.getTasklistSize());
        }
    }

    @Override
    public CommandType getType() {
        return type;
    }
}

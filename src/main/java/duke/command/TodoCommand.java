package duke.command;

import duke.DukeException;
import duke.TaskManager;
import duke.task.Task;

public class TodoCommand implements Command {
    private static final CommandType type = CommandType.TODO;
    private final String todo;

    /**
     * Todo command constructor
     *
     * @param todo Title of todo
     */
    public TodoCommand(String todo) {
        this.todo = todo;
    }


    /**
     * Adds todo to task list and saves changes to data file
     *
     * @param printMessage Print message to user on executing command
     * @throws DukeException If todo is empty
     */
    @Override
    public void run(boolean printMessage) throws DukeException {
        Task task = TaskManager.addTodo(todo);
        if (printMessage) {
            System.out.print("Got it. I've added this task:\n");
            System.out.printf("   %s\n", task);
            System.out.printf("Now you have %d tasks in the list.\n", TaskManager.getTasklistSize());
        }
    }

    /**
     * @return Type of command
     */
    @Override
    public CommandType getType() {
        return type;
    }
}

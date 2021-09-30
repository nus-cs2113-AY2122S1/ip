package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
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
        Task task = TaskList.addTodo(todo);
        if (printMessage) {
            Ui.printTaskAddedMessage(task);
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

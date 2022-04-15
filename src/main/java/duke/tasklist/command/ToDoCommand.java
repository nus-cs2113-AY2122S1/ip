package duke.tasklist.command;

import duke.tasklist.TaskManager;

/**
 * Class responsible to add 'ToDo' type task.
 */
public class ToDoCommand extends Command {

    protected String component;

    /**
     * Created a ToDoCommand object, sets TaskManager object and command components.
     * @param taskManager TaskManager object used to perform operations.
     * @param component String containing details of todo task to add.
     */
    public ToDoCommand(TaskManager taskManager, String component) {
        super(taskManager);
        this.component = component;
    }

    /**
     * Creates a todo task.
     */
    public void execute() {
        taskManager.addToDoTask(component);
    }
}

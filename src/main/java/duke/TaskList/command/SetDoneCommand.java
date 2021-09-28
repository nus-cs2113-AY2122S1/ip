package duke.TaskList.command;

import duke.TaskList.TaskManager;

public class SetDoneCommand extends Command{

    /**
     * Creates a SetDoneCommand object, sets TaskManager object and command components.
     * @param taskManager TaskManager object used to perform operations.
     * @param components String containing indexes of tasks to be set as done.
     */
    public SetDoneCommand(TaskManager taskManager, String components) {
        super(taskManager);
        this.components = components;
    }

    public void execute() {
        taskManager.setAsDone(components);
    }
}

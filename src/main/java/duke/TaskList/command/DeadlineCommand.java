package duke.TaskList.command;

import duke.TaskList.TaskManager;
import duke.Ui.Parser;

public class DeadlineCommand extends Command{

    protected Parser parser;
    protected String component;

    /**
     * Creates a new DeadlineTask object, sets TaskManager and Parser object, and command component.
     * @param taskManager TaskManager object used to perform operations.
     * @param parser Parser object used to perform parsing operations.
     * @param component String containing details of deadline task to add.
     */
    public DeadlineCommand(TaskManager taskManager, Parser parser, String component) {
        super(taskManager);
        this.parser = parser;
        this.component = component;
    }

    /**
     * Creates and adds a new deadline to the list of tasks.
     */
    public void execute() {
        taskManager.addDeadlineTask(parser, component);
    }
}

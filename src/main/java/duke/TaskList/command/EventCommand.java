package duke.TaskList.command;

import duke.TaskList.TaskManager;
import duke.Ui.Parser;

public class EventCommand extends Command{

    protected Parser parser;

    /**
     * Creates a EventCommand object, sets TaskManager object and command components.
     * @param taskManager TaskManager object used to perform operations.
     * @param parser Parser object used to perform parsing operations.
     * @param component String containing details of event to add.
     */
    public EventCommand(TaskManager taskManager, Parser parser, String component) {
        super(taskManager, component);
        this.parser = parser;
    }

    /**
     * Creates and adds a new event to the list of tasks.
     */
    public void execute() {
        taskManager.addEventTask(parser, component);
    }
}

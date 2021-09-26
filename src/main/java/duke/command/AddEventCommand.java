package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Command to add an Event.
 */
public class AddEventCommand extends Command {
    private static final String COMMAND_EVENT = "event";
    private static final String PARAMETER_AT = "/at";
    private final String line;

    /**
     * Constructs an AddEventCommand with the user input.
     *
     * @param line The user input.
     */
    public AddEventCommand(String line) {
        this.line = line;
    }

    /**
     * Executes the AddEventCommand by adding an Event to the
     * ArrayList of tasks.
     *
     * @param taskManager TaskManager object used to add the Event task.
     * @param ui          Ui object used to print message.
     * @throws DukeException If the command description is empty.
     */
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        String rawDescription = Parser.parseDescription(line, COMMAND_EVENT);
        int indexAt = rawDescription.indexOf(PARAMETER_AT);
        String[] details = Parser.splitTaskDetails(rawDescription, COMMAND_EVENT, indexAt);
        Task addedTask = new Event(details[0], details[1]);
        taskManager.addTask(addedTask);
        ui.printAddTask(addedTask, taskManager);
    }
}

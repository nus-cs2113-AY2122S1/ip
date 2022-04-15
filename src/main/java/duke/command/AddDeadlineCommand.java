package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Command to add a Deadline.
 */
public class AddDeadlineCommand extends Command {
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String PARAMETER_BY = "/by";
    private final String line;

    /**
     * Constructs an AddDeadlineCommand with the user input.
     *
     * @param line The user input.
     */
    public AddDeadlineCommand(String line) {
        this.line = line;
    }

    /**
     * Executes the AddDeadlineCommand by adding a Deadline to the
     * ArrayList of tasks.
     *
     * @param taskManager TaskManager object used to add the Deadline task.
     * @param ui          Ui object used to print message.
     * @throws DukeException If the command description is empty.
     */
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        String rawDescription = Parser.parseDescription(line, COMMAND_DEADLINE);
        int indexBy = rawDescription.indexOf(PARAMETER_BY);
        String[] details = Parser.splitTaskDetails(rawDescription, COMMAND_DEADLINE, indexBy);
        Task addedTask = new Deadline(details[0], details[1]);
        taskManager.addTask(addedTask);
        ui.printAddTask(addedTask, taskManager);
    }
}

package duke.commands;

import duke.components.Parser;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.IllegalDateTimeException;
import duke.exceptions.IllegalDeadlineException;
import duke.task.Deadline;

import java.io.IOException;

import static duke.constants.TaskConstants.PREFIX_BY_DEADLINE;

/**
 * A representation of the command for adding a Deadline.
 */
public class AddDeadlineCommand extends Command {

    String commandArgs;
    Parser parser = new Parser();

    /**
     * Class constructor specifying the command arguments.
     *
     * @param commandArgs The command arguments.
     */
    public AddDeadlineCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        int indexOfByPrefix = commandArgs.indexOf(PREFIX_BY_DEADLINE);
        if (indexOfByPrefix == -1) {
            throw new IllegalDeadlineException();
        }
        String description = commandArgs.substring(0, indexOfByPrefix).trim();
        String timeInput = commandArgs.substring(indexOfByPrefix + 3).trim();
        String by;
        try {
            by = parser.formatDateTime(timeInput);
        } catch (IllegalDateTimeException e) {
            throw new IllegalDateTimeException();
        }
        Deadline toBeAddedDeadline = new Deadline(description, by);
        tasks.addTask(toBeAddedDeadline);
        ui.printConfirmAdd(toBeAddedDeadline, tasks.getNumberOfTasks());
        storage.saveDataToFile(tasks);
    }
}

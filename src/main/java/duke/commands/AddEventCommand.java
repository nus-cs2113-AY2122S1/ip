package duke.commands;

import duke.components.Parser;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.IllegalDateTimeException;
import duke.exceptions.IllegalEventException;
import duke.task.Event;

import java.io.IOException;

import static duke.constants.TaskConstants.PREFIX_TIME_EVENT;

/**
 * A representation of the command for adding an Event.
 */
public class AddEventCommand extends Command {

    String commandArgs;
    Parser parser = new Parser();

    /**
     * Class constructor specifying the command arguments.
     *
     * @param commandArgs The command arguments.
     */
    public AddEventCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Executes the add event command.
     *
     * @param tasks The ArrayList of tasks.
     * @param ui The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     * @throws DukeException If there is an exception of type DukeException occurs.
     * @throws IOException If there is an error while saving data to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        int indexOfTimePrefix = commandArgs.indexOf(PREFIX_TIME_EVENT);
        if (indexOfTimePrefix == -1) {
            throw new IllegalEventException();
        }
        String description = commandArgs.substring(0, indexOfTimePrefix).trim();
        String timeInput = commandArgs.substring(indexOfTimePrefix + 3).trim();
        String time;
        try {
            time = parser.formatDateTime(timeInput);
        } catch (IllegalDateTimeException e) {
            throw new IllegalDateTimeException();
        }
        Event toBeAddedEvent = new Event(description, time);
        tasks.addTask(toBeAddedEvent);
        ui.printConfirmAdd(toBeAddedEvent, tasks.getNumberOfTasks());
        storage.saveDataToFile(tasks);
    }
}

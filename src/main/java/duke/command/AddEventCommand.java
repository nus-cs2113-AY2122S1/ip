package duke.command;

import duke.exception.DukeException;
import duke.exception.UnsavedFile;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.task.Deadline;

import java.io.IOException;


/**
 * Represents a command to add an event.
 */
public class AddEventCommand extends Command {
    private final String eventName;
    private final String eventTime;

    /**
     * The AddEventCommand constructor.
     * @param eventName the name/description of the event task
     * @param eventTime the time of the event
     */
    public AddEventCommand(String eventName, String eventTime) {
        this.eventName = eventName;
        this.eventTime = eventTime;
    }

    /**
     * Add the event into the list.
     * @param tasks the TaskList object that takes in the new event task
     * @param ui the Ui object responsible for printing messages
     * @param storage the Storage object responsible for saving data in a local txt file
     * @throws UnsavedFile if fail to save data in the local file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnsavedFile {
        tasks.addTask(new Deadline(eventName, eventTime));
        int currentTaskIndex = tasks.getTaskListSize() - 1;
        ui.printAdded(tasks.getTaskDetails(currentTaskIndex));
        try {
            storage.writeData(tasks.getTaskList());
        } catch (IOException e) {
            throw new UnsavedFile();
        }
    }
}
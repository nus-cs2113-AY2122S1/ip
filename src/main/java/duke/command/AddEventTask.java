package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a command type of Add Event Task
 */
public class AddEventTask extends Command {

    private String userCommand;

    /**
     * Constructor of AddEventTask Class
     *
     * @param userCommand command user type in
     */
    public AddEventTask(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Adds an Event task to the ArrayList tasks
     *
     * @param tasks TaskList including all tasks
     * @param ui User Interface
     * @param storage Storage to load and save the data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEvent(userCommand);
        storage.saveData(tasks.getTasks());
    }

    /**
     * Returns false to indicate the program continues running
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

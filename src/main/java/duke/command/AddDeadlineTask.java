package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class represents a command type of Add Deadline Task
 */
public class AddDeadlineTask extends Command{

    private String userCommand;

    /**
     * Constructor of AddDeadlineTask Class
     *
     * @param userCommand command user type in
     */
    public AddDeadlineTask(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Adds a deadline task to the ArrayList tasks
     *
     * @param tasks TaskList including all tasks
     * @param ui User Interface
     * @param storage Storage to load and save the data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addDeadline(userCommand);
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

package Duke.commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

public class IncorrectCommand extends Command {
    private String errorType;

    /**
     * Class constructor for IncorrectCommand
     *
     * @param errorType Type of error
     */
    public IncorrectCommand(String errorType) {
        this.errorType = errorType;
    }

    /**
     * Shows user the error message according to the corresponding error type
     *
     * @param tasks TaskList containing Task
     * @param ui User interface
     * @param storage File management system
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(errorType);
    }
}

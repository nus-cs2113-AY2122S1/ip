package Duke.Command;

import Duke.Ui.Ui;
import Duke.Storage.Storage;
import Duke.Tasks.Event;
import Duke.Tasks.TaskList;

public class AddEventCommand extends Command {
    private final String description;
    private final String at;

    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + ": add an event task to the current list.\n"
            + " Parameters: DESCRIPTION, AT_TIME\n"
            + " Example: " + COMMAND_WORD + "attend CS2113 lecture /at Dec 4";

    /**
     * Execute the add event command by adding a new event task to TaskList
     *
     * @param tasks TaskList the command to be executed on
     * @param ui Ui used for execution
     * @param storage Storage which the command may make change on
     */
    public AddEventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printAddTaskMessage(new Event(this.description, this.at));
        tasks.addEvent(this.description, this.at);
        ui.printNumOfTasks(tasks);
        storage.save(tasks.getTasks());
    }
}

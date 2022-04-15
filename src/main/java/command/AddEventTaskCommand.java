package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Adds an event task into the list and the file
 */
public class AddEventTaskCommand extends Command {
    public static final String COMMAND_KEYWORD = "event";
    private String description;
    private LocalDateTime at;

    /**
     * Constructs AddEventTaskCommand
     *
     * @param description Task description
     * @param at Event date and time
     */
    public AddEventTaskCommand(String description, LocalDateTime at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Executes AddEventTaskCommand by adding the event task into the list
     * and into the file. It also prints the confirmation message into the console.
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     * @throws IOException If there is an error accessing the file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addEventTask(description, at);
        storage.updateFile(tasks);
        ui.acknowledgeAdd();
    }
}

package command;

import exception.AustinException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Adds a deadline task into the list and the file
 */
public class AddDeadlineTaskCommand extends Command {
    public static final String COMMAND_KEYWORD = "deadline";
    private String description;
    private LocalDateTime by;

    /**
     * Constructs AddDeadlineTaskCommand
     *
     * @param description Task description
     * @param by Deadline date and time
     */
    public AddDeadlineTaskCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes AddDeadlineTaskCommand by adding the deadline task into the list
     * and into the file. It also prints the confirmation message into the console.
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     * @throws IOException If there is an error accessing the file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addDeadlineTask(description, by);
        storage.updateFile(tasks);
        ui.acknowledgeAdd();
    }
}

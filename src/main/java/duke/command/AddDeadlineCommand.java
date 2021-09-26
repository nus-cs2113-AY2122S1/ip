package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

import java.time.LocalDate;

public class AddDeadlineCommand extends AddTaskCommand{
    private String description;
    private LocalDate by;
    private String input;

    public AddDeadlineCommand(String description, LocalDate by, String input) {
        this.description = description;
        this.by = by;
        this.input = input;
    }

    /**
     * Adds a deadline to tasklist, prints the success message and stores it into the data file.
     *
     * @param tasks Tasklist object used to store tasks
     * @param ui Ui object used to print messages
     * @param storage Storage object used to manipulate data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addDeadline(description, by);
        ui.printAddTaskMessage(tasks);
        storage.storeTask(input);
    }

    /**
     * Adds a deadline to tasklist.
     *
     * @param tasks Tasklist object used to store tasks
     */
    @Override
    public void addTask(TaskList tasks) {
        tasks.addDeadline(description, by);
    };
}

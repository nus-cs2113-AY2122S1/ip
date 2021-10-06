package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class AddEventCommand extends AddTaskCommand{
    private String description;
    private LocalDate at;
    private String input;

    public AddEventCommand(String description, LocalDate at, String input) {
        this.description = description;
        this.at = at;
        this.input = input;
    }

    /**
     * Adds an event to tasklist, prints the success message and stores it into the data file.
     *
     * @param tasks Tasklist object used to store tasks
     * @param ui Ui object used to print messages
     * @param storage Storage object used to manipulate data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addEvent(description, at);
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
        tasks.addEvent(description, at);
    }
}

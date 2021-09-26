package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddTodoCommand extends AddTaskCommand{
    private String description;
    private String input;

    public AddTodoCommand(String description, String input) {
        this.description = description;
        this.input = input;
    }

    /**
     * Adds a todo to tasklist, prints the success message and stores it into the data file.
     *
     * @param tasks Tasklist object used to store tasks
     * @param ui Ui object used to print messages
     * @param storage Storage object used to manipulate data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTodo(description);
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
        tasks.addTodo(description);
    }
}

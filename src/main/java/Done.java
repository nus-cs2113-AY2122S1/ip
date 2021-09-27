import java.io.IOException;

public class Done extends Command {
    private Tasks tasks;
    private Task task;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for command when the user marks a task as done.
     * @param tasks List of all existing tasks.
     * @param index Index of task to be marked done
     * @param ui Handles interaction with user.
     * @param storage updates "data/duke.txt" file
     */
    public Done(Tasks tasks, int index, Ui ui, Storage storage) {
        tasks.get(index).markComplete();
        this.tasks = tasks;
        this.task = tasks.get(index);
        this.ui = ui;
        this.storage = storage;
    }

    public void execute() throws IOException {
        ui.done(task, tasks);
        task.markComplete();
        storage.writeToFile(tasks);
    }
}

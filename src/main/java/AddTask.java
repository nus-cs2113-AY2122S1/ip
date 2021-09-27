import java.io.IOException;

public class AddTask extends Command{
    private Task task;
    private Tasks tasks;
    private Ui ui;
    private Storage storage;
    /**
     * Constructor for command for user to add a new task. Task can be either a normal task, an Event or a Deadline
     * @param tasks list of existing tasks for task to be added to
     * @param task Task to be added. Either be a normal task, an Event or a Deadline
     * @param ui Handles interaction with the user
     * @param storage updates "data/duke.txt"
     */
    public AddTask(Tasks tasks, Task task, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.task = task;
        this.ui = ui;
        this.storage = storage;
    }
    public void execute() throws IOException {
        tasks.add(task);
        ui.add(task, tasks);
        storage.writeToFile(tasks);
    }
}

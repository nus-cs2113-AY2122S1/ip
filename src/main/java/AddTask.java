import java.io.IOException;

public class AddTask extends Command{
    private Task task;
    private Tasks tasks;
    private Ui ui;
    private Storage storage;

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

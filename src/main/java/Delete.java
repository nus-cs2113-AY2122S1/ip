import java.io.IOException;

public class Delete extends Command{
    private Tasks tasks;
    private Task task;
    private Ui ui;
    private Storage storage;

    public Delete(Tasks tasks, int index, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.task = tasks.get(index);
        this.ui = ui;
        this.storage = storage;
    }

    public void execute() throws IOException {
        ui.delete(task, tasks);
        tasks.delete(task);
        storage.writeToFile(tasks);
    }
}

public class AddCommand extends Command {
    Task task = null;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskManager taskManager) throws DukeException {
        taskManager.addTask(task);
    }
}

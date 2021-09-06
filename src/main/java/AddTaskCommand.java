public class AddTaskCommand extends Command{
    protected Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void executeCommand(TaskManager taskManager) throws DukeException{
        taskManager.addTasks(task);
    }
}

public class DoneCommand extends Command{
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager taskManager) throws DukeException{
        taskManager.completeTask(taskIndex);
    }
}

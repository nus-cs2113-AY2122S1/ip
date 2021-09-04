public class ListCommand extends Command{
    @Override
    public void execute(TaskManager taskManager) {
        taskManager.printTasks();
    }
}

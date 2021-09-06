public class ListCommand extends Command{

    @Override
    public void executeCommand(TaskManager taskManager) {
        taskManager.printTasks();
    }
}

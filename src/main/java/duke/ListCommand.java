package duke;

public class ListCommand extends Command {

    /**
     * Calls the printTasks method in taskManager to list out its Tasks
     *
     * @param taskManager The taskManager that will be read
     */
    @Override
    public void execute(TaskManager taskManager) {
        taskManager.printTasks();
    }
}

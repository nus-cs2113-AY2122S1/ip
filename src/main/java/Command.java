public class Command {

    /**
     * Adds a todo task to the list stored by taro
     *
     * @param taskName the description provided by the user for the task to be added
     * @param ui the UI attribute of the Duke instance that calls addTask()
     * @param taskManager the TaskManager attribute of the Duke instance, which performs modifications to the list of
     *                   tasks
     */
    public void addTask(String taskName, UI ui, TaskManager taskManager) {
        Task newTask = taskManager.addTask(taskName);
        ui.printTaskAddedMessage(newTask, taskManager.getTaskCount());
    }

    /**
     * Adds either a deadline or an event to the list of stored tasks with its date included. This function is only
     * called when the taskType parameter is provided
     *
     * @param taskName the description provided by the user for the task to be added
     * @param ui the UI attribute of the Duke instance that calls addTask()
     * @param taskManager the TaskManager attribute of the Duke instance, which performs modifications to the list of
     *                   tasks
     * @param date the date or time (either deadline or event date) to be attached to the task
     * @param taskType either "deadline" or "event", used to indicate whether the task is a deadline or event
     */
    public void addTask(String taskName, UI ui, TaskManager taskManager, String date,
                                          String taskType) {
        Task newTask = taskManager.addTask(taskName, date, taskType);
        ui.printTaskAddedMessage(newTask, taskManager.getTaskCount());
    }

    /**
     * Lists out all tasks stored by Duke, printing to standard output
     * Tasks marked as completed will have an [X] beside their names
     */
    public void listTasks(TaskManager taskManager, UI ui) {
        ui.printTasksList(taskManager.getTasks(), taskManager.getTaskCount());
    }

    /**
     * Marks task n from the stored array of tasks as done when Duke
     * receives the command "done n" and prints an acknowledgement
     * to the standard output
     */
    public void markTaskAsDone(int taskIndex, UI ui, TaskManager taskManager) {
        taskManager.markAsDone(taskIndex);
        ui.printMarkedDoneMessage(taskManager.getTasks()[taskIndex - 1]);
    }

    /**
     * Uses the ui to print an exit message to the output and then exits the programme
     */
    public void exitDuke(UI ui) {
        ui.printExitMessage();
        System.exit(0);
    }

}

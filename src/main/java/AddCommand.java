public class AddCommand extends Command {
    private String taskType;
    private String mainTask;
    private String taskDate = null;

    /**
     * We use this constructor for Todo task
     * @param taskType The task type, which should be Todo
     * @param mainTask The task description
     * */
    public AddCommand(String taskType, String mainTask) {
        this.taskType = taskType;
        this.mainTask = mainTask;
    }

    /**
     * We use this constructor for either Event or Deadline task
     * @param taskType The task type, which should be either Event or Deadline
     * @param mainTask The task description
     * @param taskDate The event or deadline date
     * */
    public AddCommand(String taskType, String mainTask, String taskDate) {
        this.taskType = taskType;
        this.mainTask = mainTask;
        this.taskDate = taskDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, DukeStorage dukeStorage) {
        taskList.addTask(taskType, mainTask, taskDate);
        ui.printNumberOfTasks(taskList);
        dukeStorage.updateStorage(taskList);
    }

    @Override
    public boolean exit() {
        return false;
    }
}

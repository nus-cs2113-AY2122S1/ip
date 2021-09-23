public class AddCommand extends Command {
    private String taskType;
    private String mainTask;
    private String taskDate = null;

    public AddCommand(String taskType, String mainTask) {
        this.taskType = taskType;
        this.mainTask = mainTask;
    }

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

public class AddCommand extends Command {
    private String taskName;
    private String taskDate;
    private TaskType taskType;

    public AddCommand(String taskName, String taskDate, TaskType taskType) {
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskType = taskType;
    }

    @Override
    public void runCommand() {
        TaskManager.addToList(taskName, taskDate, taskType);
    }
}

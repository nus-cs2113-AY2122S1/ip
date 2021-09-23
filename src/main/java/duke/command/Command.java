package duke.command;

import duke.task.TaskManager;

public class Command {

    final public static String TAG_NO_FORMAT = " <no additional input required>";
    final public static String TAG_TASK_NUMBER = " <task number(can be seen using the list command, eg. 1)>";
    final public static String TAG_TASK_DESCRIPTION = " <task description>";

    protected TaskManager taskManager;
    private int taskIndex = -1;
    protected boolean hasDataChange = false;

    public Command() {

    }

    public Command(int targetIndex) {
        this.taskIndex = targetIndex;
    }

    public void setTaskManager(TaskManager taskManager) throws CommandException {
        if (taskManager == null) {
            throw new CommandException("Error: task manager not found.");
        }
        this.taskManager = taskManager;
    }

    public void execute() throws CommandException {
        throw new CommandException("Error: Command not found.");
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public boolean hasDataChange() {
        return hasDataChange;
    }

}

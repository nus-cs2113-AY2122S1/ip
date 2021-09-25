package commands;

import common.Messages;
import task.Task;
import task.TaskManager;

import java.util.ArrayList;


import static common.Messages.MESSAGE_LIST_ALL_TASKS;
import static ui.Ui.DISPLAYED_INDEX_OFFSET;

public abstract class Command {
    protected TaskManager taskManager;
    private int targetDisplayIndex = -1;

    public void setData(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    protected Command() {
    }

    public Command(int targetDisplayIndex) {
        this.setTargetDisplayIndex(targetDisplayIndex);
    }

    public int getTargetDisplayIndex() {
        return targetDisplayIndex;
    }

    public void setTargetDisplayIndex(int targetDisplayIndex) {
        this.targetDisplayIndex = targetDisplayIndex;
    }

    protected Task getTargetTask() throws IndexOutOfBoundsException {
        int targetIndex = getTargetDisplayIndex() - DISPLAYED_INDEX_OFFSET;
        return taskManager.getTaskList().get(targetIndex);
    }

    public abstract CommandResult execute();

    public String getMessageForTaskListDisplayed(ArrayList<Task> tasksDisplayed) {
        return String.format(Messages.MESSAGE_TASKS_FOUND_OVERVIEW + MESSAGE_LIST_ALL_TASKS, tasksDisplayed.size());
    }
}

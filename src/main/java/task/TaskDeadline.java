package task;

import time.Time;

public class TaskDeadline extends TaskBase {
    public TaskDeadline(TaskType taskType, Time taskTime, String taskDescription) {
        super(taskType , taskTime, taskDescription);
    }
}

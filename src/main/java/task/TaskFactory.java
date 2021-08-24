package task;

import command.Command;
import time.Time;

public class TaskFactory {

    public TaskFactory() {}

    /**
     * Function to create a task of type todo, deadline, or event
     **/
    public TaskBase makeTask(Command cmd) {
        Time timeInfo = cmd.getTimeInfo();
        String taskContent = cmd.getTaskContent();
        return new TaskTodo(TaskType.TODO, timeInfo, taskContent);
    }

}

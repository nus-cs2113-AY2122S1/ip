package duke.tasks;

import duke.tasks.TaskList;

public class TaskListResponse {
    public TaskList taskList;
    public String response;

    public TaskListResponse(TaskList taskList, String response) {
        this.taskList = taskList;
        this.response = response;
    }
}

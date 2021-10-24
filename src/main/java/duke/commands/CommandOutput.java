package duke.commands;

import duke.tasks.TaskList;

public class CommandOutput {

    private String response;
    private boolean updated;
    private TaskList taskList;

    public CommandOutput(String response, boolean updated) {
        this.response = response;
        this.updated = updated;
        taskList = null;
    }

    public CommandOutput(String response, boolean updated, TaskList taskList) {
        this.response = response;
        this.updated = updated;
        this.taskList = taskList;
    }

    public String getResponse() {
        return  response;
    }

    public boolean isUpdated() {
        return updated;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}

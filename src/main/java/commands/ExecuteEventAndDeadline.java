package commands;

import storage.Storage;
import task.type.Event;
import task.type.Deadline;
import task.type.TaskList;

public class ExecuteEventAndDeadline extends Command {
    private String type;
    private String description;
    private String time;

    public ExecuteEventAndDeadline(String type, String description, String time) {
        this.type = type;
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasksList, Storage storage){
        if (type.equals("event")) {
            Event event = new Event(description, time);
            tasksList.addTaskToList(event);
        } else {
            Deadline deadline = new Deadline(description, time);
            tasksList.addTaskToList(deadline);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}


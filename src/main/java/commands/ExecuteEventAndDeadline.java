package commands;

import storage.Storage;
import task.type.Event;
import task.type.Deadline;
import task.type.TaskList;

public class ExecuteEventAndDeadline extends Command {
    private String typeOfInput;
    private String description;
    private String time;

    /**
     * Constructor
     *
     * @param typeOfInput whether Event or Deadline
     * @param description Task description
     * @param time the information after /by or /at.
     */
    public ExecuteEventAndDeadline(String typeOfInput, String description, String time) {
        this.typeOfInput = typeOfInput;
        this.description = description;
        this.time = time;
    }

    /**
     * Adds events of type Event and Deadline to list
     *
     * @param tasksList Object of TaskList.
     * @param storage Object of Storage.
     */
    @Override
    public void execute(TaskList tasksList, Storage storage){
        if (typeOfInput.equals("event")) {
            Event event = new Event(description, time);
            tasksList.addTaskToList(event);
        } else {
            Deadline deadline = new Deadline(description, time);
            tasksList.addTaskToList(deadline);
        }
    }

    /**
     * To check whether to exit the application
     *
     * @return false To take input from user again.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}


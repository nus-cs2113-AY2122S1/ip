package shima.command;

import shima.task.TaskList;

import java.io.IOException;

/**
 * This class serves to create event
 */
public class AddEventCommand extends AddToDoCommand {
    protected String taskName;
    protected String time;

    public AddEventCommand(TaskList tasks, String command, String[] words, String taskName, String time) {
        super(tasks, command, words);
        this.taskName = taskName;
        this.time = time;
    }

    /**
     * Runs the command for creating new event task
     *
     * @throws IOException Throws this exception when there is error during accessing storage file
     */
    public void runCommand() throws IOException {
        tasks.createEvent(taskName, time);
    }
}

package shima.command;

import shima.task.TaskList;

import java.io.IOException;

/**
 * This class serves to create deadline
 */
public class AddDeadlineCommand extends AddToDoCommand {
    protected String time;

    public AddDeadlineCommand(TaskList tasks, String command, String[] words, String time) {
        super(tasks, command, words);
        this.time = time;
    }

    /**
     * Runs the command for creating new deadline task
     *
     * @throws IOException Throws this exception when there is error during accessing storage file
     */
    public void runCommand() throws IOException {
        command = command.replaceFirst(words[0], "").trim();
        String taskName = command.split("/", 2)[0].trim();
        tasks.createDeadline(taskName, time);
    }
}

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

    public void runCommand() throws IOException {
        command = command.replaceFirst(words[0], "").trim();
        String taskName = command.split("/", 2)[0].trim();
        tasks.createDeadline(taskName, time);
    }
}

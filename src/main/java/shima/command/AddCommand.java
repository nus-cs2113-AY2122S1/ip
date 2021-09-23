package shima.command;

import shima.task.TaskList;

import java.io.IOException;

public class AddCommand extends Command {

    private final TaskList tasks;
    private final String command;
    private final String[] words;

    /**
     * @param tasks   The list that stores all the tasks
     * @param command The user input command
     * @param words   The array of words that compose the command
     */
    public AddCommand(TaskList tasks, String command, String[] words) {
        this.tasks = tasks;
        this.command = command;
        this.words = words;
    }

    /**
     * Runs the add task command
     *
     * @throws IOException Throws this exception when there is error occurs during accessing storage file
     */
    @Override
    public void runCommand() throws IOException {
        tasks.addTask(command, words);
    }

}

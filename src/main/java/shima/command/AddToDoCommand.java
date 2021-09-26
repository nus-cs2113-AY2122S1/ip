package shima.command;

import shima.task.TaskList;

import java.io.IOException;

/**
 * This class serves to creates to-do
 */
public class AddToDoCommand extends Command {

    protected TaskList tasks;
    protected String command;
    protected String[] words;

    /**
     * @param tasks   The list that stores all the tasks
     * @param command The user input command
     * @param words   The array of words that compose the command
     */
    public AddToDoCommand(TaskList tasks, String command, String[] words) {
        this.tasks = tasks;
        this.command = command;
        this.words = words;
    }

    /**
     * Runs the command for creating new to-do task
     *
     * @throws IOException Throws this exception when there is error occurs during accessing storage file
     */
    @Override
    public void runCommand() throws IOException {
        tasks.createToDo(command, words);
    }

}

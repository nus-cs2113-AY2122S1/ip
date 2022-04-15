package shima.command;

import shima.task.TaskList;

public class DeleteCommand extends Command {
    private final TaskList tasks;
    private String[] words;

    public DeleteCommand(TaskList tasks, String[] words) {
        this.tasks = tasks;
        this.words = words;
    }

    /**
     * Runs the command for deleting tasks
     */
    public void runCommand() {
        tasks.deleteTasks(words);
    }
}

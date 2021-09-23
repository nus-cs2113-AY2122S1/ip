package shima.command;

import shima.storage.Storage;
import shima.task.TaskList;

public class DoneCommand extends Command {
    private final TaskList tasks;
    private final Storage storage;
    private final String[] words;

    public DoneCommand(TaskList tasks, Storage storage, String[] words) {
        this.tasks = tasks;
        this.storage = storage;
        this.words = words;
    }

    @Override
    public void runCommand() {
        tasks.markAsDone(tasks, words, storage);
    }
}

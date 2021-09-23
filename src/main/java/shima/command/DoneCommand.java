package shima.command;

import shima.design.Default;
import shima.storage.Storage;
import shima.task.Task;
import shima.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class DoneCommand extends Command{
    private final TaskList tasks;
    private final Storage storage;
    private final String[] words;

    public DoneCommand(TaskList tasks, Storage storage, String[] words) {
        this.tasks = tasks;
        this.storage = storage;
        this.words = words;
    }

    @Override
    public void runCommand(){
        tasks.markAsDone(tasks, words, storage);
    }

}

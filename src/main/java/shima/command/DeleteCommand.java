package shima.command;

import shima.design.Default;
import shima.storage.Storage;
import shima.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DeleteCommand extends Command{
    private final TaskList tasks;
    private String[] words;

    public DeleteCommand(TaskList tasks, String[] words){
        this.tasks = tasks;
        this.words = words;
    }
    public void runCommand(){
        tasks.deleteTasks(words);
    }
}

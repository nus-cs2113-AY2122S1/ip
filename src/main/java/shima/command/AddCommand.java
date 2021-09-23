package shima.command;

import shima.design.Default;
import shima.storage.Storage;
import shima.task.*;

import java.io.IOException;

public class AddCommand extends Command{
    public static String name = "";
    public static final String EMPTY_PERIOD_MSG = "Sorry, the date and period for the task \"" + name + "\" is missing!";
    public static final String EMPTY_DEADLINE_MSG = "Sorry, the deadline for the task \"" + name + "\" is missing!";
    public static final String EMPTY_TASK_MSG = "Sorry, the task is empty! I don't know how to record it :(";
    public static final String SLASH_MISSING_MSG = "Sorry, fail to create an Event, the time specific character '/' is missing";
    public static final String DASH_MISSING_MSG = "Sorry, fail to create an Event, the period specific character '-' is missing";

    private TaskList tasks;
    private Storage storage;
    private String command;
    private String[] words;

    public AddCommand(TaskList tasks, Storage storage, String command, String[] words){
        this.tasks = tasks;
        this.storage = storage;
        this.command = command;
        this.words = words;
    }

    @Override
    public void runCommand() throws IOException {
        tasks.addTask(command, words, storage);
    }

}

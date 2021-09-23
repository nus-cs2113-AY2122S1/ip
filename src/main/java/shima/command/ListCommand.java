package shima.command;

import shima.task.TaskList;

public class ListCommand extends Command {
    //Corner symbols for to-do list frames
    public static final String TOP_LEFT_CORNER = "/";
    public static final String TOP_RIGHT_CORNER = "\\";
    public static final String BOTTOM_LEFT_CORNER = "\\";
    public static final String BOTTOM_RIGHT_CORNER = "/";
    private final TaskList tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    public void runCommand() {
        tasks.printToDoList();
    }

}

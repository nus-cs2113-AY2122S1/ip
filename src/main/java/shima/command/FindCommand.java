package shima.command;

import shima.design.UserInterface;
import shima.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String LINE_SEPARATOR = "\t@----------------------------------------------------------------------@";
    protected ArrayList<Task> matchingTasks;
    protected UserInterface ui;

    public FindCommand(ArrayList<Task> matchingTasks, UserInterface ui) {
        this.matchingTasks = matchingTasks;
        this.ui = ui;
    }

    /**
     * Runs the command to print all the tasks that contain the keyword which are stored in the matchingTasks array list
     */
    public void runCommand() {
        int count = 0;
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tHere are the tasks that matched the keyword");
        for (Task t : matchingTasks) {
            String doneIcon = (t.getDone())? "[X] " : "[ ] ";
            System.out.println("\t\t\t" + (count + 1) + ". [" + t.getClassType() + "]" + doneIcon + t);
            count++;
        }
        System.out.println(LINE_SEPARATOR);
    }
}

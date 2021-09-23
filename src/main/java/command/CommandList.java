package command;

import duke.TaskList;
import duke.Ui;

public class CommandList extends Command {
    public static final String LIST_HEADER = "     Here are the tasks in your list:";
    public static final String LIST_NO_TASK = "     You have no tasks in the list at the moment.\n" +
            "     Please add a new task to begin.";
    private static final int INDEX_FIX = 1;

    public CommandList() {
    }

    /**
     * Print all the tasks in the list array
     */
    public void run() {
        System.out.println(Ui.DASH_LINE);
        int size = TaskList.getArraySize();
        if (size != 0) {
            System.out.println(LIST_HEADER);
            for (int i = 0; i < size; i++) {
                int numbering = i + INDEX_FIX;
                System.out.println("     " + numbering + ". " + TaskList.getTask(i));
            }
        } else {
            System.out.println(LIST_NO_TASK);
        }
        System.out.println(Ui.DASH_LINE);
    }
}

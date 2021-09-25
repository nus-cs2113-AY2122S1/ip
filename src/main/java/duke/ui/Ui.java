package duke.ui;

import duke.task.List;
import duke.task.TaskList;
import duke.task.TaskDoneList;
import java.util.ArrayList;

public class Ui {
    private static final String SPLIT_LINE = "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public void showWelcomeScreen() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(SPLIT_LINE + "\n    Hello! I'm Duke\n"
            + "    What can I do for you?\n" + SPLIT_LINE);
    }

    public void printSplitLine() {
        System.out.println(SPLIT_LINE);
    }

    public void printDoneList(TaskDoneList doneList, Ui ui) {
        if (doneList.doneListSize() == 0) {
            ui.printSplitLine();
            System.out.println("    You have not finished any tasks");
        } else {
            System.out.println("    Congratulations! Now you have done " + doneList.doneListSize() + " task(s):");
            for (int i = 0; i < doneList.doneListSize(); i++) {
                System.out.println("    " + (i + 1) + "." + doneList.getDoneTask(i));
            }
        }
        ui.printSplitLine();
    }

    public static void printList(TaskList list, Ui ui) {
        if (list.listSize() == 0) {
            ui.printSplitLine();
            System.out.println("    There is no task in your list!");
        } else {
            ui.printSplitLine();
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < list.listSize(); i++) {
                System.out.println("    " + (i + 1) + "." + list.getTask(i));
            }
        }
        ui.printSplitLine();
    }

    public void showTerminateScreen(Ui ui) {
        ui.printSplitLine();
        System.out.println("    Bye. Hope to see you again soon!");
        ui.printSplitLine();
    }

    public void printAddCommand(TaskList list, Ui ui){
        ui.printSplitLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + list.getTask(list.listSize() - 1));
        System.out.println("    Now you have " + list.listSize() + " task(s) in the list.");
        printSplitLine();
    }

    public void printDukeException(Ui ui) {
        ui.printSplitLine();
        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        ui.printSplitLine();
    }

    public void printTaskError(Ui ui) {
        ui.printSplitLine();
        System.out.println("    ☹ OOPS!!! The description of a task cannot be empty.");
        ui.printSplitLine();
    }

    public void printDeleteError(Ui ui) {
        ui.printSplitLine();
        System.out.println("    ☹ OOPS!!! You need to add a correct index after 'delete' ");
        ui.printSplitLine();
    }

    public void printDoneError(Ui ui) {
        ui.printSplitLine();
        System.out.println("    ☹ OOPS!!! You need to add a correct index after 'done' ");
        ui.printSplitLine();
    }

    public void printCreateFileError(Ui ui) {
        ui.printSplitLine();
        System.out.println("    ☹ OOPS!!! There are some errors when creating files");
        ui.printSplitLine();
    }
}

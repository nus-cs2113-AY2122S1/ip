package duke.ui;

import duke.task.TaskList;
import duke.task.TaskDoneList;

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
        System.out.println("    Enter \'help\' for detailed information\n");
    }

    public void printSplitLine() {
        System.out.println(SPLIT_LINE);
    }

    /**
     * Print only all tasks which have been finished
     */
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

    /**
     * Print all tasks
     */
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

    public void showHelpMessage(Ui ui) {
        ui.printSplitLine();
        System.out.println("    Hello! Welcome to Duke.\n"
            + "    I will help you track three types of tasks: Todo, Deadline and Event.\n"
            + "    To add task in list, start with \"todo\", \"deadline\" or \"event\""
            + "and followed with the description of your task\n"
            + "    (For deadlines and events, please enter either \"by (your deadline)\""
            + "or \"at (your event time)\")\n"
            + "    To view all your tasks, enter \"list\".\n"
            + "    To mark one task as done and print all finished tasks in list,"
            + " enter \"done (task index)\". \n"
            + "    To delete a task in the list, enter \"delete (task index)\". \n"
            + "    To find your tasks with keywords, enter \"find (keyword)\". \n"
            + "    To exit duke, enter \"bye\". \n"
            + "    To view the help information, enter \"help\". \n"
            + "    Thank you for choosing Duke :) Please enter command:");
        ui.printSplitLine();
    }

    public void printAddCommand(TaskList list, Ui ui){
        ui.printSplitLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + list.getTask(list.listSize() - 1));
        System.out.println("    Now you have " + list.listSize() + " task(s) in the list.");
        printSplitLine();
    }

    /**
     * Print error message when program cannot understand the command
     */
    public void printDukeException(Ui ui) {
        ui.printSplitLine();
        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        ui.printSplitLine();
    }

    /**
     * Print error message when the description of a task is empty
     */
    public void printTaskError(Ui ui) {
        ui.printSplitLine();
        System.out.println("    ☹ OOPS!!! The description of a task cannot be empty.");
        ui.printSplitLine();
    }

    /**
     * Print error message when the index
     * after 'delete' command is not correct
     */
    public void printDeleteError(Ui ui) {
        ui.printSplitLine();
        System.out.println("    ☹ OOPS!!! You need to add a correct index after 'delete' ");
        ui.printSplitLine();
    }

    /**
     * Print error message when the index
     * after 'done' command is not correct
     */
    public void printDoneError(Ui ui) {
        ui.printSplitLine();
        System.out.println("    ☹ OOPS!!! You need to add a correct index after 'done' ");
        ui.printSplitLine();
    }

    /**
     * Print error message when the create-file process is failed
     */
    public void printCreateFileError(Ui ui) {
        ui.printSplitLine();
        System.out.println("    ☹ OOPS!!! There are some errors when creating files");
        ui.printSplitLine();
    }
}

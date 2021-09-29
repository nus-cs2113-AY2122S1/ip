package duke.processes;

import duke.tasks.Task;

import java.util.ArrayList;

public class Ui {

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("--------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(" ");
        System.out.println("--------------------");
    }

    public static void printGoodbyeMessage() {
        System.out.println("--------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(" ");
        System.out.println("--------------------");
    }

    public static void printDoneMessage(ArrayList<Task> list, int itemNum) {
        System.out.println("--------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println( itemNum + ".[" + list.get(itemNum - 1).getStatusIcon() + "] " + list.get(itemNum - 1).getDescription() );
        System.out.println("--------------------");
    }

    public static void printListMessage(TaskList tasks) {
        System.out.println("--------------------");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.counter; i += 1){
            printListOfTaskSubMessage(tasks.list.get(i), i);
        }
        System.out.println("--------------------");
    }

    public static void printListButEmptyMessage() {
        System.out.println("--------------------");
        System.out.println("List is empty. Time to get productive!");
        System.out.println("--------------------");
    }

    public static void printDoneButNotSpecificMessage() {
        System.out.println("Please specify which task is done.");
    }

    public static void printDoneButEmptyMessage() {
        System.out.println("--------------------");
        System.out.println("Unable to tick off list.");
        System.out.println("List is empty. Time to get productive!");
        System.out.println("--------------------");
    }

    public static void printAddedTaskMessage(Task task, int i) {
        System.out.println("--------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println("[" + task.getLetter() + "] "
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getDate() );
        System.out.println("Now you have " + i + " tasks in the list.");
        System.out.println("--------------------");
    }

    public static void printListOfTaskSubMessage(Task task, int i) {
        System.out.println(i + 1
                + ".[" + task.getLetter() + "] "
                + "[" + task.getStatusIcon() + "] "
                + task.getDescription()
                + task.getDate() );
    }

    public static void printDeleteMessage(ArrayList<Task> list, int itemNum) {
        System.out.println("--------------------");
        System.out.println("Noted. I've removed this task:");
        System.out.println( itemNum + ".[" + list.get(itemNum - 1).getStatusIcon() + "] " + list.get(itemNum - 1).getDescription() );
        System.out.println("Now you have " + ( list.size() - 1 )+ " tasks in the list.");
        System.out.println("--------------------");
    }

    public static void printDeleteButNotSpecificMessage() {
        System.out.println("Please specify which task is to be deleted.");
    }

    public static void printDeleteButEmptyMessage() {
        System.out.println("--------------------");
        System.out.println("Unable to delete.");
        System.out.println("List is empty. Time to get productive!");
        System.out.println("--------------------");
    }

    public static void printFindMessageStart() {
        System.out.println("--------------------");
        System.out.println("Here are the matching tasks in your list:");
    }

    public static void printFindMessageEnd() {
        System.out.println("--------------------");
    }

    public static void printFindNothingMessage() {
        System.out.println("(there are 0 matches)");
    }
}

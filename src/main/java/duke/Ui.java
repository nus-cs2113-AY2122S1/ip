package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;


public class Ui {
    private final Scanner SCANNER = new Scanner(System.in);

    public String readCommand() {
        return SCANNER.nextLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showLine();
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke!");
        System.out.println("\tWhat can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public void showAllTasks(TasksList tasks) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i=0; i<tasks.getSize(); i++) {
            System.out.println("\t" + (i+1) + "." + tasks.getTaskString(i));
        }
    }

    public void showTaskAdded(Task task, int taskListSize) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + taskListSize + " tasks in the list.");
    }

    public void showTaskDone(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t[" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public void showTaskDeleted(Task task, int taskListSize) {
        System.out.println("\tNoted. I have removed this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + taskListSize + " tasks in the list.");
    }

    public void showFoundTasks(ArrayList<Task> foundTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i=0; i<foundTasks.size(); i++) {
            System.out.println("\t" + (i+1) + "." + foundTasks.get(i).toString());
        }
    }

    public void showLoadingError() {
        System.out.println("An error occurred when loading data store.");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}

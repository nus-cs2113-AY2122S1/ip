import task.Task;

import java.util.ArrayList;

public class Ui {

    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public void printGreetings() {
        System.out.println("Welcome to Duke! What can I do for you?");
        printDivider();
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void printTaskCount() {
        int count = TaskManager.tasks.size();
        if (count == 0) {
            System.out.print("You have not added any tasks");
        } else if (count == 1) { //grammar
            System.out.print("Now you have 1 task in the list" + System.lineSeparator());
        } else {
            System.out.printf("Now you have %d tasks in the list" + System.lineSeparator(), count);
        }
    }

    public static void printDeleteTaskMessage(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(TaskManager.tasks.get(index));
        System.out.println("Now you have " + (TaskManager.tasks.size() - 1) + " tasks in the list");
        printDivider();
    }

    public static void printNewlyAddedTask(int index) {
        System.out.println("Got it. I've added this task:");
        System.out.println(TaskManager.tasks.get(index));
    }

    public static void printInvalidCommandMessage() {
        System.out.println("☹ OOPS!!! I do not understand what that means!");
        printDivider();
    }

    public static void printDukeExceptionMessage(String command) {
        if (command.equalsIgnoreCase("done")) {
            System.out.println("☹ OOPS!!! You've forgotten to write the task number");
        } else if (command.equalsIgnoreCase("find")) {
            System.out.println("☹ OOPS!!! What are you finding?");
        } else {
            System.out.printf("☹ OOPS!!! The description of a %s cannot be empty" + System.lineSeparator(), command);
        }
        printDivider();
    }

    public static void printMarkAsDoneMessage(int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TaskManager.tasks.get(index));
    }

    public static void printNoDataFoundMessage() {
        System.out.println("New user, no local data found");
    }

    public static void printDataSavedMessage() {
        System.out.println("Data is saved to hard drive.");
    }

    public static void printFileCreatedMessage() {
        System.out.println("Local file has been created.");
    }

    public static void printData(ArrayList<Task> tasks, String userInput) {
        System.out.printf("There are %d tasks containing \"%s\"" + System.lineSeparator(), tasks.size(), userInput);
        for (Task t : tasks) {
            System.out.println(t);
        }
        printDivider();
    }
}

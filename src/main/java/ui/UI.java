package ui;
import task.type.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private static Scanner in = new Scanner(System.in);

    public static String getInput() {
        return in.nextLine();
    }

    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printWithLines(String description) {
        printHorizontalLine();
        System.out.println(description);
        printHorizontalLine();
    }

    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printWithLines("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public static void printBye() {
        printWithLines("Bye. Hope to see you again soon!");
    }

    public static void printDescriptionNotFoundForTodo() {
        printWithLines("☹ OOPS!!! The description of todo cannot be empty.");
    }

    public static void printDescriptionNotFoundForEvent() {
        printWithLines("☹ OOPS!!! The description of event cannot be empty.");
    }

    public static void printDescriptionNotFoundForDeadline() {
        printWithLines("☹ OOPS!!! The description of deadline cannot be empty.");
    }

    public static void printErrorMessageFailedToWrite() {
        printWithLines("Failed to write data");
    }

    public static void printErrorMessageFileNotFound() {
        printWithLines("Error: save file not found");
    }

    public static void printAddTaskMessage(ArrayList<Task> taskList) {
        printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printHorizontalLine();
    }

    public static void printDeleteTaskMessage(String description, int size) {
        printHorizontalLine();
        System.out.println("Got it. I've deleted this task:");
        System.out.println(description);
        System.out.println("Now you have " + size + " tasks in the list.");
        printHorizontalLine();
    }

    public static void printDoneTaskMessage(String description) {
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(description);
        printHorizontalLine();
    }

    public static void printErrorUnknownCommandMessage() {
        printWithLines("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printListFoundTasks(ArrayList<Task> tasksList) {
        printHorizontalLine();
        if (!tasksList.isEmpty()) {
            int i = 1;
            for (Task item : tasksList) {
                System.out.println((i++) + ". " + item);
            }
        } else {
            System.out.println("Sorry ☹. There were no matching tasks!");
        }
        printHorizontalLine();
    }

    public static void printListTasks(ArrayList<Task> taskList) {
        System.out.println("____________________________________________________________");
        int i = 1;
        for (Task item: taskList) {
            System.out.println((i++) + ". " + item);
        }
        System.out.println("____________________________________________________________");
    }

}

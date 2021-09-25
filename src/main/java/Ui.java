package ip.src.main.java;

/**
 * Represents a UI class. A <code>UI</code> Class contains methods regarding the
 * printing of data from the computer to the user and makes the main code simpler.
 */
public class Ui {
    public static void printContents() {
        System.out.println("Here's your content from last time!");
    }

    public static void printFailContents() {
        System.out.println("File not found");
    }

    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
    }

    public static void removeTasks() {
        System.out.println("Removing to do list item boss!");
    }

    public static void markDone() {
        System.out.println("Nice! I've marked the task as done!");
    }

    public static void addDeadline() {
        System.out.println("Gotcha! I've added this deadline");
    }

    public static void addEvent() {
        System.out.println("Gotcha! I've added this event");
    }

    public static void addTodo() {
        System.out.println("Gotcha! I've added this todo");
    }

    public static void emptyTodo() {
        System.out.println("OOPS! The description of a todo cannot be empty. Please input again!");
    }

    public static void rephrase() {
        System.out.println("Sorry please rephrase");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void hello() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    public static void find() {
        System.out.println("Here are the matching tasks in your list!");
    }
}

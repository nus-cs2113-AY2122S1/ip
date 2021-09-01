import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static void printGreeting() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }
    public static void printConfused() {
        System.out.println("Could you say that again?");
    }
    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void printGotIt() {
        System.out.println("Got it. I've added this task:");
    }
    public static void printTaskCount() {
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    public static void printTaskTypeResponse() {
        printGotIt();
        tasks[taskCount - 1].printTask();
        printTaskCount();
    }
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i += 1) {
            System.out.print((i + 1) + ".");
            tasks[i].printTask();
        }
    }
    public static void TaskDone(int index) {
        tasks[index].setDone();
        System.out.println("Nice! I've marked this task as done:");
        tasks[index].printTask();
    }
    public static void addTodo(String line) {
        tasks[taskCount] = new Todo(line);
        taskCount = taskCount + 1;
        printTaskTypeResponse();
    }
    public static void addDeadline(String line) {
        String description = line.replaceAll("/.+", "");
        String by = line.replaceAll(".+/by", "");
        tasks[taskCount] = new Deadline(description, by);
        taskCount = taskCount + 1;
        printTaskTypeResponse();
    }
    public static void addEvent(String line) {
        String description = line.replaceAll("/.+", "");
        String by = line.replaceAll(".+/at", "");
        tasks[taskCount] = new Event(description, by);
        taskCount = taskCount + 1;
        printTaskTypeResponse();
    }
    public static void main(String[] args) {
        printGreeting();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList();
            } else if (line.contains("done")) {
                String[] splitTask = line.split(" ");
                int index = Integer.parseInt(splitTask[1]) - 1;
                tasks[index].setDone();
                TaskDone(index);
            } else if (line.contains("todo")) {
                addTodo(line);
            } else if (line.contains("deadline")) {
                addDeadline(line);
            } else if (line.contains("event")) {
                addEvent(line);
            } else {
                printConfused();
            }
            line = in.nextLine();
        }
        printExit();
    }
}

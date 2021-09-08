import java.util.Objects;
import java.util.Scanner;

public class Duke {

    public static int taskNum = 0;
    public static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        String userCommand;
        Scanner userType = new Scanner(System.in);
        greet();
        do {
            // Read in the keyboard input from user, and refer to different conditions
            userCommand = userType.nextLine();
            if (userCommand.equals("bye")) {
                break;
            } else if (userCommand.equals("list")) {
                printList(tasks, taskNum);
            } else if (userCommand.contains("done")) {
                int id = Integer.parseInt(userCommand.substring(5));
                taskDone(tasks[id - 1]);
            } else if (userCommand.startsWith("todo")) {
                addTodo(userCommand);
            } else if (userCommand.startsWith("event")) {
                addEvent(userCommand);
            } else if (userCommand.startsWith("deadline")) {
                addDeadline(userCommand);
            } else {
                printSign();
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printSign();
            }
        } while (!Objects.equals(userCommand, "bye"));
        exit();
    }

    public static void greet() {
        // the function is used to greet user in the very first beginning
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printSign();
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");
        printSign();
    }

    public static void addTodo(String userCommand) {
        try {
            int contentStart = 5;
            String description = userCommand.substring(contentStart);
            tasks[taskNum] = new Todo(description);
            taskNum++;
            printTotalNumOfTasks(tasks, taskNum);
        }
        catch (StringIndexOutOfBoundsException e) {
            printSign();
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            printSign();
        }
    }

    public static void addEvent(String userCommand) {
        try {
            int contentStart = 6;
            int contentEnd = userCommand.indexOf("/at") - 1;
            int atStart = userCommand.indexOf("/at") + 4;
            String description = userCommand.substring(contentStart, contentEnd);
            String at = userCommand.substring(atStart);
            tasks[taskNum] = new Event(description, at);
            taskNum++;
            printTotalNumOfTasks(tasks, taskNum);
        }
        catch (StringIndexOutOfBoundsException e) {
            printSign();
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
            printSign();
        }
    }

    public static void addDeadline(String userCommand) {
        try {
            int contentStart = 9;
            int contentEnd = userCommand.indexOf("/by") - 1;
            int byStart = userCommand.indexOf("/by") + 4;
            String description = userCommand.substring(contentStart, contentEnd);
            String by = userCommand.substring(byStart);
            tasks[taskNum] = new Deadline(description, by);
            taskNum++;
            printTotalNumOfTasks(tasks, taskNum);
        }
        catch (StringIndexOutOfBoundsException e) {
            printSign();
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            printSign();
        }
    }

    public static void taskDone(Task args) {
        // Mark the relevant task as "done", and print out a line indicates that the task is marked as done
        args.markAsDone();
        printSign();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(args.toString());
        printSign();
    }

    public static void printList(Task[] args, int length) {
        // Print out the whole task list with their status icons
        printSign();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < length; i++) {
            System.out.print(i + 1 + ".");
            System.out.println(args[i].toString());
        }
        printSign();
    }

    public static void printTotalNumOfTasks(Task[] args, int total) {
        // Print out the total number of the tasks and what is added to do
        printSign();
        System.out.println("Got it. I've added this task:");
        System.out.println(args[total-1].toString());
        System.out.println("Now you have " + total + " tasks in the list");
        printSign();
    }

    public static void exit() {
        // Exit the program one user key in "bye"
        printSign();
        System.out.println("Bye. Hope to see you again soon!\n");
        printSign();
    }

    public static void printSign() {
        // Print out a "-" line
        for (int i = 1; i <= 40; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
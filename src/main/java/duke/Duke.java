package duke;

import duke.command.Deadline;
import duke.command.Event;
import duke.command.Todo;
import duke.task.Task;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static int taskNum = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();

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
                printList();
            } else if (userCommand.contains("done")) {
                int id = Integer.parseInt(userCommand.substring(5));
                taskDone(tasks.get(id - 1));
            } else if (userCommand.startsWith("todo")) {
                addTodo(userCommand);
            } else if (userCommand.startsWith("event")) {
                addEvent(userCommand);
            } else if (userCommand.startsWith("deadline")) {
                addDeadline(userCommand);
            } else if (userCommand.startsWith("delete")) {
                deleteTask(userCommand);
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
        System.out.println("Hello! I'm duke.Duke\n");
        System.out.println("What can I do for you?\n");
        printSign();
    }

    public static void addTodo(String userCommand) {
        //add exception
        try {
            int contentStart = 5;
            String description = userCommand.substring(contentStart);
            tasks.add(new Todo(description));
            taskNum++;
            printTotalNumOfTasks(tasks.get(taskNum - 1), taskNum);
        }
        catch (StringIndexOutOfBoundsException e) {
            printSign();
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            printSign();
        }
    }

    public static void addEvent(String userCommand) {
        //add exception
        try {
            int contentStart = 6;
            int contentEnd = userCommand.indexOf("/at") - 1;
            int atStart = userCommand.indexOf("/at") + 4;
            String description = userCommand.substring(contentStart, contentEnd);
            String at = userCommand.substring(atStart);
            tasks.add(new Event(description, at));
            taskNum++;
            printTotalNumOfTasks(tasks.get(taskNum - 1), taskNum);
        }
        catch (StringIndexOutOfBoundsException e) {
            printSign();
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
            printSign();
        }
    }

    public static void addDeadline(String userCommand) {
        //add exception
        try {
            int contentStart = 9;
            int contentEnd = userCommand.indexOf("/by") - 1;
            int byStart = userCommand.indexOf("/by") + 4;
            String description = userCommand.substring(contentStart, contentEnd);
            String by = userCommand.substring(byStart);
            tasks.add(new Deadline(description, by));
            taskNum++;
            printTotalNumOfTasks(tasks.get(taskNum - 1), taskNum);
        }
        catch (StringIndexOutOfBoundsException e) {
            printSign();
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            printSign();
        }
    }

    public static void deleteTask(String userCommand) {
        try {
            int contentStart = 7;
            int id = Integer.parseInt(userCommand.substring(contentStart));
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.get(id - 1).toString());
            tasks.remove(id - 1);
            taskNum--;
            System.out.println("Now you have " + taskNum + " tasks in the list.");
            printSign();
        }
        catch (StringIndexOutOfBoundsException e) {
            printSign();
            System.out.println("☹ OOPS!!! The index of the task to be deleted cannot be empty.");
            printSign();
        }
    }

    public static void taskDone(Task args) {
        // Mark the relevant task as "done", and print out a line indicates that the task is marked as done
        try {
            args.markAsDone();
            printSign();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(args.toString());
            printSign();
        }
        catch (StringIndexOutOfBoundsException e) {
            printSign();
            System.out.println("☹ OOPS!!! The index of the task to be marked as done cannot be empty.");
            printSign();
        }
    }

    public static void printList() {
        // Print out the whole task list with their status icons
        printSign();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.print(i + ".");
            System.out.println(tasks.get(i - 1).toString());
        }
        printSign();
    }

    public static void printTotalNumOfTasks(Task args, int total) {
        // Print out the total number of the tasks and what is added to do
        printSign();
        System.out.println("Got it. I've added this task:");
        System.out.println(args.toString());
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
package duke.manager;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    /**
     * Prints a line on the console
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Determines the task type,
     * assuming it is the first
     * word in the message and
     * returns it
     *
     * @param message The input by the user.
     * @return The first word of the string.
     */
    private static String taskType(String message) {
        String[] type = message.split(" ");
        return type[0];
    }


    /**
     * The function adds the task input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addTask(ArrayList<Task> tasks, String message) {
        switch (taskType(message)) {
        case "todo":
            addTodo(tasks, message);
            break;
        case "deadline":
            addDeadline(tasks, message);
            break;
        case "event":
            addEvent(tasks, message);
            break;
        default:
            printLine();
            System.out.println("I really cannot understand what you wrote");
            printLine();
            // Fallthrough
        }
        if (Task.COUNT > 0) {
            printAddedTask(tasks.get(Task.COUNT - 1));
        }
    }

    /**
     * The function prints the recently added task
     */
    private static void printAddedTask(Task task) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.getDescription());
        System.out.println("Now you have " + Task.COUNT + " tasks in the list.");
        printLine();
    }

    /**
     * The function adds the todo task input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addEvent(ArrayList<Task> tasks, String message) {
        try {
            message = message.substring(6);
            String[] eventData = message.split("/", 2);
            tasks.set(Task.COUNT, new Event(eventData[0], eventData[1].substring(3)));
            Task.COUNT++;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("YOU IDIOT !!??!! The description of an event cannot be empty.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("YOU IDIOT !!??!! The input format should be : ");
            System.out.println("event description /on date or time");
        }
    }

    /**
     * The function adds the deadline task input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addDeadline(ArrayList<Task> tasks, String message) {
        try {
            message = message.substring(9);
            String[] deadlineData = message.split("/", 2);
            tasks.set(Task.COUNT, new Deadline(deadlineData[0], deadlineData[1].substring(3)));
            Task.COUNT++;
        } catch (StringIndexOutOfBoundsException e) {
            printLine();
            System.out.println("YOU IDIOT !!??!! The description of a deadline cannot be empty.");
            printLine();
        } catch (ArrayIndexOutOfBoundsException d) {
            printLine();
            System.out.println("YOU IDIOT !!??!! The input format should be : ");
            System.out.println("deadline description /by date or time");
            printLine();
        }
    }

    /**
     * The function adds the event input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addTodo(ArrayList<Task> tasks, String message) {
        try {
            tasks.set(Task.COUNT, new Todo(message.substring(5)));
            Task.COUNT++;
        } catch (StringIndexOutOfBoundsException e) {
            printLine();
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
            printLine();
        }
    }

    /**
     * Prints all the tasks
     *
     * @param tasks the array of tasks
     */
    private static void printTasks(ArrayList<Task> tasks) {
        printLine();
        for (int i = 0; i < Task.COUNT; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getDescription());
        }
        if (Task.COUNT == 0) {
            System.out.println("Smartass, you need to add tasks before listing them !!!");
        }
        printLine();
    }

    /**
     * Marks the task done based
     * on the integer value provided
     * by the user in the String message.
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void markDone(ArrayList<Task> tasks, String message) {
        try {
            String[] arrOfStr = message.split(" ");
            int index = Integer.parseInt(arrOfStr[arrOfStr.length - 1]) - 1;
            tasks.get(index).isDone();
        } catch (NullPointerException e) {
            printLine();
            System.out.println("OH MY GOD, can you maybe type a task that exists ?");
            printLine();
        }
    }

    /**
     * The function processes the inputs
     * by the user and calls the necessary
     * functions to add, print and manipulate
     * tasks.
     */
    public static void processInput() {

        // Task[] tasks = new Task[Task.MAX];
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        while (!message.equals("bye")) {
            if (message.equals("list")) {
                printTasks(tasks);
            } else if (message.contains("done")) {
                markDone(tasks, message);
            } else {
                addTask(tasks, message);
            }
            message = scanner.nextLine();
        }
        scanner.close();
    }
}

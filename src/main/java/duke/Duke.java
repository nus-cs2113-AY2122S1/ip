package duke;

import java.util.Arrays;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A personal assistant chatbot.
 */
public class Duke {
    private static final String INDENTED_HORIZONTAL_LINE = " ".repeat(4) + "_".repeat(60);
    /** Array of Task objects */
    private static final Task[] tasks = new Task[100];
    /** Number of tasks */
    private static int taskCount = 0;

    private static String indent(String text) {
        String[] lines = text.split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            lines[i] = " ".repeat(5) + lines[i];
        }
        return String.join(System.lineSeparator(), lines);
    }

    /**
     * Prints out the specified text formatted as a response block.
     * Horizontal lines will be printed before and after the specified text, and the text will be indented.
     *
     * @param text Text to be printed out.
     */
    private static void printResponseBlock(String text) {
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println(indent(text));
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println();
    }

    private static void printGreeting() {
        String greeting = "Hello! I'm Duke" + System.lineSeparator()
                + "What can I do for you?";
        printResponseBlock(greeting);
    }

    /** Prints out the list of tasks (numbered) together with their status icons */
    private static void printTasks() {
        String[] formattedTasks = new String[taskCount];
        for (int i = 0; i < taskCount; i++) {
            formattedTasks[i] = String.format("%d.%s", i + 1, tasks[i]);
        }
        String formattedTaskList = String.join(System.lineSeparator(), formattedTasks);
        printResponseBlock("Here are the tasks in your list:" + System.lineSeparator()
                + formattedTaskList);
    }

    private static void markTaskAsDone(int taskId) {
        Task task = tasks[taskId];
        task.setAsDone();
        String formattedTask = "  " + task;
        printResponseBlock("Nice! I've marked this task as done:" + System.lineSeparator()
                + formattedTask);
    }

    private static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        printResponseBlock("Got it. I've added this task:" + System.lineSeparator()
                + "  " + task + System.lineSeparator()
                + "Now you have " + taskCount
                + (taskCount > 1
                ? " tasks in the list"
                : " task in the list")
        );
    }

    private static void addTodo(String description) {
        if (description.isEmpty()) {
            printResponseBlock("☹ OOPS!!! The description of a todo cannot be empty.");
            return;
        }
        Task task = new Todo(description);
        addTask(task);
    }

    private static void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        addTask(task);
    }

    private static void addEvent(String description, String at) {
        Task task = new Event(description, at);
        addTask(task);
    }

    private static void handleUnrecognisedCommand() {
        printResponseBlock("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Reads input commands from the user and executes the appropriate actions.
     * Upon receiving the "bye" command, stops waiting for user input and returns.
     */
    private static void handleCommands() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();
            String[] words = line.split(" ");
            String args = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
            String[] splitArgs;

            switch (words[0]) {
            case "bye":
                return;
            case "todo":
                addTodo(args);
                break;
            case "deadline":
                splitArgs = args.split(" /by ");
                addDeadline(splitArgs[0], splitArgs[1]);
                break;
            case "event":
                splitArgs = args.split(" /at ");
                addEvent(splitArgs[0], splitArgs[1]);
                break;
            case "list":
                printTasks();
                break;
            case "done":
                int taskId = Integer.parseInt(words[1]) - 1;
                markTaskAsDone(taskId);
                break;
            default:
                handleUnrecognisedCommand();
                break;
            }
        }
    }

    private static void printFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        printResponseBlock(farewell);
    }

    public static void main(String[] args) {
        printGreeting();
        handleCommands();
        printFarewell();
    }
}

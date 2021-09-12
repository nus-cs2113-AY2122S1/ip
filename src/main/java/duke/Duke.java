package duke;

import java.util.ArrayList;
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
    /** Platform independent line separator */
    private static final String LS = System.lineSeparator();

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String MESSAGE_GREETING = "Hello! I'm Duke" + LS + "What can I do for you?";
    private static final String MESSAGE_FAREWELL = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_ERROR = "☹ OOPS!!! %1$s";
    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:" + LS + "  %1$s" + LS
            + "Now you have %2$s task(s) in the list";
    private static final String MESSAGE_TASK_LIST = "Here are the tasks in your list:" + LS + "%1$s";
    private static final String MESSAGE_TASK_MARKED_AS_DONE = "Nice! I've marked this task as done:" + LS + "%1$s";

    private static final String MESSAGE_TODO_DESCRIPTION_EMPTY = "The description of a todo cannot be empty.";
    private static final String MESSAGE_UNRECOGNISED_COMMAND = "I'm sorry, but I don't know what that means :-(";

    private static final String DEADLINE_PREFIX_BY = "/by";
    private static final String EVENT_PREFIX_AT = "/at";

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_LIST_TASKS = "list";
    private static final String COMMAND_MARK_TASK_AS_DONE = "done";

    /** Array of Task objects */
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Main entry point of Duke.
     */
    public static void main(String[] args) {
        printGreeting();
        while (true) {
            final String userInput = getUserInput();
            final String feedback = executeCommand(userInput);
            printResponseBlock(feedback);
        }
    }

    private static void printGreeting() {
        printResponseBlock(MESSAGE_GREETING);
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

    private static String indent(String text) {
        String[] lines = text.split(LS);
        for (int i = 0; i < lines.length; i++) {
            lines[i] = " ".repeat(5) + lines[i];
        }
        return String.join(LS, lines);
    }

    /**
     * Reads input commands from the user.
     * Ignores blank lines and trims input command.
     *
     * @return Trimmed input command.
     */
    private static String getUserInput() {
        String line = SCANNER.nextLine();
        // Ignore blank lines
        while (line.trim().isEmpty()) {
            line = SCANNER.nextLine();
        }
        return line.trim();
    }

    /**
     * Executes the command specified by the input.
     *
     * @param userInput Input command together with any arguments.
     * @return Feedback about what was executed.
     */
    private static String executeCommand(String userInput) {
        final String[] commandAndArgs = userInput.split(" ", 2);
        final String command = commandAndArgs[0];
        final String args = commandAndArgs.length > 1 ? commandAndArgs[1] : "";

        try {
            switch (command) {
            case COMMAND_EXIT:
                printFarewell();
                System.exit(0);
                // fallthrough
            case COMMAND_ADD_TODO:
                return addTodo(args);
            case COMMAND_ADD_DEADLINE:
                return addDeadline(args);
            case COMMAND_ADD_EVENT:
                return addEvent(args);
            case COMMAND_LIST_TASKS:
                return listTasks();
            case COMMAND_MARK_TASK_AS_DONE:
                return markTaskAsDone(args);
            default:
                return handleUnrecognisedCommand();
            }
        } catch (DukeException e) {
            return String.format(MESSAGE_ERROR, e.getMessage());
        }
    }

    private static void printFarewell() {
        printResponseBlock(MESSAGE_FAREWELL);
    }

    private static String addTodo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException(MESSAGE_TODO_DESCRIPTION_EMPTY);
        }
        Task task = new Todo(description);
        return addTask(task);
    }

    private static String addDeadline(String args) {
        final String[] splitArgs = args.split(" " + DEADLINE_PREFIX_BY + " ");
        final String description = splitArgs[0];
        final String by = splitArgs[1];
        Task task = new Deadline(description, by);
        return addTask(task);
    }

    private static String addEvent(String args) {
        final String[] splitArgs = args.split(" " + EVENT_PREFIX_AT + " ");
        final String description = splitArgs[0];
        final String at = splitArgs[1];
        Task task = new Event(description, at);
        return addTask(task);
    }

    private static String addTask(Task task) {
        tasks.add(task);
        return String.format(MESSAGE_TASK_ADDED, task, tasks.size());
    }

    /** Returns the list of tasks (numbered) together with their status icons */
    private static String listTasks() {
        String[] formattedTasks = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            formattedTasks[i] = String.format("%d.%s", i + 1, tasks.get(i));
        }
        String taskListOutput = String.join(LS, formattedTasks);
        return String.format(MESSAGE_TASK_LIST, taskListOutput);
    }

    private static String markTaskAsDone(String args) {
        int taskId = Integer.parseInt(args) - 1;
        Task task = tasks.get(taskId);
        task.setAsDone();
        String formattedTask = "  " + task;
        return String.format(MESSAGE_TASK_MARKED_AS_DONE, formattedTask);
    }

    private static String handleUnrecognisedCommand() throws DukeException {
        throw new DukeException(MESSAGE_UNRECOGNISED_COMMAND);
    }
}

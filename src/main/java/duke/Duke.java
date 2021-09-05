package duke;

import duke.task.Task;
import duke.task.TaskManager;

import java.util.Scanner;

public class Duke {
    // Line width
    private static final int LINE_WIDTH = 60;

    // Command strings
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";

    // Task manager
    private static TaskManager taskManager = new TaskManager();

    // Print horizontal line for improving readability
    private static void printHorizontalLine() {
        System.out.println("_".repeat(LINE_WIDTH));
    }

    // Print greeting message
    private static void printGreeting() {
        printHorizontalLine();
        String greeting = " Hello! I'm Duke" + System.lineSeparator() +
                " What can I do for you?";
        System.out.println(greeting);
        printHorizontalLine();
        System.out.print(System.lineSeparator());
    }

    // Print farewell message
    private static void printFarewell() {
        String farewell = " Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    // Print invalid command warning
    private static void printInvalid() {
        String invalid = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(invalid);
    }

    // Print command with no description warning
    private static void printEmptyDescription(String command) {
        String emptyDescription = " ☹ OOPS!!! The description of a " +
                command + " cannot be empty.";
        System.out.println(emptyDescription);
    }

    // Print task added message
    private static void printAddTask(Task task) {
        if (task == null) {
            return;
        }
        String addTask = " Got it. I've added this task:" + System.lineSeparator() +
                "  " + task.toString() + System.lineSeparator() +
                " Now you have " + task.getNoOfTasks() +
                " tasks in the list.";
        System.out.println(addTask);
    }

    private static void printInvalidTaskNumberFormat() {
        String message = "Task ID must be an integer!";
        System.out.println(message);
    }

    private static void printInvalidTaskNumber() {
        String message = "Task ID does not exist!";
        System.out.println(message);
    }

    private static void executeList() {
        System.out.println(" Here are the tasks in your list:");
        taskManager.listTasks();
    }

    private static void executeDone(String taskId) {
        int num;
        try {
            if (taskId.equals("") || taskId.equals("done")) {
                throw new DukeException("Please provide task ID");
            }
            num = Integer.parseInt(taskId);
        } catch (NumberFormatException e) {
            printInvalidTaskNumberFormat();
            return;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return;
        }
        Task task;
        try {
            task = taskManager.markAsDone(num - 1);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            printInvalidTaskNumber();
            return;
        }
        System.out.println(" Nice! I've marked this task as done:" +
                System.lineSeparator() + "   " + task.toString());
    }

    private static void executeAdd(String line, String command) {
        int descriptionIndex = line.indexOf(" ");
        String description;
        try {
            description = line.substring(descriptionIndex).strip();
        } catch (StringIndexOutOfBoundsException e) {
            printEmptyDescription(command);
            return;
        }
        Task task = null;
        switch (command) {
        case COMMAND_TODO:
            task = taskManager.addTodo(description);
            break;
        case COMMAND_DEADLINE:
            task = taskManager.addDeadline(description);
            break;
        case COMMAND_EVENT:
            task = taskManager.addEvent(description);
            break;
        default:
            printInvalid();
            break;
        }
        printAddTask(task);
    }

    private static void execute(String line) {
        String[] words = line.split(" "); // Convert sentence into array of words
        String command = words[0];
        switch (command) {
        case COMMAND_BYE:
            printFarewell();
            break;
        case COMMAND_LIST:
            executeList();
            break;
        case COMMAND_DONE:
            int spacePos = line.indexOf(" ");
            String taskId = line.substring(spacePos + 1).strip();
            executeDone(taskId);
            break;
        case COMMAND_TODO:
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            executeAdd(line, command);
            break;
        default:
            printInvalid();
            break;
        }
    }

    public static void main(String[] args) {
        printGreeting();
        String line;
        Scanner in = new Scanner(System.in);
        do {
            line = in.nextLine();
            try {
                if (line.strip().equals("")) {
                    throw new DukeException("Empty Command");
                }
            } catch (DukeException e) {
                printHorizontalLine();
                printInvalid();
                printHorizontalLine();
                System.out.print(System.lineSeparator());
                continue;
            }
            printHorizontalLine();
            execute(line);
            printHorizontalLine();
            System.out.print(System.lineSeparator());
        } while (!line.equals(COMMAND_BYE));
    }
}

package duke;

import duke.file.FileManager;
import duke.task.Task;
import duke.task.TaskManager;

import java.io.FileNotFoundException;
import java.util.Scanner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

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
    private static final String COMMAND_DELETE = "delete";

    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "src", "main", "status", "status.txt");
    private static final Path directoryPath = Paths.get(root, "src", "main", "status");

    // Task manager
    private static final TaskManager taskManager = new TaskManager();

    // File manager
    private static final FileManager fileManager = new FileManager(filePath.toString(), directoryPath.toString());

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
                " Now you have " + taskManager.getTasksCount() +
                " tasks in the list.";
        System.out.println(addTask);
    }

    private static void printDeleteTask(Task task) {
        String deleteTask = " Got it! I've removed this task:" +
                System.lineSeparator() + "   " + task.toString() +
                System.lineSeparator() + " Now you have " +
                taskManager.getTasksCount() + " tasks in the list.";
        System.out.println(deleteTask);
    }

    private static void printInvalidTaskNumberFormat() {
        String message = "Task ID must be an integer!";
        System.out.println(message);
    }

    private static void printInvalidTaskNumber() {
        String message = "Task ID does not exist!";
        System.out.println(message);
    }

    private static void printSavedTasks() {
        printHorizontalLine();
        System.out.println("Current tasks successfully saved at: " +
                System.lineSeparator() + filePath.toString());
        printHorizontalLine();
    }

    private static void executeList() {
        System.out.println(" Here are the tasks in your list:");
        taskManager.listTasks();
    }

    private static void executeDone(String line) {
        Integer taskId = getTaskId(line);
        if (taskId == null) {
            return;
        }
        Task task;
        try {
            task = taskManager.markAsDone(taskId - 1);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
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

    private static void executeDelete(String line) {
        Integer taskId = getTaskId(line);
        if (taskId == null) {
            return;
        }
        Task task;
        try {
            task = taskManager.deleteTask(taskId - 1);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            printInvalidTaskNumber();
            return;
        }
        printDeleteTask(task);
    }

    private static Integer getTaskId(String line) {
        int spacePos = line.indexOf(" ");
        String taskId = line.substring(spacePos + 1).strip();
        int num;
        try {
            if (taskId.equals("") || taskId.equals("done") || taskId.equals("delete")) {
                throw new DukeException("Please provide task ID");
            }
            num = Integer.parseInt(taskId);
        } catch (NumberFormatException e) {
            printInvalidTaskNumberFormat();
            return null;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return num;
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
            executeDone(line);
            break;
        case COMMAND_TODO:
        case COMMAND_DEADLINE:
        case COMMAND_EVENT:
            executeAdd(line, command);
            break;
        case COMMAND_DELETE:
            executeDelete(line);
            break;
        default:
            printInvalid();
            break;
        }
    }

    private static void saveDuke() {
        try {
            fileManager.saveDukeStatus(taskManager);
        } catch (IOException e) {
            System.out.println("File write error");
        }
        printSavedTasks();
    }

    private static void initialiseDuke() {
        fileManager.createDirectory();
        try {
            fileManager.createFile();
        } catch (IOException e) {
            System.out.println("An error has occurred");
        }
        try {
            fileManager.initialiseDukeStatus(taskManager);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("File error!");
        }
    }

    public static void main(String[] args) {
        initialiseDuke();
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
        saveDuke();
    }
}

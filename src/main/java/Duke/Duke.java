package Duke;

import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    //define constants
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";

    private static final String DEADLINE_BY_PREFIX = "/by ";
    private static final String EVENT_AT_PREFIX = "/at ";

    //pepepopo response text output
    private static final String GREETING = "Hello! I'm Pepepopo\n" +
                                            "What can I do for you?";
    private static final String DIVIDER = "____________________________________________________________";
    private static final String BYE = "PLEASE DONT LEAVE :( \n" +
                                        "a....noo.....ahhhhh..\n" +
                                        "..AAAAAAGHHHH.......pepepopo loved u :(";

    private static final String TASK_ADD = "New task! Pepepopo has added it to the list: ";
    private static final String TASK_DONE = "Yay! Pepepopo has marked your task as done:";

    private static final String TODO_EMPTY = "Todo cannot be empty!";
    private static final String DEADLINE_EMPTY = "Deadline cannot be empty!";
    private static final String EVENT_EMPTY = "Event cannot be empty!";
    private static final String INVALID_INPUT = "Invalid input, try another command";
    private static final String INVALID_DONE_NUMBER = "Please enter a valid number after done";

    private static Task[] tasks = new Task[100];

    public static void greeting() {
        printDiv(GREETING);
    }

    public static void added(Task task) {
        printDiv(TASK_ADD +
                    "\n  " + task +
                    "\nYou now have " + Task.getTaskCount() + " task(s) in your list.");
    }

    public static void done(Task[] tasks, int taskNumber) {
        printDiv(TASK_DONE +
                "\n" + tasks[taskNumber]);
    }

    public static String getInput() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        return line;
    }

    public static void executeCommand(String input) throws DukeException {
        String[] commandAndParams = splitString(input, " ");
        String command = commandAndParams[0];
        String params = commandAndParams[1];
        switch (command) {
        case COMMAND_TODO:
            try {
                executeTodo(params);
            } catch (DukeException e) {
                printDiv(TODO_EMPTY);
            }
            break;
        case COMMAND_DEADLINE:
            try {
                executeDeadline(params);
            } catch (DukeException e) {
                printDiv(DEADLINE_EMPTY);
            }
            break;
        case COMMAND_EVENT:
            try {
                executeEvent(params);
            } catch (DukeException e) {
                printDiv(EVENT_EMPTY);
            }
            break;
        case COMMAND_DONE:
            executeDone(params);
            break;
        case COMMAND_LIST:
            executeList();
            break;
        case COMMAND_BYE:
            executeBye();
            break;
        default:
            throw new DukeException();
        }
    }

    public static void addTask(Task t) {
        tasks[Task.getTaskCount()] = t;
        Task.setTaskCount();
        added(t);
    }

    public static void executeTodo(String params) throws DukeException {
        if (params == "") {
            throw new DukeException();
        }
        Todo t = new Todo(params);
        addTask(t);
    }

    public static void executeDeadline(String params) throws DukeException {
        if (params == "") {
            throw new DukeException();
        }
        String[] descAndBy = splitString(params, DEADLINE_BY_PREFIX);
        String description = descAndBy[0];
        String by = descAndBy[1];
        Deadline d = new Deadline(description, by);
        addTask(d);
    }

    public static void executeEvent(String params) throws DukeException {
        if (params == "") {
            throw new DukeException();
        }
        String[] descAndAt = splitString(params, EVENT_AT_PREFIX);
        String description = descAndAt[0];
        String at = descAndAt[1];
        Event e = new Event(description, at);
        addTask(e);
    }

    public static void executeDone(String params) {
        if (isDone(params)) {
            int taskNumber = Integer.parseInt(params) - 1;
            tasks[taskNumber].setDone();
            done(tasks, taskNumber);
        }
    }

    public static void executeList() {
        Task[] listPrint = Arrays.copyOf(tasks, Task.getTaskCount());
        int curr = 1;
        for (Task item : listPrint) {
            System.out.println(curr + "." + item);
            curr += 1;
        }
        print(DIVIDER);
    }

    public static void executeBye() {
        printDiv(BYE);
        System.exit(0);
    }

    public static String[] splitString(String input, String separator) {
        String[] split = input.trim().split(separator, 2);
        return split.length == 2 ? split : new String[] {split[0], ""};
    }

    public static boolean isDone(String params) {
        if (!isNumeric(params)) {
            return false;
        }
        int taskNumber = Integer.parseInt(params);
        if (taskNumber > Task.getTaskCount()) {
            printDiv(INVALID_DONE_NUMBER);
            return false;
        } else {
            return true;
        }
    }

    public static boolean isNumeric(String string) {
        try {
            int intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            printDiv(INVALID_DONE_NUMBER);
        }
        return false;
    }

    public static void printDiv(String text) {
        System.out.println(text);
        System.out.println(DIVIDER);
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        greeting();

        while (true) {
            String input = getInput();
            try {
                executeCommand(input);
            } catch (DukeException e) {
                printDiv(INVALID_INPUT);
            }
        }

    }
}

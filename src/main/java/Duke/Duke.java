package Duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {
    //define constants
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
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
    private static final String TASK_DELETE = "Alright! Pepepopo has removed the task:";

    private static final String TODO_EMPTY = "Todo cannot be empty!";
    private static final String DEADLINE_EMPTY = "Deadline cannot be empty!";
    private static final String EVENT_EMPTY = "Event cannot be empty!";
    private static final String INVALID_INPUT = "Invalid input, try another command";
    private static final String INVALID_NUMBER = "Please enter a valid number after the command";
    private static final String INVALID_DONE_NUMBER = "Please enter a number within the list";

//    private static Task[] tasks = new Task[100];
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static String getInput() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        return line;
    }

    public static String readFile() {
        
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
                print(TODO_EMPTY);
            }
            break;
        case COMMAND_DEADLINE:
            try {
                executeDeadline(params);
            } catch (DukeException e) {
                print(DEADLINE_EMPTY);
            }
            break;
        case COMMAND_EVENT:
            try {
                executeEvent(params);
            } catch (DukeException e) {
                print(EVENT_EMPTY);
            }
            break;
        case COMMAND_DONE:
            executeDone(params);
            break;
        case COMMAND_LIST:
            executeList();
            break;
        case COMMAND_DELETE:
            executeDelete(params);
            break;
        case COMMAND_BYE:
            executeBye();
            break;
        default:
            throw new DukeException();
        }
    }

    public static void addTask(Task t) {
        tasks.add(t);
        Task.setTaskCount(1);
        printAdd(t);
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
            tasks.get(taskNumber).setDone();
            printDone(taskNumber);
        }
    }

    public static void executeDelete(String params) {
        if (isDone(params)) {
            int taskNumber = Integer.parseInt(params) - 1;
            Task.setTaskCount(-1);
            printDelete(taskNumber);
            tasks.remove(taskNumber);
        }
    }

    public static void executeList() {
//        Task[] listPrint = Arrays.copyOf(tasks, Task.getTaskCount());
//        int curr = 1;
        for (Task item : tasks) {
            int curr = tasks.indexOf(item) + 1;
            System.out.println(curr + "." + item);
        }
        print("");
    }

    public static void executeBye() {
        print(BYE);
        System.exit(0);
    }

    public static String[] splitString(String input, String separator) {
        String[] split = input.trim().split(separator, 2);
        return split.length == 2 ? split : new String[] {split[0], ""};
    }

    public static boolean isDone(String params) {
        isInt(params);
        int taskNumber = Integer.parseInt(params);
        if (taskNumber > Task.getTaskCount()) {
            print(INVALID_NUMBER);
            return false;
        } else {
            return true;
        }
    }

    public static void isInt(String string) {
        try {
            int intValue = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            print(INVALID_NUMBER);
        }
    }

    public static void printGreeting() {
        print(GREETING);
    }

    public static void printAdd(Task task) {
        print(TASK_ADD +
                "\n  " + task +
                "\nYou now have " + Task.getTaskCount() + " task(s) in your list.");
    }

    public static void printDone(int taskNumber) {
        print(TASK_DONE +
                "\n" + tasks.get(taskNumber));
    }

    public static void printDelete(int taskNumber) {
        print(TASK_DELETE +
                "\n" + tasks.get(taskNumber) +
                "\nYou now have " + Task.getTaskCount() + " task(s) in your list.");
    }

    public static void print(String text) {
        if (!text.equals("")) {
            System.out.println(text);
        }
            System.out.println(DIVIDER);
    }

    //public static void print(String text) {
//        System.out.println(text);
//    }

    public static void main(String[] args) {
        printGreeting();

        while (true) {
            String input = getInput();
            try {
                executeCommand(input);
            } catch (DukeException e) {
                print(INVALID_INPUT);
            }
        }

    }
}

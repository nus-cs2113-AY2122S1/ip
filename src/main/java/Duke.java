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

    private static final String TODO_EMPTY = "todo cannot be empty!";
    private static final String DEADLINE_EMPTY = "deadline cannot be empty!";
    private static final String EVENT_EMPTY = "event cannot be empty!";
    private static final String INVALID_INPUT = "invalid input, try another command";

    private static Task[] tasks = new Task[100];

    /*
    Standard text output by pepepopo
     */
    public static void greeting() {
        String greeting = " Hello! I'm Pepepopo\n" +
                        " What can I do for you?";
        System.out.println(greeting);
        divider();
    }

    public static void divider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    public static void bye() {
        String bye = "PLEASE DONT LEAVE :( \n" +
                    "a....noo.....ahhhhh..\n" +
                    "..AAAAAAGHHHH.......pepepopo loved u :(";
        System.out.println(bye);
        divider();

    }

    public static void added(Task t) {
        System.out.println("New task! Pepepopo has added it to the list: ");
        System.out.println("  " + t);
        System.out.println("You now have " + Task.getTaskCount() + " task(s) in your list.");
    }

    public static void done(Task[] tasks, int taskNumber) {
        System.out.println("Yay! Pepepopo has marked your task as done:");
        System.out.println(tasks[taskNumber]);
        divider();
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
                System.out.println(TODO_EMPTY);
                divider();
            }
            break;
        case COMMAND_DEADLINE:
            try {
                executeDeadline(params);
            } catch (DukeException e) {
                System.out.println(DEADLINE_EMPTY);
                divider();
            }
            break;
        case COMMAND_EVENT:
            try {
                executeEvent(params);
            } catch (DukeException e) {
                System.out.println(EVENT_EMPTY);
                divider();
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
        divider();
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
        divider();
    }

    public static void executeBye() {
        bye();
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
        }
        return false;
    }

    public static void main(String[] args) {
        greeting();

        while (true) {
            String input = getInput();
            try {
                executeCommand(input);
            } catch (DukeException e) {
                System.out.println(INVALID_INPUT);
                divider();
            }
        }

    }
}

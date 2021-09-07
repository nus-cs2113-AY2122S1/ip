import java.util.Scanner;

public class Duke {
    private static final int ARRAY_SIZE = 100;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int EIGHT = 8;
    private static final int NINE = 9;
    private static final int ERROR_TODO_IS_EMPTY = 1;
    private static final int ERROR_EVENT_IS_EMPTY = 2;
    private static final int ERROR_DEADLINE_IS_EMPTY = 3;
    private static final int ERROR_COMMAND_NOT_FOUND = 4;
    private static final Task[] items = new Task[ARRAY_SIZE];
    private static int taskCount = 0;
    private static final String logo = " _____  ___ _____\n"
            + "|___  | | ||_____| \n"
            + "   / /  | |   / / \n"
            + "  / /   | |  / /  \n"
            + " / /___ | | /_/__  \n"
            + "|_____| | ||_____| \n";
    private static final String border = "____________________________________________________________\n";

    public static void addTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    public static void addEvent(String description, String time) {
        Events newEvent = new Events(description, time);
        items[taskCount] = newEvent;
        taskCount++;
        addTaskMessage(newEvent);
    }
    public static void addDeadline(String description, String by) {
        Deadlines newDeadline = new Deadlines(description, by);
        items[taskCount] = newDeadline;
        taskCount++;
        addTaskMessage(newDeadline);
    }
    public static void addTodo(String description) {
        Todo newTodo = new Todo(description);
        items[taskCount] = newTodo;
        taskCount++;
        addTaskMessage(newTodo);
    }
    public static void addTask(String description) {
        System.out.println(border + "added: " + description + '\n' + border);
        Task newItem = new Task(description);
        items[taskCount] = newItem;
        taskCount++;
    }
    public static void printStartMessage() {
        System.out.println(logo);
        System.out.println(border + "Hi bro, my name is Echo");
        System.out.println("What do you want?\n" + border);
        System.out.println("Type bye to exit\n" + border);
    }
    public static void printEndMessage() {
        System.out.println(border);
        System.out.println("chat again next time!\n" + border);
    }
    public static void printTasks(Task[] items) {
        int j = 1;
        System.out.println(border);
        System.out.println("Here is your list");
        for (Task item : items) {
            if (item != null) {
                System.out.print(j + ".");
                System.out.println(item);
                j++;
            }
        }
    }

    public static void main(String[] args) {
        String line;
        printStartMessage();
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.matches("list")) {
                printTasks(items);
                System.out.println(border);
            } else if (line.length() > FOUR && line.substring(0, FOUR).contains("done")) {
                int dividerPosition = line.indexOf(" ") + 1;
                int endPosition = line.length();
                if (endPosition > FIVE) {
                    String num = line.substring(dividerPosition, endPosition);
                    int taskNum = Integer.parseInt(num) - 1;
                    items[taskNum].markDone();
                    System.out.println(border + "Nice! task is done " + '\n' + border);
                }
            } else if (line.length() >= FOUR && line.substring(0, FOUR).contains("todo")) { //improve condition to first word
                if (line.length() <= FIVE) {
                    DukeException error = new DukeException(ERROR_TODO_IS_EMPTY);
                    error.printError(error.errorType);
                } else {
                    addTodo(line.replace("todo", "").trim());
                }
            } else if (line.length() >= FIVE && line.substring(0, FIVE).contains("event")) {
                if (line.length() > SIX && line.contains("/at")) {
                    String time = line.split("/at")[1].trim();
                    String description = line.split("/at")[0].trim().replace("event", "").trim();
                    addEvent(description, time);
                } else {
                    DukeException error = new DukeException(ERROR_EVENT_IS_EMPTY);
                    error.printError(error.errorType);
                }
            } else if (line.length() >= EIGHT && line.substring(0, EIGHT).contains("deadline")) {
                if (line.length() <= NINE && line.contains("by")) {
                    String by = line.split("/by")[1].trim();
                    String description = line.split("/by")[0].trim().replace("deadline", "").trim();
                    addDeadline(description, by);
                } else {
                    DukeException error = new DukeException(ERROR_DEADLINE_IS_EMPTY);
                    error.printError(error.errorType);
                }
            } else {
                DukeException error = new DukeException(ERROR_COMMAND_NOT_FOUND);
                error.printError(error.errorType);
            }
        } while (!line.matches("bye"));
        printEndMessage();
    }
}

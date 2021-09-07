import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static final int TODO_MIN_LENGTH = 6;
    private static final int TODO_DESCRIPTION_START = 5;
    private static final String TODO_DESCRIPTION_ERROR = "The todo description cannot be empty";
    private static final int EVENT_MIN_LENGTH = 7;
    private static final int EVENT_DESCRIPTION_START = 6;
    private static final String EVENT_EMPTY_ERROR = "The event must contain an '/at'";
    private static final String EVENT_DESCRIPTION_ERROR = "The event description cannot be empty";
    private static final int DEADLINE_MIN_LENGTH = 10;
    private static final int DEADLINE_DESCRIPTION_START = 9;
    private static final String DEADLINE_EMPTY_ERROR = "The deadline must contain a '/by'";
    private static final String DEADLINE_DESCRIPTION_ERROR = "The deadline description cannot be empty";
    private static final int DONE_MIN_LENGTH = 2;
    private static final String DONE_DESCRIPTION_ERROR = "The task to be marked as done cannot be empty";
    private static final String DONE_EXCEED_ERROR = "The task to be marked as done does not exist in the list of tasks";
    private static final int EVENT_DEADLINE_TIME = 3;
    private static final String COMMAND_ERROR = "Sorry, I don't understand what you are saying";


    public static void printHorizontalLine() {
<<<<<<< HEAD
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
=======
        System.out.println(HORIZONTAL_LINE);
>>>>>>> branch-Level-5
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

    public static void markAsDone(Task[] tasks, int currCount, String line) throws DukeException {
        String[] input = line.split(" ");
        if (input.length < DONE_MIN_LENGTH) {
            throw new DukeException(DONE_DESCRIPTION_ERROR);
        }
        int index = Integer.parseInt(input[1]) - 1;
        if (index >= currCount) {
            throw new DukeException(DONE_EXCEED_ERROR);
        }
        tasks[index].markAsDone();
        printHorizontalLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + tasks[index].toString());
        printHorizontalLine();
    }

    public static void printList(Task[] tasks, int currCount) {
        printHorizontalLine();
        System.out.println(" Here are the tasks in your list:");
        Task[] taskList = Arrays.copyOf(tasks, currCount);
        int taskCount = 1;
        for (Task elem : taskList) {
            System.out.println(" " + taskCount + ". " + elem.toString());
            taskCount += 1;
        }
        printHorizontalLine();
    }

    public static int completeAddTask(Task[] tasks, int currCount) {
        printHorizontalLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + tasks[currCount].toString());
        int numTasks = currCount + 1;
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
        printHorizontalLine();
        currCount += 1;
        return currCount;
    }

<<<<<<< HEAD
    public static void printGoodBye() {
=======
    public static void addEvent(Task[] tasks, int currCount, String line) throws DukeException {
        if (line.length() < EVENT_MIN_LENGTH) {
            throw new DukeException(EVENT_DESCRIPTION_ERROR);
        }
        if (!line.contains("/at")) {
            throw new DukeException(EVENT_EMPTY_ERROR);
        }
        String[] input = line.substring(EVENT_DESCRIPTION_START).split("/");
        tasks[currCount] = new Event(input[0], input[1].substring(EVENT_DEADLINE_TIME));
    }

    public static void addDeadline(Task[] tasks, int currCount, String line) throws DukeException {
        if (line.length() < DEADLINE_MIN_LENGTH) {
            throw new DukeException(DEADLINE_DESCRIPTION_ERROR);
        }
        if (!line.contains("/by")) {
            throw new DukeException(DEADLINE_EMPTY_ERROR);
        }
        String[] input = line.substring(DEADLINE_DESCRIPTION_START).split("/");
        // first elem: description, second elem: deadline
        tasks[currCount] = new Deadline(input[0], input[1].substring(EVENT_DEADLINE_TIME));
    }

    public static void addTodo(Task[] tasks, int currCount, String line) throws DukeException {
        if (line.length() < TODO_MIN_LENGTH) {
            throw new DukeException(TODO_DESCRIPTION_ERROR);
        }
        // take the description part of the input string
        tasks[currCount] = new Todo(line.substring(TODO_DESCRIPTION_START));
    }

    public static void goodbye() {
>>>>>>> branch-Level-5
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void main(String[] args) {
<<<<<<< HEAD
        printWelcomeMessage();
        Task[] tasks = new Task[100]; // fixed size array for now, each contains a Task element
=======
        welcome();
        Task[] tasks = new Task[MAX_TASKS]; // fixed size array for now, each contains a Task element
>>>>>>> branch-Level-5
        int currCount = 0;
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
<<<<<<< HEAD
            if (line.contains("done")) { // mark task as done
                markAsDone(tasks, line);
            } else if (line.equals("list")) { // print the list
                printList(tasks, currCount);
            } else { // user inputs a task
                currCount = addTask(tasks, currCount, line);
=======
            try {
                if (line.contains("done")) { // mark task as done
                    markAsDone(tasks, currCount, line);
                } else if (line.contains("todo")) {
                    addTodo(tasks, currCount, line);
                    currCount = completeAddTask(tasks, currCount);
                } else if (line.contains("deadline")) {
                    addDeadline(tasks, currCount, line);
                    currCount = completeAddTask(tasks, currCount);
                } else if (line.contains("event")) {
                    addEvent(tasks, currCount, line);
                    currCount = completeAddTask(tasks, currCount);
                } else if (line.equals("list")){ // print the list
                    printList(tasks, currCount);
                }
                else {
                    throw new DukeException(COMMAND_ERROR);
                }
            } catch (DukeException e) {
                printHorizontalLine();
                System.out.println(e.getMessage());
                printHorizontalLine();
>>>>>>> branch-Level-5
            }
            line = in.nextLine();
        }
        printGoodBye();
    }
}

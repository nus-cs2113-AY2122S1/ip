import allTasks.typesOfTasks.Deadline;
import allTasks.typesOfTasks.Event;
import allTasks.Task;
import allTasks.typesOfTasks.Todo;

import java.util.Scanner;

public class Duke {

    // Init constants storing various magic literals or Strings
    public static final int DASH_INDX     = 4;
    public static final int TODO_SIZE     = 5;
    public static final int EVENT_SIZE    = 6;
    public static final int DEADLINE_SIZE = 9;
    public static final int TASK_ARR_SIZE = 100;
    public static final Task[] tasks      = new Task[TASK_ARR_SIZE];
    public static final String LINE       = "--------------------------------------------------------------------------------";
    private static int itemIndex          = 0;

    public static void main(String[] args) throws DukeException {

        boolean botIsActive = false;
        Scanner in = new Scanner(System.in);

        // Prints logo and welcome message
        printWelcome();

        // Cond. is true when input "bye" is given, terminates run
        while (!botIsActive) {

            String command = in.nextLine();

            if (command.equals("bye")) {
                botIsActive = true;
                printBye();
            } else if (command.equals("list")) {
                printList(tasks);
            } else if (command.contains("done")) {
                try {
                    markAsDone(tasks, itemIndex, command);
                } catch (NumberFormatException e) {
                    System.out.println("Sir, I don't believe you provided me with an index number");
                }
            } else if (command.equals("echo")) {
                echoMode();
            } else {
                if (command.contains("todo")) {
                    try {
                        createTodo(command);
                    } catch (DukeException error) {
                        System.out.println(error.getMessage());
                    }
                } else  if (command.contains("deadline")) {
                    createDeadline(command);
                } else if (command.contains("event")) {
                    createEvent(command);
                } else {
                    System.out.println("Apologies sir but, I believe that command is beyond my understanding...");
                }
            }
        }
    }

    private static void createTodo(String command) throws DukeException {
        String taskDescription;
        try {
            taskDescription = command.substring(TODO_SIZE);
        } catch (Exception e) {
            throw new DukeException("Sir, that seems to be an inavlid command");
        }
        if (taskDescription.isBlank()) {
            throw new DukeException("Sir, you haven't given me the name of the task");
        }
        tasks[itemIndex] = new Todo(command.substring(TODO_SIZE));
        itemIndex++;
        printListSummary(itemIndex);
    }

    private static void createDeadline(String command) {
        // Reads two substrings as param: 1. The task description after keyword "deadline"
        //                                2. The actual deadline after "/by " till end of string
        int dashStart = command.indexOf("/");
        String dateOrTime = command.substring(dashStart + DASH_INDX);
        tasks[itemIndex] = new Deadline((command.substring(DEADLINE_SIZE, dashStart)), dateOrTime);
        itemIndex++;
        printListSummary(itemIndex);
    }

    private static void createEvent(String command) {
        int dashStart = command.indexOf("/");
        String dateOrTime = command.substring(dashStart + DASH_INDX);
        tasks[itemIndex] = new Event((command.substring(EVENT_SIZE, dashStart)), dateOrTime);
        itemIndex++;
        printListSummary(itemIndex);
    }

    public static void printWelcome() {
        String logo = "        _|       _|_|        _|_|_|      _|      _|    _|_|_|        _|_|_|\n"
                + "        _|     _|    _|      _|    _|    _|      _|      _|        _|\n"
                + "        _|     _|_|_|_|      _|_|_|      _|      _|      _|          _|_|\n"
                + " _|     _|     _|    _|      _|    _|      _|  _|        _|              _|\n"
                + "  _| _|    _|  _|    _|  _|  _|    _|  _|    _|    _|  _|_|_|  _|  _|_|_|\n";
        System.out.print("Initialising...............\n");
        System.out.println(LINE + System.lineSeparator() + logo);
        // Prints enclosed text in italics
        String byline = "\033[3m----------------------------------Just a rather very intelligent system---------\033[0m\n";
        System.out.println(byline + "Good Evening Sir! I'm J.A.R.V.I.S");
        System.out.println("How may I be of assistance to you today?");
        System.out.println(LINE + System.lineSeparator());
    }

    public static void printBye() {
        System.out.println(LINE + System.lineSeparator() + "Affirmative sir, I'll shut down all operations" + LINE);
    }

    public static void printList(Task[] tasks) {
        System.out.println(LINE + System.lineSeparator() + "Here are the current tasks in your list:");
        for (int count = 0; count < itemIndex; count++) {
            System.out.println(count + 1 + "." + tasks[count].printTask());
        }
        System.out.println(LINE);
    }

    public static void printListSummary(int itemIndex) {
        System.out.println(LINE + System.lineSeparator() + "Will do sir, I've added: "
                    + System.lineSeparator() + "  " + tasks[itemIndex - 1].printTask());
        if (itemIndex == 1) {
            System.out.printf("Now you have %d task in your list.\n", itemIndex);
        } else {
            System.out.printf("Now you have %d tasks in your list.\n", itemIndex);
        }
        System.out.println(LINE);
    }

    public static void markAsDone(Task[] tasks, int itemIndex, String command) {
        // When user enters string "done 2", string is split to extract the index 2 only
        int taskDoneIndex = Integer.parseInt(command.split(" ")[1]) - 1;
        // Checks if given index holds a task and throws error message if no such task exists
        if (taskDoneIndex > itemIndex - 1 || taskDoneIndex < 0) {
            System.out.println(LINE + System.lineSeparator() +
                    "Apologies sir but, it seems that task hasn't been created yet :(" + System.lineSeparator() + LINE);
        } else {
            // Selects task to be modified with command "done"
            Task taskChosen = tasks[taskDoneIndex];
            // Checks if task has already been marked as done
            if (taskChosen.isDone()) {
                System.out.println(LINE + System.lineSeparator() + "Sir, I believe this task has already been completed");
            } else {
                taskChosen.changeStatusDone(true);
                System.out.println(LINE + System.lineSeparator() + "As you wish sir, this task will be marked as done!");
            }
            // Otherwise, marks task as done with X. E.g. 1.[ ][X] read book if user inputs "done 1"
            System.out.println("    " + taskChosen.printTask() + System.lineSeparator() + LINE);
        }
    }

    public static void echoMode() {
        // Simply echos given command until user types "stop"
        System.out.println("What would you like me to repeat sir?");
        Scanner echo = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String echoLine = echo.nextLine();
            if (echoLine.equals("stop")) {
                isExit = true;
                System.out.println(LINE + System.lineSeparator()
                        + "Okay sir, stopping echo mode" + System.lineSeparator() + LINE);
            } else {
                System.out.println(LINE + System.lineSeparator() + "J.A.R.V.I.S says: "
                        + echoLine + System.lineSeparator() + LINE);
            }
        }
    }
}

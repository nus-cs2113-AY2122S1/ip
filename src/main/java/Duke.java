import java.util.Scanner;

public class Duke {

    // Constants that store index no. of "deadline" and "event"
    public static final int DASH_INDX     = 4;
    public static final int TODO_SIZE     = 5;
    public static final int EVENT_SIZE    = 6;
    public static final int DEADLINE_SIZE = 9;
    public static final Task[] tasks      = new Task[100];
    public static final String LINE       = "--------------------------------------------------------------------------------";

    public static void main(String[] args) {

        // Prints logo and welcome message
        printWelcome();

        int itemIndex = 0;
        boolean saidBye = false;

        Scanner in = new Scanner(System.in);

        // Cond. is true when input "bye" is given, terminates run
        while (!saidBye) {

            // Takes in user commands to interact with bot
            String command = in.nextLine();

            if (command.equals("bye")) {
                saidBye = true;
                printBye();
            } else if (command.equals("list")) {
                printList(tasks, itemIndex);
            } else if (command.contains("done")) {
                doneMethod(tasks, itemIndex, command);
            } else if (command.equals("echo")) {
                echoMode();
            } else {
                // Create tasks for class Todo
                if (command.contains("todo")) {
                    // Reads substring containing the task description only
                    tasks[itemIndex] = new Todo(command.substring(TODO_SIZE));
                }
                // Create tasks for class Deadline
                else {
                    final String dateOrTime = command.substring(command.indexOf("/") + DASH_INDX);
                    if (command.contains("deadline")) {
                        // Reads two substrings as param: 1. The task description after keyword "deadline"
                        //                                2. The actual deadline after "/by " till end of string
                        tasks[itemIndex] = new Deadline((command.substring(DEADLINE_SIZE, command.indexOf("/"))),
                                dateOrTime);
                    }
                    // Create tasks for class Event
                    else if (command.contains("event")) {
                        // Same substring read as Deadline
                        tasks[itemIndex] = new Event((command.substring(EVENT_SIZE, command.indexOf("/"))),
                                dateOrTime);
                    }
                    // If no keywords are used, stores new tasks into Task array
                    else {
                        tasks[itemIndex] = new Task(command);
                    }
                    itemIndex++;
                    System.out.println("Will do sir, I've added: " + System.lineSeparator() + "  "
                            + tasks[itemIndex - 1].printTask());
                    if (itemIndex == 1) {
                        System.out.printf("Now you have %d task in your list.\n", itemIndex);
                    } else {
                        System.out.printf("Now you have %d tasks in your list.\n", itemIndex);
                    }
                }
            }
        }
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
        System.out.println("Affirmative sir, I'll shut down all operations");
    }

    public static void printList(Task[] tasks, int itemIndex) {
        System.out.println(LINE + System.lineSeparator() + "Here are the current tasks in your list:");
        for (int count = 0; count < itemIndex; count++) {
            System.out.println(count + 1 + "." + tasks[count].printTask());
        }
        System.out.println(LINE);
    }

    public static void doneMethod(Task[] tasks, int itemIndex, String command) {
        // When user enters string "done 2", string is split to extract the index 2 only
        int taskDoneIndex = Integer.parseInt(command.split(" ")[1]) - 1;
        // Checks if given index holds a task and throws error message if no such task exists
        if (taskDoneIndex > itemIndex - 1 || taskDoneIndex < 0) {
            System.out.println("Apologies sir but, it seems that task hasn't been created yet :(\n" + LINE);
        } else {
            // Selects task to be modified with command "done"
            Task taskChosen = tasks[taskDoneIndex];
            // Checks if task has already been marked as done
            if (taskChosen.isDone()) {
                System.out.println("Sir I believe this task has already been completed");
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
                System.out.println("Okay sir, stopping echo mode");
            } else {
                System.out.println(echoLine);
            }
        }
    }
}
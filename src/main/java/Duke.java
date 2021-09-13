// List of imported packages
import allTasks.Task;
import allTasks.typesOfTasks.Todo;
import allTasks.typesOfTasks.Event;
import allTasks.typesOfTasks.Deadline;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // Init constants storing various magic literals or Strings
    public static final int DASH_INDX          = 4;
    public static final int TODO_SIZE          = 5;
    public static final int EVENT_SIZE         = 6;
    public static final int DEADLINE_SIZE      = 9;
    public static final int TASK_ARR_SIZE      = 100;
    public static final String LINE            = "--------------------------------------------------------------------------------";
    public static final ArrayList<Task> tasks  = new ArrayList<>();

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
                printList();
            } else if (command.contains("done")) {
                try {
                    markAsDone(command);
                } catch (NumberFormatException e) {
                    System.out.println(LINE + System.lineSeparator() + "Sir, you didn't give me a valid entry index "
                            + "\u2639" + System.lineSeparator() + LINE);
                }
            } else if (command.contains("delete")) {
                removeFromList(command);
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
                    try {
                        createDeadline(command);
                    } catch (DukeException error) {
                        System.out.println(error.getMessage());
                    }
                } else if (command.contains("event")) {
                    try {
                        createEvent(command);
                    } catch (DukeException error) {
                        System.out.println(error.getMessage());
                    }
                } else {
                    System.out.println(LINE + System.lineSeparator() + "Apologies sir but, I don't recognize that protocol! "
                            + "\uD83E\uDD28" + System.lineSeparator() + LINE);
                }
            }
        }
    }

    private static void createTodo(String command) throws DukeException {
        String taskDescription;
        try {
            taskDescription = command.substring(TODO_SIZE);
        } catch (Exception e) {
            throw new DukeException(LINE + System.lineSeparator() + "Sir, that seems to be an inavlid command "
                    + "\u2639" + System.lineSeparator() + LINE);
        }
        if (taskDescription.isBlank()) {
            throw new DukeException(LINE + System.lineSeparator() + "Sir, you haven't given me the name of the task "
                    + "\u2639" + System.lineSeparator() + LINE);
        }
        Todo todo = new Todo(command.substring(TODO_SIZE));
        tasks.add(todo);
        printListSummary();
    }

    private static void createDeadline(String command) throws DukeException{
        // Reads two substrings as param: 1. The task description after keyword "deadline"
        //                                2. The actual deadline after "/by " till end of string
        String dateOrTime;
        String taskDescription;
        int dashStart = command.indexOf("/");
        try {
            dateOrTime = command.substring(dashStart + DASH_INDX);
        } catch (Exception e) {
            throw new DukeException(LINE + System.lineSeparator() + "Sir, you haven't given me a valid deadline "
                     + "\u2639" + System.lineSeparator() + LINE);
        }
        try {
            taskDescription = command.substring(DEADLINE_SIZE, dashStart);
        } catch (Exception e){
            throw new DukeException(LINE + System.lineSeparator() + "Sir, I'm afraid that command is invalid. " + "\u2639"
                    + System.lineSeparator() + "Please frame your request in this format: deadline CS2113T Assg /by Wed 2359 hrs"
                    + System.lineSeparator() + LINE);
        }
        Deadline deadline = new Deadline(command.substring(DEADLINE_SIZE, dashStart), dateOrTime);
        tasks.add(deadline);
        printListSummary();
    }

    private static void createEvent(String command) throws DukeException {
        String dateOrTime;
        String taskDescription;
        int dashStart = command.indexOf("/");
        try {
            dateOrTime = command.substring(dashStart + DASH_INDX);
        } catch (Exception e) {
            throw new DukeException(LINE + System.lineSeparator() + "Sir, you haven't given me a valid event date "
                    + "\u2639" + System.lineSeparator() + LINE);
        }
        try {
            taskDescription = command.substring(EVENT_SIZE, dashStart);
        } catch (Exception e){
            throw new DukeException(LINE + System.lineSeparator() + "Sir, I'm afraid that command is invalid." + "\u2639"
                    + System.lineSeparator() + "Please frame your request in this format: event My B'day /at 23/07/1999"
                    + System.lineSeparator() + LINE);
        }
        Event event = new Event((command.substring(EVENT_SIZE, dashStart)), dateOrTime);
        tasks.add(event);
        printListSummary();
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
        System.out.println("How may I be of assistance to you today? " + "\uD83D\uDE01");
        System.out.println(LINE + System.lineSeparator());
    }

    public static void printBye() {
        System.out.println(LINE + System.lineSeparator() + "Affirmative sir, I'll shut down all operations"
                + System.lineSeparator() + LINE);
    }

    public static void printList() {
        System.out.println(LINE + System.lineSeparator() + "Here are the current tasks in your list:");
        for (Task task : tasks) {
            System.out.println(tasks.indexOf(task) + 1 + "." + task.printTask());
        }
        System.out.println(LINE);
    }

    public static void printListSummary() {
        System.out.println(LINE + System.lineSeparator() + "Will do sir, I've added: "
                    + System.lineSeparator() + "  " );
        if (tasks.size() == 1) {
            System.out.printf("Now you have %d task in your list.\n", tasks.size());
        } else {
            System.out.printf("Now you have %d tasks in your list.\n", tasks.size());
        }
        System.out.println(LINE);
    }

    public static void markAsDone(String command) {
        // When user enters string "done 2", string is split to extract the index 2 only
        int taskDoneIndex = Integer.parseInt(command.split(" ")[1]) - 1;
        // Checks if given index holds a task and throws error message if no such task exists
        if (taskDoneIndex >= tasks.size() || taskDoneIndex < 0) {
            System.out.println(LINE + System.lineSeparator() +
                    "Apologies sir but, it seems that task hasn't been created yet " +
                    "\u2639" + System.lineSeparator() + LINE);
        } else {
            // Selects task to be modified with command "done"
            Task taskChosen = tasks.get(taskDoneIndex);
            // Checks if task has already been marked as done
            if (taskChosen.isDone()) {
                System.out.println(LINE + System.lineSeparator() + "Sir, I believe this task has already been completed "
                    + "\uD83E\uDD14");
            } else {
                taskChosen.changeStatusDone(true);
                System.out.println(LINE + System.lineSeparator() + "As you wish sir, this task will be marked as done! "
                    + "\uD83D\uDE01");
            }
            // Otherwise, marks task as done with X. E.g. 1.[ ][X] read book if user inputs "done 1"
            System.out.println("    " + taskChosen.printTask() + System.lineSeparator() + LINE);
        }
    }

    public static void removeFromList(String command) {
        int taskRemoveIndex = Integer.parseInt(command.split(" ")[1]) - 1;
        if (taskRemoveIndex >= tasks.size() || taskRemoveIndex < 0) {
            System.out.println(LINE + System.lineSeparator() +
                    "Apologies sir but, there is no such task under that index " +
                    "\u2639" + System.lineSeparator() + LINE);
        }
        Task taskChosen = tasks.get(taskRemoveIndex);
        tasks.remove(taskChosen);
        System.out.println(LINE + System.lineSeparator() + "As you wish sir, this task will be removed at once! "
                + "\uD83D\uDE01");
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

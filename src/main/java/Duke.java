import java.util.Scanner;

public class Duke {
    public static final String DIVIDING_LINE = "________________________________________";
    public static final int MAX_STORED_TASKS = 100;
    public static final int TODO_OFFSET = 5;
    public static final int DEADLINE_OFFSET = 9;
    public static final int EVENT_OFFSET = 6;

    public static void printGreeting() {
        System.out.println(DIVIDING_LINE);
        System.out.println("Greetings, human! I'm Duke. \nWhat can I do for you?");
        System.out.println(DIVIDING_LINE);
    }

    public static void printFarewell() {
        System.out.println(DIVIDING_LINE);
        System.out.println("Closing Duke. Have a nice day!");
        System.out.println(DIVIDING_LINE);
    }

    public static void manageTasks() {
        Task[] userTasks = new Task[MAX_STORED_TASKS];
        Scanner userInput = new Scanner(System.in);
        String userInputString = userInput.nextLine();

        while (!userInputString.equalsIgnoreCase("bye")) {
            System.out.println(DIVIDING_LINE);
            try {
                if (userInputString.equalsIgnoreCase("list")) {
                    listTasks(userTasks);
                } else if (userInputString.toLowerCase().startsWith("done")) {
                    markTaskDone(userInputString, Task.totalTasks, userTasks);
                } else {
                    addNewTask(userInputString, Task.totalTasks, userTasks);
                }
            } catch (DukeException e) {
                System.out.println("DukeException: " + e.getMessage());
            }
            System.out.println(DIVIDING_LINE);
            userInputString = userInput.nextLine();
        }
    }

    public static void listTasks(Task[] userTasks) {
        for (int i = 0; i < Task.totalTasks; i++) {
            System.out.println((i + 1) + ". " + userTasks[i]);
        }
    }

    public static void markTaskDone(String userInputString, int taskIndex, Task[] userTasks) throws DukeException {
        String[] words = userInputString.split(" ");
        int completeIndex;

        try {
            completeIndex = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.TASK_INDEX_NOT_VALID);
        }

        if (completeIndex >= 0 && completeIndex < taskIndex) {
            userTasks[completeIndex].markComplete();
            System.out.println("Task " + userTasks[completeIndex].description + " marked as complete.");
        } else {
            throw new DukeException(DukeException.TASK_INDEX_OOB);
        }
    }

    public static void addNewTask(String userInputString, int taskIndex, Task[] userTasks) throws DukeException {
        if (Task.totalTasks >= MAX_STORED_TASKS) {
            throw new DukeException(DukeException.TASK_ARRAY_FULL);
        }

        int slashIndex = userInputString.indexOf('/');
        String taskSubstring;
        String timeSubstring = userInputString.substring(slashIndex + 1);
        timeSubstring = timeSubstring.replaceFirst(" ", ": ");

        if (userInputString.startsWith("todo ")) {
            taskSubstring = userInputString.substring(TODO_OFFSET);
            if (taskSubstring.isBlank()) {
                throw new DukeException(DukeException.TODO_BLANK_DESC);
            }
            userTasks[taskIndex] = new Todo(taskSubstring);
        } else if (userInputString.startsWith("deadline ")) {
            if (slashIndex < 0) {
                throw new DukeException(DukeException.DEADLINE_NO_SLASH);
            }
            if (timeSubstring.isBlank()) {
                throw new DukeException(DukeException.DEADLINE_BLANK_DATE);
            }
            if (DEADLINE_OFFSET >= slashIndex - 1) {
                throw new DukeException(DukeException.DEADLINE_BLANK_DESC);
            }
            taskSubstring = userInputString.substring(DEADLINE_OFFSET, slashIndex - 1);
            userTasks[taskIndex] = new Deadline(taskSubstring, timeSubstring);
        } else if (userInputString.startsWith("event ")) {
            if (slashIndex < 0) {
                throw new DukeException(DukeException.EVENT_NO_SLASH);
            }
            if (timeSubstring.isBlank()) {
                throw new DukeException(DukeException.EVENT_BLANK_DATE);
            }
            if (EVENT_OFFSET >= slashIndex - 1) {
                throw new DukeException(DukeException.EVENT_BLANK_DESC);
            }
            taskSubstring = userInputString.substring(EVENT_OFFSET, slashIndex - 1);
            userTasks[taskIndex] = new Event(taskSubstring, timeSubstring);
        } else {
            throw new DukeException("Command given cannot be recognized.");
        }

        System.out.println("Gotcha. I've added this task:");
        System.out.println(userTasks[taskIndex]);
        System.out.println("You have a total of " + (taskIndex + 1) + " tasks now.");
    }

    public static void main(String[] args) {
        printGreeting();
        manageTasks();
        printFarewell();
    }
}

public class TaskHandler {
    protected static final int MAX_TASK_COUNT = 100;
    protected static int taskCount = 0;
    protected static Task[] tasks = new Task[MAX_TASK_COUNT];
    protected static final String ERROR_NO_TODO_DESCRIPTION = Duke.INDENT +
            "OOPS!! The description of todo can't be empty.";
    protected static final String ERROR_NO_DEADLINE_DESCRIPTION = Duke.INDENT +
            "OOPS!! The description of deadline can't be empty.";
    protected static final String ERROR_NO_EVENT_DESCRIPTION = Duke.INDENT +
            "OOPS!! The description of event can't be empty.";
    protected static final String ERROR_NO_DONE_INDEX = Duke.INDENT +
            "OOPS!! The task's index can't be empty.";
    protected static final String ERROR_DONE_INDEX_NOT_NUMBER = Duke.INDENT +
            "OOPS!! The task's index should be a number.";
    protected static final String ERROR_DONE_INDEX_OUT_OF_BOUND = Duke.INDENT +
            "OOPS!! The task's index should be positive and " +
            Duke.LINE_SEPARATOR_AND_INDENT + "smaller than the total number of tasks";
    protected static final String ERROR_NO_EVENT_DATE = Duke.INDENT +
            "OOPS!! The date of event can't be empty." + Duke.LINE_SEPARATOR_AND_INDENT +
            "Or there should be a space in between /on and date.";
    protected static final String ERROR_NO_DEADLINE_DATE = Duke.INDENT +
            "OOPS!! The date of deadline can't be empty." + Duke.LINE_SEPARATOR_AND_INDENT +
            "Or there should be a space in between /on and date.";
    protected static final String ERROR_INVALID_COMMAND = Duke.INDENT +
            "OOPS!! I don't understand what that means :-(";


    public static void markTaskAsDone(String[] words) throws DukeException, NumberFormatException{
        if (words.length == 1) {
            throw new DukeException(ERROR_NO_DONE_INDEX);
        }
        try {
            Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_DONE_INDEX_NOT_NUMBER);
        }
        int taskIndex = Integer.parseInt(words[1]);
        if (taskIndex > taskCount || taskIndex < 1) {
            throw new DukeException(ERROR_DONE_INDEX_OUT_OF_BOUND);

        }
        tasks[taskIndex - 1].markAsDone();
    }

    public static void printTaskList() {
        System.out.println(Duke.INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(Duke.INDENT + (i + 1) + "." + tasks[i]);
        }
    }

    public static void printAddedTask() {
        System.out.println(Duke.INDENT + "Got it. I've added this task:" + Duke.LINE_SEPARATOR_AND_INDENT +
                tasks[taskCount] + Duke.LINE_SEPARATOR_AND_INDENT + "Now you have " + (taskCount + 1) + " tasks in the list.");
    }

    public static void addDeadline(String line) throws DukeException {
        String[] words = line.split("deadline");
        if (words.length == 0) {
            throw new DukeException(ERROR_NO_DEADLINE_DESCRIPTION);
        }
        words = words[1].split("/by ");
        if (words.length == 1) {
            throw new DukeException(ERROR_NO_DEADLINE_DATE);
        }
        tasks[taskCount] = new Deadline(words[0].trim(), words[1].trim());
        printAddedTask();
        taskCount++;
    }

    public static void addEvent(String line) throws DukeException {
        String[] words = line.split("event");
        if (words.length == 0) {
            throw new DukeException(ERROR_NO_EVENT_DESCRIPTION);
        }
        words = words[1].split("/on ");
        if (words.length == 1) {
            throw new DukeException(ERROR_NO_EVENT_DATE);
        }
        tasks[taskCount] = new Event(words[0].trim(), words[1].trim());
        printAddedTask();
        taskCount++;
    }

    public static void addTodo(String line) throws DukeException {
        String[] words = line.split("todo");
        if (words.length == 0) {
            throw new DukeException(ERROR_NO_TODO_DESCRIPTION);
        }
        tasks[taskCount] = new Todo(words[1].trim());
        printAddedTask();
        taskCount++;
    }

    public static void showHelp() {
        System.out.println(Duke.INDENT + Duke.HELP_INSTRUCTIONS);
    }
<<<<<<< Updated upstream
=======

    public static void handleWrongCommand() throws DukeException{
        throw new DukeException(ERROR_INVALID_COMMAND);
    }
>>>>>>> Stashed changes
}

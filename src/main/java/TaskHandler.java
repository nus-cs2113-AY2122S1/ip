import java.util.ArrayList;

public class TaskHandler {
    
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static final String ERROR_NO_TODO_DESCRIPTION = Duke.INDENT +
            "OOPS!! The description of todo can't be empty.";
    protected static final String ERROR_NO_DEADLINE_DESCRIPTION = Duke.INDENT +
            "OOPS!! The description of deadline can't be empty.";
    protected static final String ERROR_NO_EVENT_DESCRIPTION = Duke.INDENT +
            "OOPS!! The description of event can't be empty.";
    protected static final String ERROR_NO_INDEX = Duke.INDENT +
            "OOPS!! The task's index can't be empty.";
    protected static final String ERROR_INDEX_NOT_NUMBER = Duke.INDENT +
            "OOPS!! The task's index should be a number.";
    protected static final String ERROR_INDEX_OUT_OF_BOUND = Duke.INDENT +
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
            throw new DukeException(ERROR_NO_INDEX);
        }
        try {
            Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_INDEX_NOT_NUMBER);
        }
        int taskIndex = Integer.parseInt(words[1]);
        if (taskIndex > tasks.size() || taskIndex < 1) {
            throw new DukeException(ERROR_INDEX_OUT_OF_BOUND);

        }
        tasks.get(taskIndex - 1).markAsDone();
    }

    public static void printTaskList() {
        System.out.println(Duke.INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Duke.INDENT + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void printAddedTask() {
        System.out.println(Duke.INDENT + "Got it. I've added this task:" + Duke.LINE_SEPARATOR_AND_INDENT +
                tasks.get(tasks.size() - 1) + Duke.LINE_SEPARATOR_AND_INDENT + "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void printRemovedTask(int index) {
        System.out.println(Duke.INDENT + "Got it. I've removed this task:" + Duke.LINE_SEPARATOR_AND_INDENT +
                tasks.get(index) + Duke.LINE_SEPARATOR_AND_INDENT + "Now you have " + (tasks.size() - 1) + " tasks in the list.");
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
        tasks.add(new Deadline(words[0].trim(), words[1].trim()));
        printAddedTask();
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
        tasks.add(new Event(words[0].trim(), words[1].trim()));
        printAddedTask();
    }

    public static void addTodo(String line) throws DukeException {
        String[] words = line.split("todo");
        if (words.length == 0) {
            throw new DukeException(ERROR_NO_TODO_DESCRIPTION);
        }
        tasks.add(new Todo(words[1].trim()));
        printAddedTask();
    }

    public static void deleteTask(String[] words) throws DukeException, NumberFormatException{
        if (words.length == 1) {
            throw new DukeException(ERROR_NO_INDEX);
        }
        try {
            Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_INDEX_NOT_NUMBER);
        }
        int taskIndex = Integer.parseInt(words[1]);
        if (taskIndex > tasks.size() || taskIndex < 1) {
            throw new DukeException(ERROR_INDEX_OUT_OF_BOUND);

        }
        printRemovedTask(taskIndex - 1);
        tasks.remove(taskIndex - 1);
    }

    public static void addTodoFromFile(String[] words) {
        tasks.add(new Todo(words[2].trim()));
        if (words[1].trim().equals("1")) {
            tasks.get(tasks.size() - 1).markAsDoneWithoutMessage();
        }
    }

    public static void addEventFromFile(String[] words) {
        tasks.add(new Event(words[2].trim(), words[3].trim()));
        if (words[1].trim().equals("1")) {
            tasks.get(tasks.size() - 1).markAsDoneWithoutMessage();
        }
    }

    public static void addDeadlineFromFile(String[] words) {
        tasks.add(new Deadline(words[2].trim(), words[3].trim()));
        if (words[1].trim().equals("1")) {
            tasks.get(tasks.size() - 1).markAsDoneWithoutMessage();
        }
    }

    public static void showHelp() {
        System.out.println(Duke.INDENT + Duke.HELP_INSTRUCTIONS);
    }

    public static void handleWrongCommand() throws DukeException{
        throw new DukeException(ERROR_INVALID_COMMAND);
    }
}

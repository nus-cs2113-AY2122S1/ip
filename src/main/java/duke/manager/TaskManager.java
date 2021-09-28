package duke.manager;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskManager {

    public static final String TASK_TODO = "todo";
    public static final String TASK_EVENT = "event";
    public static final String TASK_DEADLINE = "deadline";
    public static final String TASK_ERROR = "I really cannot understand what you wrote";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_LIST = "list";
    public static final String DELIMITER_SPACE = " ";
    public static final String DELIMITER_SLASH = "/";
    public static final String DELIMITER_EVENT = "/at";
    public static final String DELIMITER_DEADLINE = "/by";
    public static final int DESCRIPTION_INDEX_EVENT = 6;
    public static final int INDEX_TASKTYPE = 0;
    public static final int INDEX_DESCRIPTION_DEADLINE = 9;


    /**
     * Determines the task type,
     * assuming it is the first
     * word in the message and
     * returns it
     *
     * @param message The input by the user.
     * @return The first word of the string.
     */
    private static String taskType(String message) {
        String[] type = message.split(DELIMITER_SPACE);
        return type[INDEX_TASKTYPE];
    }


    /**
     * The function adds the task input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addTask(ArrayList<Task> tasks, String message) {
        switch (taskType(message)) {
        case TASK_TODO:
            addTodo(tasks, message);
            break;
        case TASK_DEADLINE:
            addDeadline(tasks, message);
            break;
        case TASK_EVENT:
            addEvent(tasks, message);
            break;
        default:
            DukeException.taskError();
            break;
        }
    }

    /**
     * The function adds the todo task input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addEvent(ArrayList<Task> tasks, String message) {
        try {
            tasks.add(createEvent(message));
            Ui.printAddedTask(getLatestTask(tasks), tasks);
            Storage.saveTasksToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            DukeException.stringIndexEventError();
        } catch (ArrayIndexOutOfBoundsException e) {
            DukeException.arrayIndexEventError();
        } catch (DateTimeParseException e) {
            DukeException.dateTimeParseError();
        }
    }

    private static Event createEvent(String message) {
        String eventString = getEventString(message);
        String[] eventData = splitString(eventString, DELIMITER_EVENT);
        String eventDescription = eventData[INDEX_TASKTYPE].strip();
        LocalDateTime eventDateTime = DateAndTimeParser.processDateAndTime(eventData[1]);
        String eventDateTimeString = eventData[1].strip();
        return new Event(eventDescription, eventDateTime, eventDateTimeString);
    }

    private static String[] splitString(String eventString, String DELIMITER) {
        return eventString.split(DELIMITER, 2);
    }

    private static String getEventString(String message) {
        return message.substring(DESCRIPTION_INDEX_EVENT);
    }

    /**
     * The function adds the deadline task input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addDeadline(ArrayList<Task> tasks, String message) {
        try {
            tasks.add(createDeadline(message));
            Ui.printAddedTask(getLatestTask(tasks), tasks);
            Storage.saveTasksToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            DukeException.stringIndexDeadlineError();
        } catch (ArrayIndexOutOfBoundsException d) {
            DukeException.arrayIndexDeadlineError();
        } catch (DateTimeParseException e) {
            DukeException.dateTimeParseError();
        }
    }

    private static Task getLatestTask(ArrayList<Task> tasks) {
        return tasks.get(taskSize(tasks));
    }

    private static Deadline createDeadline(String message) {
        String deadlineString = getDeadlineString(message);
        String[] deadlineData = splitString(deadlineString, DELIMITER_DEADLINE);
        String deadlineDescription = deadlineData[0].strip();
        LocalDateTime deadlineDateTime = DateAndTimeParser.processDateAndTime(deadlineData[1]);
        String deadlineDateTimeString = deadlineData[1].strip();
        return new Deadline(deadlineDescription, deadlineDateTime, deadlineDateTimeString);
    }

    private static String getDeadlineString(String message) {
        return message.substring(INDEX_DESCRIPTION_DEADLINE);
    }

    private static int taskSize(ArrayList<Task> tasks) {
        return tasks.size() - 1;
    }

    /**
     * The function adds the event input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addTodo(ArrayList<Task> tasks, String message) {
        try {
            tasks.add(new Todo(message.substring(5)));
            Ui.printAddedTask(getLatestTask(tasks), tasks);
            Storage.saveTasksToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            DukeException.stringIndexTodoError();        }
    }

    /**
     * Marks the task done based
     * on the integer value provided
     * by the user in the String message.
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void markDone(ArrayList<Task> tasks, String message) {
        try {
            String[] arrOfStr = message.split(DELIMITER_SPACE);
            int index = getIndex(arrOfStr);
            tasks.get(index).isDone();
            Storage.saveTasksToFile(tasks);
        } catch (NullPointerException e) {
            DukeException.nullPointerDoneError();
        }
    }

    private static int getIndex(String[] arrOfStr) {
        return Integer.parseInt(arrOfStr[arrOfStr.length - 1]) - 1;
    }

    /**
     * The function processes the inputs
     * by the user and calls the necessary
     * functions to add, print and manipulate
     * tasks.
     */
    public static void processInput() {

        ArrayList<Task> tasks = new ArrayList<>();
        Storage.getStoredData(tasks);
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        while (isNotCommandBye(message)) {
            processMessage(tasks, message);
            message = scanner.nextLine();
        }
        scanner.close();
    }

    private static void processMessage(ArrayList<Task> tasks, String message) {
        if (isCommandList(message)) {
            Ui.printTasks(tasks);
        } else if (isCommandFind(message)) {
            findTask(tasks, message);
        } else if (isCommandDone(message)) {
            markDone(tasks, message);
        } else if (isCommandDelete(message)) {
            deleteTask(tasks, message);
        } else {
            addTask(tasks, message);
        }
    }

    private static boolean isCommandDelete(String message) {
        return message.contains(COMMAND_DELETE);
    }

    private static boolean isCommandDone(String message) {
        return message.contains(COMMAND_DONE);
    }

    private static boolean isCommandFind(String message) {
        return message.contains(COMMAND_FIND);
    }

    private static boolean isCommandList(String message) {
        return message.equals(COMMAND_LIST);
    }

    private static boolean isNotCommandBye(String message) {
        return !message.equals(COMMAND_BYE);
    }

    /**
     * Finds the matching tasks according to string
     * input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string
     */
    private static void findTask(ArrayList<Task> tasks, String message) {
        try {
            String filter = message.substring(5);
            ArrayList<Task> filteredTasks = (ArrayList<Task>) tasks.stream()
                    .filter((t) -> t.getDescription().contains(filter)).collect(Collectors.toList());
            Ui.printMatchingTasks(filteredTasks);
        } catch (NullPointerException e) {
            DukeException.nullPointerFindError();
        } catch (StringIndexOutOfBoundsException e) {
            DukeException.stringIndexFindError();
        }
    }

    /**
     * Deletes the task of the provided index
     *
     * @param tasks   the array of tasks
     * @param message the input string
     */
    private static void deleteTask(ArrayList<Task> tasks, String message) {
        try {
            String[] arrOfStr = message.strip().split(DELIMITER_SPACE);
            int index = Integer.parseInt(arrOfStr[1]);
            Ui.printDeletedTask(tasks.get(index - 1), tasks);
            tasks.remove(index - 1);
            Storage.saveTasksToFile(tasks);
        } catch (NullPointerException e) {
            DukeException.nullPointerDeleteError();
        } catch (IndexOutOfBoundsException e) {
            DukeException.indexBoundDeleteError();
        }
    }

}

package duke;

/* Importing local files from other packages */

import exception.DukeException;
import exception.EmptyTaskDescriptionException;
import exception.NoTaskFoundException;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {

    public static final String LINK_TO_UG = "-> For details please refer to the User Guide of Duke at the link given below:\n-> https://aditichadha1310.github.io/ip/";

    /**
     * Storing some delimiters as Strings.
     */
    public static final String DELIMITER_SPACE = " ";
    public static final String DELIMITER_FORWARD_SLASH = "/";
    public static final String DELIMITER_BY = "/by";
    public static final String DELIMITER_AT = "/at";
    public static final String DELIMITER_DOT = ".";

    /**
     * Storing some flags.
     */
    public static final int INVALID = -1;
    public static final int DELIMITING_LIMIT_TWO = 2;

    /**
     * Storing some display messages as Strings.
     */
    public static final String MESSAGE_TASK_REMOVED = "Noted. I've removed this task:";
    public static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:";
    public static final String MESSAGE_LIST_ALL_TASKS = "Here are the tasks in your list:";
    public static final String MESSAGE_TASK_MARKED_DONE = "Nice! I have marked this task as done:";


    /**
     * Storing some error messages as Strings.
     */
    public static final String ERROR_INVALID_TASK_STATEMENT = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_INVALID_TASK_NUMBER = "Sorry, no task is assigned at this number, you might want to re-check?";
    public static final String ERROR_EMPTY_TASKLIST = "Sorry, no tasks have been added to the list as yet!\n" +
            "You can add tasks to this list simply by typing and pressing \"Enter\"!!";
    public static final String ERROR_DEADLINE_WITHOUT_BY = "☹ OOPS!!! Its an invalid deadline creation statement." +
            "\n Include \"/by\" to state the deadline of the task followed by valid date and time in the correct format.";
    public static final String ERROR_EVENT_WITHOUT_AT = "☹ OOPS!!! Its an invalid event creation statement." +
            "\n Include \"/at\" to state the event time followed by valid date and time in the correct format.";
    public static final String ERROR_EMPTY_TODO = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String ERROR_EMPTY_DEADLINE = "☹ OOPS!!! The description or the deadline of the task cannot be empty.";
    public static final String ERROR_INCOMPLETE_DEADLINE = "☹ OOPS!!! The description of the task seems incomplete.";
    public static final String ERROR_EMPTY_EVENT = "☹ OOPS!!! The description and time schedule of the event cannot be empty.";
    public static final String ERROR_INCOMPLETE_EVENT = "☹ OOPS!!! The description or time schedule of the event seems incomplete.";
    public static final String ERROR_INCORRECT_DELETE_COMMAND = "☹ OOPS!!! Its an invalid task deletion command.";


    public static final String TASK_TODO = "todo";
    public static final String TASK_DEADLINE = "deadline";
    public static final String TASK_EVENT = "event";


    public static final String[] COMMANDS = new String[]{
            "help", "todo", "deadline", "event", "list", "done", "delete", "find", "bye"
    };

    public static int indexOfForwardSlash;
    public static boolean isTaskValid = true;
    public static String taskDescription;
    public static String timeDueAt;
    public static String timeDueBy;

    /**
     * Task type Array list to store the tasks the user will create
     */
    public static ArrayList<Task> scheduledTasks;

    /**
     * Task type Array list to store the filtered list of tasks when user searches for them using a keyword.
     */
    public static ArrayList<Task> filteredList;

    public TaskList() {
        scheduledTasks = new ArrayList<Task>();
    }


    /**
     * Displays the list of the commands accepted by Duke.
     * Displays the link of the user guide to provide details.
     **/
    public static void help() {
        Ui.printLine();
        System.out.println("The following is a list of commands that Duke accepts :");
        for (String i : COMMANDS) {
            System.out.println("-> " + i);
        }
        System.out.println(LINK_TO_UG);
    }

    /**
     * Updates the status of the task by marking it as done in the task list.
     *
     * @param taskNumberCompleted TaskNumberCompleted stores the index of the task which has been completed by the user.
     */
    public static void markTaskAsDone(String userInput) throws NoTaskFoundException {
        Ui.printLine();
        int taskNumberCompleted = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));

        if ((taskNumberCompleted <= scheduledTasks.size()) && (taskNumberCompleted > 0)) {
            scheduledTasks.get(taskNumberCompleted - 1).markAsDone();
            System.out.println(MESSAGE_TASK_MARKED_DONE);
            System.out.println(scheduledTasks.get(taskNumberCompleted - 1));
            Storage.callSaveTaskToList(scheduledTasks);
        } else {
            throw new NoTaskFoundException(ERROR_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Calls the findTasks() function with the search keyword and displays the list of the tasks that contain the search keyword.
     *
     * @param userInput    userInput stores the command given by the user.
     * @param filterString FilterString contains the string by which the task list is queried
     * @param filteredList FilteredList stores the tasks which contain the search keyword(filterString) in their task description.
     */
    public static void callFindTasks(String userInput) {
        int indexOfSpace = userInput.indexOf(DELIMITER_SPACE);
        String filterString = userInput.substring(indexOfSpace + 1);
        ArrayList<Task> filteredList = findTasks(filterString);
        filteredList.stream()
                .forEach(System.out::println);

    }

    /**
     * Queries the scheduledTasks task list by the search keyword.
     * Returns a list of tasks that contain the filterString in their task description.
     */
    public static ArrayList<Task> findTasks(String filterString) {
        int counter = 0;
        filteredList = (ArrayList<Task>) scheduledTasks.stream()
                .filter((t) -> t.description.contains(filterString))
                .collect(Collectors.toList());

        counter = filteredList.size();
        if (counter <= 0) {
            System.out.println("☹ OOPS!!! I'm sorry, but no tasks match your search.");
        }
        return filteredList;
    }


    /**
     * Adds the task to the task list if it is a valid task creation statement given by the user.
     * Displays appropriate message if task is valid and is created successfully, vice versa.
     * Displays the total number of tasks in the list as well.
     * Stores the task in the file Duke.txt as well.
     *
     * @param isTaskValid IsTaskValid stores true if the task statement entered by the user is a valid task creation statement and false, otherwise.
     * @throws DukeException                 if the description of the task or timing is incomplete or invalid.
     * @throws EmptyTaskDescriptionException if the descritiona and timing is empty.
     */
    public static void addTaskToList(String userInput) throws DukeException, EmptyTaskDescriptionException {

        isTaskValid = true;
        String firstWord;
        String[] split = userInput.split(DELIMITER_SPACE, DELIMITING_LIMIT_TWO);
        firstWord = split[0].toLowerCase();

        switch (firstWord) {
        case TASK_TODO:
            addTodo(split);
            break;

        case TASK_DEADLINE:
            addDeadline(split, userInput);
            break;

        case TASK_EVENT:
            addEvent(split, userInput);
            break;

        default:
            isTaskValid = false;
            throw new DukeException(ERROR_INVALID_TASK_STATEMENT);
        }

        if (isTaskValid) {
            Ui.printLine();
            System.out.println(MESSAGE_TASK_ADDED);
            System.out.println(DELIMITER_SPACE + scheduledTasks.get(scheduledTasks.size() - 1));
            System.out.println("Now you have " + scheduledTasks.size() + " tasks in the list.");
        }
    }

    /**
     * Adds a TODO task to the task list.
     *
     * @throws EmptyTaskDescriptionException if the task description for todo is empty.
     */
    public static void addTodo(String split[]) throws EmptyTaskDescriptionException {
        if (split.length < 2 || split[1].isEmpty()) {
            throw new EmptyTaskDescriptionException(ERROR_EMPTY_TODO);
        } else {
            scheduledTasks.add(new Todo(split[1]));
        }
        Storage.callSaveTaskToList(scheduledTasks);
    }

    /**
     * Adds a deadline type task to the task list.
     *
     * @throws DukeException                 if the task description or deadline is in invalid format or if it is incomplete.
     * @throws EmptyTaskDescriptionException if the task description or deadline are empty.
     */
    public static void addDeadline(String split[], String userInput) throws DukeException, EmptyTaskDescriptionException {
        indexOfForwardSlash = userInput.indexOf(DELIMITER_FORWARD_SLASH);
        int indexOfBy = userInput.indexOf(DELIMITER_BY);
        if (indexOfBy == INVALID) {
            throw new DukeException(ERROR_DEADLINE_WITHOUT_BY);
        }
        if (split.length < 2 || split[1].isEmpty() || indexOfForwardSlash == INVALID) {
            throw new EmptyTaskDescriptionException(ERROR_EMPTY_DEADLINE);
        }
        taskDescription = split[1].split(DELIMITER_BY, DELIMITING_LIMIT_TWO)[0];
        String timeDueByFormatted = "";
        timeDueBy = "";
        timeDueBy = split[1].split(DELIMITER_BY, DELIMITING_LIMIT_TWO)[1];
        if (taskDescription.isEmpty() || timeDueBy.isEmpty()) {
            throw new DukeException(ERROR_INCOMPLETE_DEADLINE);
        }
        timeDueBy = (timeDueBy.trim()).replace(DELIMITER_SPACE, "T");
        timeDueByFormatted = DateAndTimeParser.callProcessDateTime(timeDueBy);
        if (!timeDueByFormatted.isEmpty()) {
            scheduledTasks.add(new Deadline(taskDescription, timeDueByFormatted));
            Storage.callSaveTaskToList(scheduledTasks);
        } else {
            isTaskValid = false;
        }
    }

    /**
     * Adds an event type task to the task list.
     *
     * @throws DukeException                 if the task description or event time is in invalid format or if it is incomplete.
     * @throws EmptyTaskDescriptionException if the task description or event timing are empty.
     */
    public static void addEvent(String split[], String userInput) throws DukeException, EmptyTaskDescriptionException {
        indexOfForwardSlash = userInput.indexOf(DELIMITER_FORWARD_SLASH);
        int indexOfAt = userInput.indexOf(DELIMITER_AT);
        if (indexOfAt == INVALID) {
            throw new DukeException(ERROR_EVENT_WITHOUT_AT);
        }
        if (split.length < 2 || split[1].isEmpty() || indexOfForwardSlash == INVALID) {
            throw new EmptyTaskDescriptionException(ERROR_EMPTY_EVENT);
        }

        taskDescription = split[1].split(DELIMITER_AT, DELIMITING_LIMIT_TWO)[0];
        String timeDueAtFormatted = "";
        timeDueAt = "";
        timeDueAt = split[1].split(DELIMITER_AT, DELIMITING_LIMIT_TWO)[1];
        if (taskDescription.isEmpty() || timeDueAt.isEmpty()) {
            throw new DukeException(ERROR_INCOMPLETE_EVENT);
        }

        timeDueAt = (timeDueAt.trim()).replace(DELIMITER_SPACE, "T");
        timeDueAtFormatted = DateAndTimeParser.callProcessDateTime(timeDueAt);
        if (!timeDueAtFormatted.isEmpty()) {
            scheduledTasks.add(new Event(taskDescription, timeDueAtFormatted));
            Storage.callSaveTaskToList(scheduledTasks);
        } else {
            isTaskValid = false;
        }
    }

    /**
     * Deletes the task from the specified index in the task list.
     * Updates the task list in the Duke.txt file.
     *
     * @param deleteTask DeleteTask stores the task number which is supposed to be deleted.
     * @throws DukeException        if the task deletion command is invalid
     * @throws NoTaskFoundException if there is no task at the specified task number.
     */
    public static void deleteTask(String userInput) throws IOException, NoTaskFoundException, DukeException {
        Ui.printLine();
        int deleteTask;
        int indexOfSpace = userInput.indexOf(DELIMITER_SPACE);
        if (indexOfSpace != INVALID) {
            deleteTask = Integer.parseInt(userInput.substring(indexOfSpace + 1));
        } else {
            throw new DukeException(ERROR_INCORRECT_DELETE_COMMAND);
        }

        if ((deleteTask <= scheduledTasks.size()) && (deleteTask > 0)) {
            Task taskToBeDeleted = scheduledTasks.get(deleteTask - 1);
            System.out.println(MESSAGE_TASK_REMOVED);
            System.out.println(taskToBeDeleted
            );
            scheduledTasks.remove(deleteTask - 1);
            System.out.println("Now you have " + scheduledTasks.size() + " tasks in the list.");
            Storage.callSaveTaskToList(scheduledTasks);
        } else {
            throw new NoTaskFoundException(ERROR_INVALID_TASK_NUMBER);
        }

    }

    /**
     * Lists all the tasks in the task list along with their task completion status.
     * The tasks are enlisted as such that they reveal if they are a "todo", "event" or a "deadline".
     *
     * @throws NoTaskFoundException if there are no tasks in the list.
     */
    public static void list() throws NoTaskFoundException {
        int i;
        Ui.printLine();
        if (scheduledTasks.size() == 0) {
            throw new NoTaskFoundException(ERROR_EMPTY_TASKLIST);
        } else {
            System.out.println(MESSAGE_LIST_ALL_TASKS);
            i = 0;
            for (Task task : scheduledTasks) {
                System.out.print((i + 1) + DELIMITER_DOT);
                System.out.println(task);
                i++;
            }
        }
    }
}


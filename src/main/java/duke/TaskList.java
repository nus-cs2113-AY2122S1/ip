package duke;

import exception.DukeException;
import exception.EmptyTaskDescriptionException;
import exception.NoTaskFoundException;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    public static final String DELIMITER_SPACE = " ";
    public static final String DELIMITER_FORWARD_SLASH = "/";
    public static final String DELIMITER_BY = "/by";
    public static final String DELIMITER_AT = "/at";
    public static final String DELIMITER_DOT = ".";

    public static final String MESSAGE_TASK_REMOVED = "Noted. I've removed this task:";
    public static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:";
    public static final String MESSAGE_LIST_ALL_TASKS = "Here are the tasks in your list:";
    public static final String MESSAGE_TASK_MARKED_DONE = "Nice! I have marked this task as done:";

    public static final String ERROR_INVALID_TASK_STATEMENT = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_INVALID_TASK_NUMBER = "Sorry, no task is assigned at this number, you might want to re-check?";
    public static final String ERROR_EMPTY_TASKLIST = "Sorry, no tasks have been added to the list as yet!\n" +
            "You can add tasks to this list simply by typing and pressing \"Enter\"!!";

    public static final String TASK_TODO = "todo";
    public static final String TASK_DEADLINE = "deadline";
    public static final String TASK_EVENT = "event";


    public static ArrayList<Task> scheduledTasks;

    public TaskList() {
        scheduledTasks = new ArrayList<Task>();
    }


    /**
     * Updates the status of the task by marking it as done in the task list.
     *
     * @param taskNumberCompleted TaskNumberCompleted stores the task number which has been completed by the user.
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
     * Adds the task to the task list if it is a valid task creation statement given by the user.
     * Displays appropriate message if task is valid and is created successfully, vice versa.
     * Displays the total number of tasks in the list as well.
     *
     * @param isTaskValid IsTaskValid stores true if the task statement entered by the user is a valid task creation statement and false, otherwise.
     * @param index       Index stores the index of the "/" in the entered String
     */
    public static void addTaskToList(String userInput) throws DukeException, EmptyTaskDescriptionException {

        int index;
        boolean isTaskValid = true;
        String firstWord;
        String[] split = userInput.split(DELIMITER_SPACE, 2);
        String taskDescription;
        String timeDueAt;
        String timeDueBy;
        firstWord = split[0].toLowerCase();

        switch (firstWord) {
        case TASK_TODO:
            if (split.length < 2 || split[1].isEmpty()) {
                throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                scheduledTasks.add(new Todo(split[1]));
            }
            Storage.callSaveTaskToList(scheduledTasks);
            break;

        case TASK_DEADLINE:
            index = userInput.indexOf(DELIMITER_FORWARD_SLASH);
            if (split.length < 2 || split[1].isEmpty() == true || index == -1) {
                throw new EmptyTaskDescriptionException("☹ OOPS!!! The description or the deadline of the task cannot be empty.");
            }
            int indexOfSpace = userInput.indexOf(DELIMITER_SPACE) + 1;
            taskDescription = split[1].split(DELIMITER_BY, 2)[0];
            timeDueBy = split[1].split(DELIMITER_BY, 2)[1];
            if (taskDescription.isEmpty() || timeDueBy.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of the task seems incomplete.");
            }
            scheduledTasks.add(new Deadline(userInput.substring(indexOfSpace, index), userInput.substring(index + 3)));
            Storage.callSaveTaskToList(scheduledTasks);
            break;

        case TASK_EVENT:
            index = userInput.indexOf(DELIMITER_FORWARD_SLASH);
            if (split.length < 2 || split[1].isEmpty() || index == -1) {
                throw new EmptyTaskDescriptionException("☹ OOPS!!! The description and time schedule of the event cannot be empty.");
            }
            indexOfSpace = userInput.indexOf(DELIMITER_SPACE) + 1;
            taskDescription = split[1].split(DELIMITER_AT, 2)[0];
            timeDueAt = split[1].split(DELIMITER_AT, 2)[1];
            if (taskDescription.isEmpty() || timeDueAt.isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description or time schedule of the event seems incomplete.");
            }
            scheduledTasks.add(new Event(userInput.substring(indexOfSpace, index), userInput.substring(index + 3)));
            Storage.callSaveTaskToList(scheduledTasks);
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
     * Deletes the task from the task list.
     *
     * @param deleteTask DeleteTask stores the task number which is supposed to be deleted.
     */
    public static void deleteTask(String userInput) throws IOException, NoTaskFoundException {
        Ui.printLine();
        int deleteTask = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));

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
     * The tasks are enlisted which reveal if they are a "todo", "event" or a "deadline".
     *
     * @param taskCompletionStatus TaskCompletionStatus stores true if the task is completed, false otherwise.
     */
    public static void list() throws NoTaskFoundException {
        int i;
        String taskCompletionStatus;
        Ui.printLine();
        if (scheduledTasks.size() == 0) {
            throw new NoTaskFoundException(ERROR_EMPTY_TASKLIST);
        } else {
            System.out.println(MESSAGE_LIST_ALL_TASKS);
            i = 0;
            for (Task task : scheduledTasks) {
                taskCompletionStatus = task.getStatus();
                System.out.print((i + 1) + DELIMITER_DOT);
                System.out.println(task);
                i++;
            }
        }
    }
}

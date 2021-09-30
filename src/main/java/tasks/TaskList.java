package tasks;
import FridayExceptions.EmptyListException;
import FridayExceptions.MissingKeyWordException;
import FridayExceptions.MissingDateException;
import FridayExceptions.EmptyTaskNameException;
import FridayExceptions.InvalidIndexException;
import FridayExceptions.InvalidTaskIndexException;
import FridayExceptions.IncompleteCommandException;
import FridayExceptions.MissingQueryException;
import FridayExceptions.MissingIndexException;
import enums.Errors;
import parser.InputParser;
import ui.MessagePrinter;
import storage.UpdateData;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Main class handling functions called when user enters a command
 * ArrayList containing all tasks of user initiated here
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    // Operations to add/delete tasks in list eg. addToDo, addDeadline
    /**
     * Function called when user enters list command
     * If no tasks, throws EmptyListException which will print out a message stating that there are no tasks
     * in the users list
     * @throws EmptyListException
     */
    // function to update Task array on adding and deleting task
    public static void getList() throws EmptyListException{
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }
        // Message Printer
        MessagePrinter.printList(tasks);
    }

    /**
     * Adds a new Task to tasks ArrayList
     * Prints out message indicating task successfully added
     * @param newTask Task object of new Task to add to list
     * @param taskName name of task to print out
     * @param isLoad flag indicating whether function was called on loading of data or otherwise
     */
    private static void addTask(Task newTask, String taskName, boolean isLoad) {
        // add to array list
        tasks.add(newTask);
        // Message Printer
        if (isLoad) {
            return;
        }
        MessagePrinter.addedTask(taskName);
    }

    /**
     * Removes a specified task from tasks ArrayList
     * Prints our message showing user current list upon removal
     * @param taskToRemove
     * @param taskName
     */
    private static void removeTask(Task taskToRemove, String taskName) {
        tasks.remove(taskToRemove);
        MessagePrinter.removedTask(taskName, tasks.size());
    }

    /**
     * Function handling adding of tasks indicated as todo
     * @param userInput String containing users input; parsed to get task name
     * @param isDone indicator for whether task is done or not. If called when user adds via command line, default
     *               value is false. If called when loading data from storage, will be set to value indicated in storage
     * @param isLoad indicator for whether function was called from data storage or from user input
     * @return Task object referring to current todo added
     * @throws EmptyTaskNameException If user did not provide a name for task
     */
    public static Task addToDo(String userInput, boolean isDone, boolean isLoad) throws EmptyTaskNameException {
        String[] splitString = userInput.split("\\s");
        if (splitString.length <= 1) {
            throw new EmptyTaskNameException();
        }
        // get taskName
        String taskName = userInput.substring(userInput.indexOf(" ")).trim();
        Todo newTodo = new Todo(isDone, taskName);
        addTask(newTodo, taskName, isLoad);
        return newTodo;
    }

    /**
     * Function handling adding of tasks indicated as deadline
     * @param userInput String containing users input; parsed to get task name and deadline date
     * @param isDone indicator for whether task is done or not. If called when user adds via command line, default
     *               value is false. If called when loading data from storage, will be set to value indicated in storage
     * @param isLoad indicator for whether function was called from data storage or from user input
     * @return Task object referring to current deadline added
     * @throws IncompleteCommandException If only deadline command without any specifications provided
     * @throws EmptyTaskNameException if task name is missing
     * @throws MissingKeyWordException if /by is missing
     * @throws MissingDateException if date of deadline not provided
     * @throws DateTimeParseException if date and time not in correct format
     */
    public static Task addDeadline(String userInput, boolean isDone, boolean isLoad) throws
            IncompleteCommandException,
            EmptyTaskNameException,
            MissingKeyWordException,
            MissingDateException,
            DateTimeParseException {
        // check validity of deadline string
        Errors checkDeadline = InputParser.checkDeadlineCommand(userInput);
        switch (checkDeadline) {
        case INCOMPLETE_COMMAND:
            throw new IncompleteCommandException();
        case MISSING_BY:
            throw new MissingKeyWordException("by");
        case MISSING_TASK:
            throw new EmptyTaskNameException();
        case MISSING_DEADLINE:
            throw new MissingDateException("deadline");
        }
        // get taskName and date
        String taskName = InputParser.getTaskName(userInput);
        String deadline = InputParser.getDate(userInput);
        try {
            LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            MessagePrinter.invalidDate();
            return null;
        }
        Deadline newDeadline = new Deadline(isDone, taskName, deadline);
        addTask(newDeadline, taskName, isLoad);
        return newDeadline;
    }

    /**
     * Function handling adding of tasks indicated as event
     * @param userInput String containing users input; parsed to get task name and event date
     * @param isDone indicator for whether task is done or not. If called when user adds via command line, default
     *               value is false. If called when loading data from storage, will be set to value indicated in storage
     * @param isLoad indicator for whether function was called from data storage or from user input
     * @return Task object referring to current event added
     * @throws IncompleteCommandException If only deadline command without any specifications provided
     * @throws EmptyTaskNameException if task name is missing
     * @throws MissingKeyWordException if /by is missing
     * @throws MissingDateException if date and time not in correct format
     */
    public static Task addEvent(String userInput, boolean isDone, boolean isLoad) throws
            IncompleteCommandException,
            EmptyTaskNameException,
            MissingKeyWordException,
            MissingDateException {
        // check validity of event string
        Errors checkEvent = InputParser.checkEventCommand(userInput);
        switch (checkEvent) {
        case INCOMPLETE_COMMAND:
            throw new IncompleteCommandException();
        case MISSING_AT:
            throw new MissingKeyWordException("at");
        case MISSING_TASK:
            throw new EmptyTaskNameException();
        case MISSING_EVENT:
            throw new MissingDateException("event");
        }
        // get taskName and date
        String taskName = InputParser.getTaskName(userInput);
        String eventDate = InputParser.getDate(userInput);
        try {
            LocalDate.parse(eventDate);
        } catch (DateTimeParseException e) {
            MessagePrinter.invalidDate();
            return null;
        }
        Event newEvent = new Event(isDone, taskName, eventDate);
        addTask(newEvent, taskName, isLoad);
        return newEvent;
    }

    /**
     * Function to delete task based on index indicated by user
     * @param userInput String from user to be parsed in InputParser to get index of task to delete
     * @throws MissingIndexException If index of task to delete not specified by user
     * @throws InvalidTaskIndexException If user does not provide a valid index eg. double, string
     * @throws InvalidIndexException If no tasks at index specified by user
     * @throws IndexOutOfBoundsException If index is less than 0 or more than 99
     */
    public static void deleteTask(String userInput) throws
            MissingIndexException,
            InvalidTaskIndexException,
            InvalidIndexException,
            IndexOutOfBoundsException {
        Errors checkTaskIndex = InputParser.checkDeleteAndDoneCommand(userInput);
        switch (checkTaskIndex) {
        case MISSING_INDEX:
            throw new MissingIndexException("delete");
        case INVALID_INDEX:
            throw new InvalidIndexException();
        case OUT_OF_BOUNDS_INDEX:
            throw new IndexOutOfBoundsException();
        case NONE:
            break;
        }
        int taskIndex = InputParser.getTaskIndex(userInput);
        // change task to done
        Task currTask = tasks.get(taskIndex);
        if (currTask == null) {
            throw new InvalidTaskIndexException();
        }
        String taskName = currTask.getTaskName();
        removeTask(currTask, taskName);
        UpdateData.rewriteList(tasks);
    }

    /**
     * Function to mark task as done based on index indicated by user
     * @param userInput String from user to be parsed in InputParser to get index of task to mark as done
     * @throws MissingIndexException If index of task to mark as done not specified by user
     * @throws InvalidTaskIndexException If user does not provide a valid index eg. double, string
     * @throws InvalidIndexException If no tasks at index specified by user
     * @throws IndexOutOfBoundsException If index is less than 0 or more than 99
     */
    public static void markAsDone(String userInput) throws
            MissingIndexException,
            InvalidTaskIndexException,
            InvalidIndexException,
            IndexOutOfBoundsException {
        Errors checkTaskIndex = InputParser.checkDeleteAndDoneCommand(userInput);
        switch (checkTaskIndex) {
        case MISSING_INDEX:
            throw new MissingIndexException("mark as done");
        case INVALID_INDEX:
            throw new InvalidIndexException();
        case OUT_OF_BOUNDS_INDEX:
            throw new IndexOutOfBoundsException();
        case NONE:
            break;
        }
        // get index of task to change
        int taskIndex = InputParser.getTaskIndex(userInput);
        // change task to done
        Task currTask = tasks.get(taskIndex);
        if (currTask == null) {
            throw new InvalidTaskIndexException();
        }
        currTask.setDone(true);
        MessagePrinter.taskMarkedAsDone(currTask);
        UpdateData.rewriteList(tasks);
    }

    /**
     * Function to search tasks ArrayList containing query from user in task name
     * @param userInput String from user input to be parsed in InputParser to get query from user
     * @throws MissingQueryException If no query is specified by user
     */
    public static void findTasks(String userInput) throws MissingQueryException {
        ArrayList<Task> filteredList = new ArrayList<Task>();

        if (InputParser.checkFindCommand(userInput) == Errors.MISSING_QUERY) {
            throw new MissingQueryException();
        }

        String query = InputParser.getQuery(userInput);

        for (Task task : tasks) {
            if (task.getTaskName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(task);
            }
        }

        MessagePrinter.filteredTasks(filteredList);

    }
}

package tasks;
import FridayExceptions.EmptyListException;
import FridayExceptions.MissingKeyWordException;
import FridayExceptions.MissingDateException;
import FridayExceptions.EmptyTaskNameException;
import FridayExceptions.InvalidTaskIndexException;
import FridayExceptions.IncompleteCommandException;
import FridayExceptions.MissingQueryException;
import enums.Errors;
import parser.InputParser;
import ui.MessagePrinter;
import storage.UpdateData;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    // Operations to add/delete tasks in list eg. addToDo, addDeadline
    /**
     * @throws EmptyListException
     * Function to print out the current Task List
     * Shift inside COMMANDS package as own Java class
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
     * @param newTask Task object of new Task to add to list
     * @param taskName name of task to print out
     * Function to add a new task to the list
     * Shift inside COMMANDS package as default add class; extended
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

    private static void removeTask(Task taskToRemove, String taskName) {
        tasks.remove(taskToRemove);
        MessagePrinter.removedTask(taskName, tasks.size());
    }

    // catch exception for not enough parameters
    // Extend addTask in (super(addTask))
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

    public static Task addDeadline(String userInput, boolean isDone, boolean isLoad) throws
            IncompleteCommandException,
            EmptyTaskNameException,
            MissingKeyWordException,
            MissingDateException {
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
        // get taskName
        String taskName = InputParser.getTaskName(userInput);
        // get deadline; catch exception for no deadline
        String deadline = InputParser.getDate(userInput);
        Deadline newDeadline = new Deadline(isDone, taskName, deadline);
        addTask(newDeadline, taskName, isLoad);
        return newDeadline;
    }

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
        // get taskName
        String taskName = InputParser.getTaskName(userInput);
        // get event date; catch exception for no event date
        String eventDate = InputParser.getDate(userInput);
        Event newEvent = new Event(isDone, taskName, eventDate);
        addTask(newEvent, taskName, isLoad);
        return newEvent;
    }

    // function to remove a task from the list of tasks
    // COMMAND "delete index"
    public static void deleteTask(String userInput) throws IncompleteCommandException, InvalidTaskIndexException {
        Errors checkTaskIndex = InputParser.checkDeleteAndDoneCommand(userInput);
        switch (checkTaskIndex) {
        case INCOMPLETE_COMMAND:
            throw new IncompleteCommandException();
        case OUT_OF_BOUNDS_INDEX:
            throw new IndexOutOfBoundsException();
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

    public static void markAsDone(String userInput) throws IncompleteCommandException, InvalidTaskIndexException {
        Errors checkTaskIndex = InputParser.checkDeleteAndDoneCommand(userInput);
        switch (checkTaskIndex) {
        case INCOMPLETE_COMMAND:
            throw new IncompleteCommandException();
        case OUT_OF_BOUNDS_INDEX:
            throw new IndexOutOfBoundsException();
        }
        // get index of task to chang
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

    public static void findTasks(String userInput) throws MissingQueryException {
        ArrayList<Task> filteredList = new ArrayList<Task>();

        if (InputParser.checkFindCommand(userInput) == Errors.MISSING_QUERY) {
            throw new MissingQueryException();
        }

        String query = InputParser.getQuery(userInput);

        for (Task task : tasks) {
            if (task.getTaskName().contains(query)) {
                filteredList.add(task);
            }
        }

        MessagePrinter.filteredTasks(filteredList);

    }
}

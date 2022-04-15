package main.java.duke.command;

import main.java.duke.Duke;
import main.java.duke.exceptions.InvalidTaskNumberException;
import main.java.duke.exceptions.DukeException;
import main.java.duke.exceptions.InvalidCommandException;
import main.java.duke.task.Task;
import main.java.duke.storage.Storage;
import main.java.duke.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the Duke task manager where it includes the methods to execute
 * the user's command while also including add, delete and listing of tasks.
 */
public class TaskList {

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static ArrayList<Task> matches = new ArrayList<>();
    protected static int taskCount = 0;
    protected static String[] description;
    protected static String[] parameter;
    private static boolean isPlural = false;
    private static final String LIST = "list";
    private static final String EXIT = "bye";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Performs the adding of task to the task list based on its task type.
     *
     * @param newTask The task of class {@code Task} which is to be added onto the task list
     */
    public static void addTask(Task newTask) {
        tasks.add(newTask);
        Storage.saveToFile(Duke.file);
        taskCount = tasks.size();
        if (taskCount > 1) {
            isPlural = true;
        }
        System.out.println(newTask.printOk() + taskCount + (isPlural ? " tasks" : " task")
                + " in the list.");
        Ui.printLineBottom();
    }

    /** Lists the tasks to the user on the terminal when user inputs {@code list}. */
    public void listTasks() {
        Ui.printLineTop();
        Ui.printTaskHeader();
        if(tasks.size() == 0) {
            Ui.sendNoTasksMessage();
        }
        int taskNumber;
        for (int i = 0; i < tasks.size(); i++) {
            taskNumber = i + 1;
            System.out.println(" " + taskNumber + tasks.get(i).printTask());
        }
        Ui.printLineBottom();
    }

    /**
     * Performs the removing of the specified task from the task list.
     *
     * @param taskNumber The task ID of the task which is to be removed from the task list
     */
    public static void deleteTask(int taskNumber) {
        taskCount = tasks.size() - 1;
        isPlural = tasks.size() != 2;
        System.out.println(" " + tasks.get(taskNumber).printDelete() + "\nNow you have "
                + taskCount + (isPlural ? " tasks" : " task")
                + " in the list.");
        Ui.printLineBottom();
        tasks.remove(taskNumber);
        Storage.saveToFile(Duke.file);
    }


    /**
     * Checks if the format of the date and time is correct when
     * user adds tasks of type {@code Deadline} and {@code Event}.
     *
     * @param dateTime The string which the user inputs under the parameter of
     *                 timing for tasks of type {@code Deadline} and {@code Event}
     * @return True when the format of the user timing input is correct and false when it's wrong
     */
    public static boolean isValidDateTime(String dateTime) {
        try {
            LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Gets the tasks with the description which matches the user's query when user inputs {@code find}.
     *
     * @param query The string which user inputs to be found from the list of tasks on Duke
     * @param list The array list of tasks found oen Duke
     */
    public static void getMatches(String query, ArrayList<Task> list) {
        matches.clear();
        for(Task task: list) {
            if (task.description.contains(query)) {
                matches.add(task);
            }
        }
    }

    /**
     * Executes the specific commands on the task list on duke.
     *
     * @param userInput The input given by the user to carry out on tasks list
     * @param userCommand The specific user command to carry out on the tasks list on duke
     * @throws DukeException The custom exceptions for specific errors
     * @throws IOException Signals that an I/O exception of some sort has occurred
     * @throws InvalidTaskNumberException 1. If the task ID is larger or lower than the
     * range of the size of the task list. 2. If the task ID is missing from the user input
     */
    public void executeCommand(String userInput, String userCommand)
            throws DukeException, IOException, InvalidTaskNumberException {
        int taskNumber = 0;
        switch (userCommand) {
        case EXIT:
            Duke.hasUserExited = true;
            break;
        case LIST:
            listTasks();
            break;
        case DONE:
            DoneCommand.executeUserCommand(userInput, taskNumber);
            break;
        case TODO:
             ToDoCommand.executeUserCommand(userInput);
            break;
        case DEADLINE:
            DeadLineCommand.executeUserCommand(userInput);
            break;
        case EVENT:
            EventCommand.executeUserCommand(userInput);
            break;
        case DELETE:
            DeleteCommand.executeUserCommand(userInput, taskNumber);
            break;
        case FIND:
            FindCommand.executeUserCommand(userInput);
            break;
        default:
            throw new InvalidCommandException();
        }
    }

}



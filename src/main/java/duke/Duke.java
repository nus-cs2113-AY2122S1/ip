package duke;

import duke.data.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main entry class for the Duke project.
 * @author Mohamed Irfan
 */
public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String ADD_TASK_MSG = "Got it. I've added this task: ";
    private static final String ERROR_MSG = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String TODO_ERROR = "The description of a todo cannot be empty.";
    private static final String DEADLINE_ERROR = "The description of a deadline cannot be empty and must have a '/by'.";
    private static final String EVENT_ERROR = "The description of an event cannot be empty and must have a '/at'.";
    private static final String SEARCH_ERROR = "Cannot find such a thing, please try again!";
    private static final String DELETE_ERROR = "Cannot delete something that doesn't exists!";
    private static final String DONE_ERROR = "Cannot mark a non-existing task as done!";
    private static final String FILE_PATH = "tasks.txt";
    private static ArrayList<Task> tasks;
    private static Storage storage;

    public static void main(String[] args) {
        showHelloGreeting();
        fileManager();
        executeResponses();
        showByeGreeting();
    }

    /**
     * Control method used to do file handling. It tries
     * to load the saved tasks file from the storage class.
     */
    private static void fileManager() {
        try {
            storage = new Storage(FILE_PATH);
            tasks = storage.loadTasksFromFile();
        } catch (DukeException err) {
            System.out.println("Unable to load file.");
        }
    }

    /**
     * The method responsible for all the user response management.
     * It receives user input and calls the appropriate method to
     * execute based on the functionality of the application.
     */
    private static void executeResponses() {
        Scanner in = new Scanner(System.in);
        String text;
        text = in.nextLine();
        while (!text.equals("bye")) {
            System.out.println(LINE);
            String[] userInput = text.split(" ");
            try {
                switch (userInput[0]) {
                case "list":
                    displayTasks();
                    break;
                case "done":
                    markTask(userInput);
                    break;
                case "delete":
                    deleteTask(userInput);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    break;
                case "todo":
                    addTodo(text);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    break;
                case "deadline":
                    addDeadline(text);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    break;
                case "event":
                    addEvent(text);
                    System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
                    break;
                case "find":
                    findTask(text);
                    break;
                default:
                    showErrorMessage();
                    break;
                }
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
            System.out.println(LINE);
            text = in.nextLine();
        }

        try {
            storage.saveTasksToFile(tasks);
        } catch (DukeException err) {
            System.out.println("Unable to save file.");
        }
    }

    /**
     * A helper method used to display all the tasks in the list.
     */
    private static void displayTasks() {
        if (tasks.size() == 0) {
            System.out.println("You do not have any tasks! Go and add some now!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * A helper method used to mark tasks as done.
     * @param userInput The task number to mark.
     * @throws DukeException If there are no numbers given to mark or the number is invalid.
     */
    private static void markTask(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException(DONE_ERROR);
        }
        int taskNum = Integer.parseInt(userInput[userInput.length - 1]);
        if (taskNum > tasks.size() || taskNum <= 0) {
            throw new DukeException(DONE_ERROR);
        }
        Task taskToSetDone = tasks.get(taskNum - 1);
        taskToSetDone.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToSetDone.getStatusIcon() + taskToSetDone.getDescription());
    }

    /**
     * A helper method used to delete tasks.
     * @param userInput The task number to delete.
     * @throws DukeException If there are no numbers given to delete or the number is invalid.
     */
    private static void deleteTask(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException(DELETE_ERROR);
        }
        int taskNumToDelete = Integer.parseInt(userInput[userInput.length - 1]);
        if (taskNumToDelete > tasks.size() || taskNumToDelete <= 0) {
            throw new DukeException(DONE_ERROR);
        }
        Task taskToDelete = tasks.remove(taskNumToDelete - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskToDelete.getStatusIcon() + taskToDelete.getDescription());
    }

    /**
     * A helper method used to add todos as one of the tasks.
     * @param text The user's input including the word "todo"
     *             and the subsequent todo description.
     * @throws DukeException If there are errors associated with the todo description.
     */
    private static void addTodo(String text) throws DukeException {
        if (text.length() <= "todo".length()) {
            throw new DukeException(TODO_ERROR);
        }
        String[] todoTaskInfo = extractInfo(text, "todo");
        Task newTodo = new Todo(todoTaskInfo[0], false);
        tasks.add(newTodo);
        System.out.println(ADD_TASK_MSG);
        System.out.println("\t" + newTodo);
    }

    /**
     * A helper method used to add deadlines as one of the tasks.
     * @param text The user's input including the word "deadline"
     *             and the subsequent deadline description.
     * @throws DukeException If there are errors associated with the deadline
     *             description or if the description does not contain "/by".
     */
    private static void addDeadline(String text) throws DukeException {
        if (text.length() <= "deadline".length()) {
            throw new DukeException(DEADLINE_ERROR);
        }

        if (!text.contains("/by")) {
            throw new DukeException(DEADLINE_ERROR);
        }
        String[] deadlineTaskInfo = extractInfo(text, "deadline");
        Task newDeadline = new Deadline(deadlineTaskInfo[0], deadlineTaskInfo[1], false);
        tasks.add(newDeadline);
        System.out.println(ADD_TASK_MSG);
        System.out.println("\t" + newDeadline);
    }

    /**
     * A helper method used to add event as one of the tasks.
     * @param text The user's input including the word "event"
     *             and the subsequent event description.
     * @throws DukeException If there are errors associated with the event
     *             description or if the description does not contain "/at".
     */
    private static void addEvent(String text) throws DukeException {
        if (text.length() <= "event".length()) {
            throw new DukeException(EVENT_ERROR);
        }

        if (!text.contains("/at")) {
            throw new DukeException(EVENT_ERROR);
        }
        String[] eventTaskInfo = extractInfo(text, "event");
        Task newEvent = new Event(eventTaskInfo[0], eventTaskInfo[1], false);
        tasks.add(newEvent);
        System.out.println(ADD_TASK_MSG);
        System.out.println("\t" + newEvent);
    }

    /**
     * A helper method used to search for a task by keyword.
     * @param text The user's input including the word "find"
     *             and the subsequent keyword.
     * @throws DukeException if there are errors in the keyword
     */
    private static void findTask(String text) throws DukeException {
        if (text.length() <= "find".length()) {
            throw new DukeException(SEARCH_ERROR);
        }
        ArrayList<Task> tasksFound = new ArrayList<>();
        String[] taskToFind = extractInfo(text, "find");
        for (Task task : tasks) {
            if (task.getDescription().contains(taskToFind[0])) {
                tasksFound.add(task);
            }
        }
        if (tasksFound.size() == 0) {
            System.out.println(SEARCH_ERROR);
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasksFound) {
            System.out.println("* " + task.toString());
        }
    }

    /**
     * A helper method used to extract the task info from the user input.
     * The method is also able to extract the dates for the tasks that need them.
     * @param taskString The user input which contains the task word eg. "todo"
     *             and the task description.
     * @param taskType The task type to extract the info from.
     *             eg. "todo", "event", "deadline"
     * @return taskInfo array of size 2, where the taskInfo[0] is the task description
     *             and taskInfo[1] is an optional date/time info.
     */
    private static String[] extractInfo(String taskString, String taskType) {
        String[] taskInfo = new String[2];
        int slashPos = taskString.indexOf('/');
        switch (taskType) {
        case "todo":
        case "find":
            taskInfo[0] = taskString.substring("find".length() + 1);
            break;
        case "deadline":
            taskInfo[0] = taskString.substring("deadline".length() + 1, slashPos - 1);
            taskInfo[1] = taskString.substring(slashPos + "/by".length() + 1);
            break;
        case "event":
            taskInfo[0] = taskString.substring("event".length() + 1, slashPos - 1);
            taskInfo[1] = taskString.substring(slashPos + "/at".length() + 1);
            break;
        default:
            break;
        }
        return taskInfo;
    }

    private static void showByeGreeting() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void showHelloGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE);
        System.out.println(" Hello! I'm Duke\n" +
                " What can I do for you?");
        System.out.println(LINE);
    }

    private static void showErrorMessage() throws DukeException {
        throw new DukeException(ERROR_MSG);
    }
}

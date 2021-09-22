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
    private static final String ADD_TASK_MSG = "Got it. I've added this duke.task: ";
    private static final String ERROR_MSG = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String TODO_ERROR = "The description of a todo cannot be empty.";
    private static final String DEADLINE_ERROR = "The description of a deadline cannot be empty and must have a '/by'.";
    private static final String EVENT_ERROR = "The description of an event cannot be empty and must have a '/at'.";
    private static final String[] taskTypes = {"todo", "deadline", "event"};
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
            String[] words = text.split(" ");
            int numOfTasks = tasks.size();
            try {
                switch (words[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < numOfTasks; i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                    break;
                case "done":
                    int taskNum = Integer.parseInt(words[words.length - 1]);
                    Task taskToSetDone = tasks.get(taskNum - 1);
                    taskToSetDone.setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskToSetDone.getStatusIcon() + taskToSetDone.getDescription());
                    break;
                case "delete":
                    int taskNumToDelete = Integer.parseInt(words[words.length - 1]);
                    Task taskToDelete = tasks.remove(taskNumToDelete - 1);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(taskToDelete.getStatusIcon() + taskToDelete.getDescription());
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
                default:
                    showErrorMessage();
                    break;
                }
            } catch (DukeException error) {
                System.out.println("☹ OOPS!!! " + error.getMessage());
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
     * A functionality method used to add todos as one of the tasks.
     * @param text The user's input including the word "todo"
     *             and the subsequent todo description.
     * @throws DukeException If there are errors associated with the todo description.
     */
    private static void addTodo(String text) throws DukeException {
        if (text.length() <= taskTypes[0].length()) {
            throw new DukeException(TODO_ERROR);
        }
        String[] todoTaskInfo = extractInfo(text, "todo");
        Task newTodo = new Todo(todoTaskInfo[0], false);
        tasks.add(newTodo);
        System.out.println(ADD_TASK_MSG);
    }

    /**
     * A functionality method used to add deadlines as one of the tasks.
     * @param text The user's input including the word "deadline"
     *             and the subsequent deadline description.
     * @throws DukeException If there are errors associated with the deadline
     *             description or if the description does not contain "/by".
     */
    private static void addDeadline(String text) throws DukeException {
        if (text.length() <= taskTypes[1].length()) {
            throw new DukeException(DEADLINE_ERROR);
        }

        if (!text.contains("/by")) {
            throw new DukeException(DEADLINE_ERROR);
        }
        String[] deadlineTaskInfo = extractInfo(text, "deadline");
        Task newDeadline = new Deadline(deadlineTaskInfo[0], deadlineTaskInfo[1], false);
        tasks.add(newDeadline);
        System.out.println(ADD_TASK_MSG);
    }

    /**
     * A functionality method used to add event as one of the tasks.
     * @param text The user's input including the word "event"
     *             and the subsequent event description.
     * @throws DukeException If there are errors associated with the event
     *             description or if the description does not contain "/at".
     */
    private static void addEvent(String text) throws DukeException {
        if (text.length() <= taskTypes[2].length()) {
            throw new DukeException(EVENT_ERROR);
        }

        if (!text.contains("/at")) {
            throw new DukeException(EVENT_ERROR);
        }
        String[] eventTaskInfo = extractInfo(text, "event");
        Task newEvent = new Event(eventTaskInfo[0], eventTaskInfo[1], false);
        tasks.add(newEvent);
        System.out.println(ADD_TASK_MSG);
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

    /**
     * Helper method used to extract the task info from the user input.
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
            taskInfo[0] = taskString.substring(5);
            break;
        case "deadline":
            taskInfo[0] = taskString.substring(9, slashPos - 1);
            taskInfo[1] = taskString.substring(slashPos + 4);
            break;
        case "event":
            taskInfo[0] = taskString.substring(6, slashPos - 1);
            taskInfo[1] = taskString.substring(slashPos + 4);
            break;
        default:
            break;
        }
        return taskInfo;
    }

    private static void showErrorMessage() throws DukeException {
        throw new DukeException(ERROR_MSG);
    }
}

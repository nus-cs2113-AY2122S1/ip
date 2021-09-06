package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * This class implements the Project Duke, a Personal
 * Assistant Chatbot that helps a user to keep track
 * of various things. Currently, Duke can greet user;
 * add/list user tasks, and mark them as "done".
 *
 * @author richwill28
 */
public class Duke {
    /** A scanner to read from standard input. */
    private Scanner sc;

    /** Stores the list of tasks */
    private TaskList taskList;

    /**
     * The constructor method. Initializes scanner and
     * tasks list.
     */
    public Duke() {
        sc = new Scanner(System.in);
        taskList = new TaskList();
    }

    public String getResponse() {
        return sc.nextLine();
    }

    /** Lists all the tasks. */
    public void list() {
        System.out.print(Ui.LINE);
        System.out.println(Ui.PADDING + "Here are the tasks in your list:");
        System.out.print(taskList);
        System.out.println(Ui.LINE);
    }

    /**
     * Marks a task as done and displays it.
     *
     * @param response User response.
     */
    public void markAsDone(String response) throws DukeException {
        String description = response.replace("done", "").strip();
        try {
            int taskNumber = Integer.parseInt(description);
            if (taskNumber <= taskList.getSize()) {
                taskList.markAsDone(taskNumber - 1);
                System.out.print(Ui.LINE);
                System.out.println(Ui.PADDING + "Nice! I've marked this task as done:");
                System.out.println(Ui.PADDING + "  " + taskList.getTask(taskNumber - 1));
                System.out.println(Ui.LINE);
            } else {
                throw new DukeException("Invalid number of task given.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(description + " is not a number.");
        }
    }

    /**
     * Reports to user that the task is added
     * successfully.
     *
     * @param task User task.
     */
    public void reportTaskAdded(Task task) {
        System.out.print(Ui.LINE);
        System.out.println(Ui.PADDING + "Got it. I've added this task:");
        System.out.println(Ui.PADDING + "  " + task);
        System.out.println(Ui.PADDING + "Now you have " + taskList.getSize() + " tasks in the list.");
        System.out.println(Ui.LINE);
    }

    /**
     * Adds Todo to the list of tasks.
     *
     * @param response User response, consists of only
     *                 description.
     */
    public void addTodo(String response) throws DukeException {
        String description = response.replace("todo", "").strip();
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            Task task = new Todo(description);
            taskList.addTask(task);
            reportTaskAdded(task);
        }
    }

    /**
     * Add Deadline to the list of tasks.
     *
     * @param response User response, consists of
     *                 description and deadline.
     */
    public void addDeadline(String response) throws DukeException {
        String[] params = response.replace("deadline", "").strip().split("/by");
        if (params.length > 2) {
            throw new DukeException("Invalid number of arguments given.");
        }

        try {
            String description = params[0].strip();
            String by = params[1].strip();
            if (description.isBlank() || by.isBlank()) {
                throw new DukeException("Looks like you have missing arguments.");
            } else {
                Task task = new Deadline(description, by);
                taskList.addTask(task);
                reportTaskAdded(task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Looks like you have missing arguments.");
        }
    }

    /**
     * Add Event to the list of tasks.
     *
     * @param response User response, consists of
     *                 description and time period.
     */
    public void addEvent(String response) throws DukeException {
        String[] params = response.replace("event", "").strip().split("/at");
        if (params.length > 2) {
            throw new DukeException("Invalid number of arguments given.");
        }

        try {
            String description = params[0].strip();
            String at = params[1].strip();
            if (description.isBlank() || at.isBlank()) {
                throw new DukeException("Looks like you have missing arguments.");
            } else {
                Task task = new Event(description, at);
                taskList.addTask(task);
                reportTaskAdded(task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Looks like you have missing arguments.");
        }
    }

    /** Starts the main functionality of Duke. */
    public void start() {
        while (true) {
            try {
                Command response = new Command(getResponse());
                if (response.isBye()) {
                    break;
                } else if (response.isList()) {
                    this.list();
                } else if (response.isDone()) {
                    this.markAsDone(response.getCommand());
                } else if (response.isTodo()) {
                    this.addTodo(response.getCommand());
                } else if (response.isDeadline()) {
                    this.addDeadline(response.getCommand());
                } else if (response.isEvent()) {
                    this.addEvent(response.getCommand());
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    /** Runs the whole Duke program. */
    public void run() {
        Ui.printGreeting();
        this.start();
        Ui.printGoodbye();
    }

    /**
     * The main method of Duke.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

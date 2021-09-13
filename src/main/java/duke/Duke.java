package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");
    private static final String STORAGE_DIRECTORY = "data";
    private static final String FILE_NAME = "duke.txt";
    private static final Path PATH_TO_DIRECTORY = Paths.get(ROOT_DIRECTORY, STORAGE_DIRECTORY);
    private static final Path PATH_TO_FILE = Paths.get(ROOT_DIRECTORY, STORAGE_DIRECTORY, FILE_NAME);

    private Scanner sc;
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

    /** List all the tasks. */
    public void list() {
        System.out.print(Ui.LINE);
        System.out.println(Ui.PADDING + "Here are the tasks in your list:");
        System.out.print(taskList);
        System.out.println(Ui.LINE);
    }

    /** Create new data file. */
    public void createDataFile() {
        try {
            File file = new File(PATH_TO_FILE.toString());
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            // Unexpected exceptions
            System.out.println(Ui.PADDING + e);
        }
    }

    /** Load stored data, if available. */
    public void loadDataFile() {
        try {
            FileReader fin = new FileReader(PATH_TO_FILE.toString());
            BufferedReader bin = new BufferedReader(fin);
            List<String> data = new ArrayList<>();
            String line;
            while ((line = bin.readLine()) != null) {
                data.add(line);
            }
            bin.close();
            taskList = TaskList.deserialize(data);
            System.out.print(Ui.LINE);
            System.out.println(Ui.PADDING + "Data retrieved successfully.");
            System.out.println(Ui.PADDING + "Here are the tasks in your list:");
            System.out.print(taskList);
            System.out.println(Ui.LINE);
        } catch (IllegalArgumentException e) {
            // Overwrite existing data
            System.out.println(Ui.PADDING + "Error: Data stored in invalid format. Overwriting file..");
        } catch (FileNotFoundException e) {
            // Create new data file
            System.out.println(Ui.PADDING + "Data not found. Initializing new file..");
            createDataFile();
        } catch (IOException e) {
            // Unexpected exceptions
            System.out.println(Ui.PADDING + e);
        }
    }

    /** Save data into a text file */
    public void saveData() {
        try {
            FileWriter fout = new FileWriter(PATH_TO_FILE.toString());
            BufferedWriter bout = new BufferedWriter(fout);
            bout.write(taskList.serialize());
            bout.close();
        } catch (IOException e) {
            // Do nothing
            System.out.println(Ui.PADDING + "Error: Unable to save data.");
        }
    }

    /**
     * Mark task as done and display it.
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
                throw new DukeException("Invalid task number given.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(description + " is not a number.");
        }
        saveData();
    }

    /**
     * Report to user that task is added successfully.
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
     * Add Todo to the list of tasks.
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
        saveData();
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
        saveData();
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
        saveData();
    }

    /**
     * Delete a task from the list.
     *
     * @param response User response.
     */
    public void deleteTask(String response) throws DukeException {
        String description = response.replace("delete", "").strip();
        try {
            int taskNumber = Integer.parseInt(description);
            if (taskNumber <= taskList.getSize()) {
                System.out.print(Ui.LINE);
                System.out.println(Ui.PADDING + "Noted. I've removed this task:");
                System.out.println(Ui.PADDING + "  " + taskList.getTask(taskNumber - 1));
                taskList.deleteTask(taskNumber - 1);
                System.out.println(Ui.PADDING + "Now you have " + taskList.getSize() + " tasks in the list.");
                System.out.println(Ui.LINE);
            } else {
                throw new DukeException("Invalid task number given.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(description + " is not a number.");
        }
    }

    /** Start the main functionality of Duke. */
    public void start() {
        while (true) {
            try {
                Command response = new Command(getResponse());
                if (response.isBye()) {
                    break;
                } else if (response.isList()) {
                    list();
                } else if (response.isDone()) {
                    markAsDone(response.getCommand());
                } else if (response.isTodo()) {
                    addTodo(response.getCommand());
                } else if (response.isDeadline()) {
                    addDeadline(response.getCommand());
                } else if (response.isEvent()) {
                    addEvent(response.getCommand());
                } else if (response.isDelete()) {
                    deleteTask(response.getCommand());
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    /** Run the whole Duke program. */
    public void run() {
        Ui.printGreeting();
        this.loadDataFile();
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

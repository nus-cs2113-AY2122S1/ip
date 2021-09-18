package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String DELIMITER = "@@@";

    private static final int TASK_TYPE = 0;
    private static final int TASK_COMPLETION_STATUS = 1;
    private static final int TASK_NAME = 2;
    private static final int TASK_DATE = 3;

    private static final String ERROR_LOAD = "     File not found. A new list will be started.";
    private static final String ERROR_SAVE = "     Save file not found. List of tasks will not be saved";
    private static final String ERROR_UNRECOGNISED_TASK_TYPE = "     Task type not recognised";

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task data from the file when Duke starts up.
     */
    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        checkFileExist();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNext()) {
                Task task = parseLine(scanner.nextLine());
                tasks.addTask(task);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(ERROR_LOAD);
        }
        return tasks;
    }

    /**
     * Checks if the file exists, otherwise new file will be created.
     */
    private void checkFileExist() {
        File file = new File(System.getProperty("user.dir"), filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Make sense of the string line data loaded from the file
     * and adds the saved tasks into the list.
     *
     * @param line Single line read from the file as a string.
     * @throws DukeException If there is an error with the string format on the file.
     */
    private Task parseLine(String line) throws DukeException {
        String[] words = line.split(DELIMITER);
        Task task = parseTask(words);
        setTaskCompletionStatus(task, words[TASK_COMPLETION_STATUS]);
        return task;
    }

    /**
     * Creates a new Task depending on the task type specifications.
     *
     * @param words String array containing details about the task.
     * @return New task depending on task type specifications.
     * @throws DukeException If an unrecognised task type is detected.
     *                       Unlikely to happen unless the file is corrupted.
     */
    private Task parseTask(String[] words) throws DukeException {
        Task task;
        switch(words[TASK_TYPE]) {
        case "T":
            task = new Todo(words[TASK_NAME]);
            break;
        case "D":
            task = new Deadline(words[TASK_NAME], words[TASK_DATE]);
            break;
        case "E":
            task = new Event(words[TASK_NAME], words[TASK_DATE]);
            break;
        default:
            throw new DukeException(ERROR_UNRECOGNISED_TASK_TYPE);
        }
        return task;
    }

    /**
     * Marks a task in the list as completed if defined as such in the save file.
     *
     * @param task               Task in the list.
     * @param completedIndicator "1" if task is mark as completed.
     *      *                    "0" if task is not mark as completed.
     */
    private void setTaskCompletionStatus(Task task, String completedIndicator) {
        if (isTaskCompleted(completedIndicator)) {
            task.markTaskAsDone();
        }
    }

    /**
     * Checks if a task is marked as completed in the save file.
     *
     * @param completedIndicator "1" if task is mark as completed.
     *                           "0" if task is not mark as completed.
     * @return True if task is mark as completed.
     *         False if task is not mark as completed.
     */
    private boolean isTaskCompleted(String completedIndicator) {
        return completedIndicator.equals("1");
    }

    /**
     * Save the tasks in the file upon exiting Duke.
     */
    public void saveTask(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.getListSize(); i++) {
                fileWriter.write(tasks.getTaskStringForStorage(i));
            }
            fileWriter.close();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(ERROR_SAVE);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

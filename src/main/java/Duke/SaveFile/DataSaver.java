package Duke.SaveFile;

import Duke.Exception.DukeException;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Event;
import Duke.TaskTypes.Deadline;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataSaver {

    private static final String DONE_STATUS = "1";
    private static final String NOT_DONE_STATUS = "0";
    private static final String DIRECTORY_PATH = "savedData";
    private static final String FILE_PATH = "savedData\\duke.txt";
    private static final String DIVIDER = " | ";

    /**
     * Loads saved tasks in the file to task list
     *
     * @param taskList the array list which will store all saved user tasks
     */
    public static void manageLoad(ArrayList<Task> taskList) {
        File file = new File(FILE_PATH);
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException fileNotFoundException) {
            createNewFile();
        }
        loadFileContents(taskList, scan);
    }

    /**
     * Creates new file if there was no save file in the directory
     */
    public static void createNewFile() {
        try {
            Files.createDirectories(Paths.get(DIRECTORY_PATH));
            Files.createFile(Paths.get(FILE_PATH));
        } catch (IOException ioException) {
            DukeException.createIOException(ioException);
        }
    }

    /**
     * Loads the tasks from the text file to task list, the array list
     *
     * @param taskList the array list which will store the user tasks
     * @param scan the scanned save file
     */
    public static void loadFileContents(ArrayList<Task> taskList, Scanner scan) {
        while (scan != null && scan.hasNext()) {
            String newTask = scan.nextLine();
            try {
                addToTaskList(taskList, newTask);
            } catch (DukeException invalidSaveFileException) {
                DukeException.invalidSaveFileException();
            }
        }
    }

    /**
     * Add newly scanned saved task to task list, the array list
     *
     * @param taskList the array list which will store the user tasks
     * @param newTask the newly scanned saved task
     * @throws DukeException if the newly scanned saved task is of an invalid format
     */
    public static void addToTaskList(ArrayList<Task> taskList, String newTask) throws DukeException {
        String[] taskDetails = newTask.split("\\|");

        if (!checkValidDetails(taskDetails)) {
            throw new DukeException();
        }

        String type = taskDetails[0].trim();
        switch(type) {
        case "T":
            TaskListTypes.addTodoToTaskList(taskList, taskDetails);
            break;
        case "D":
            TaskListTypes.addDeadlineToTaskList(taskList, taskDetails);
            break;
        case "E":
            TaskListTypes.addEventToTaskList(taskList, taskDetails);
            break;
        default:
            throw new DukeException();
        }
    }

    /**
     * Checks if the task details contain the correct task status
     *
     * @param taskDetails the task details stored in save file
     * @return true if task status is valid, false otherwise
     */
    public static boolean checkValidDetails(String[] taskDetails) {
        String status = taskDetails[1].trim();
        if (!status.equals(DONE_STATUS) && !status.equals(NOT_DONE_STATUS)) {
            return false;
        }

        return taskDetails.length == 3 || taskDetails.length == 4;
    }

    /**
     * Assigned the task status according to the saved details
     *
     * @param addedTask the task whose status will be updated
     * @param taskDetails the task details stored in save file
     */
    public static void addDoneStatus(Task addedTask, String[] taskDetails) {
        String status = taskDetails[1].trim();
        if (status.equals(DONE_STATUS)) {
            addedTask.markAsDone();
        }
    }

    /**
     * Saves tasks from the task list to the text file
     *
     * @param taskList the array list containing user tasks to be saved
     */
    public static void manageSave(ArrayList<Task> taskList) {
        try {
            saveFileContents(taskList);
        } catch (IOException ioException) {
            DukeException.SaveIOException(ioException);
        }
    }

    /**
     * Saves the tasks from the task list to the text file
     *
     * @param taskList the array list containing user tasks
     * @throws IOException
     */
    public static void saveFileContents(ArrayList<Task> taskList) throws IOException {
        FileWriter writeFile = new FileWriter(FILE_PATH);
        configureTask(taskList, writeFile);
        writeFile.close();
    }

    /**
     * Save the tasks to the text file in a proper and parsed format
     *
     * @param taskList the array list containing user tasks
     * @param writeFile the text file to save the tasks to
     * @throws IOException
     */
    public static void configureTask(ArrayList<Task> taskList, FileWriter writeFile) throws IOException {
        for (Task task : taskList) {
            if (task != null) {
                StringBuilder parsedTask = new StringBuilder();
                parseType(task, parsedTask);
                parseStatus(task, parsedTask);
                parseDescription(task, parsedTask);
                writeFile.write(parsedTask + System.lineSeparator());
            }
        }
    }

    /**
     * Save the task type section using the correct format
     *
     * @param task the task to be saved in the text file
     * @param parsedTask the new saved task format in the text file
     */
    public static void parseType(Task task, StringBuilder parsedTask) {
        String type = task.getType();
        parsedTask.append(type).append(DIVIDER);
    }

    /**
     * Save the task status section using the correct format
     *
     * @param task the task to be saved in the text file
     * @param parsedTask the new saved task format in the text file
     */
    public static void parseStatus(Task task, StringBuilder parsedTask) {
        if (task.getIsDone()) {
            parsedTask.append("1").append(DIVIDER);
        } else {
            parsedTask.append("0").append(DIVIDER);
        }
    }

    /**
     * Save the task description, if any, using the correct format
     *
     * @param task the task to be saved in the text file
     * @param parsedTask the new saved task format in the text file
     */
    public static void parseDescription(Task task, StringBuilder parsedTask) {
        parsedTask.append(task.getDescription()).append(DIVIDER);
        if (task instanceof Deadline) {
            parsedTask.append(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            parsedTask.append(((Event) task).getAt());
        }
    }
}

package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Scanner;

public class Storage {
    private static final String PATH_NAME = "data/data.txt";
    private static final File FILE = new File(PATH_NAME);

    private static final String STORAGE_SEPARATOR = " \\| ";
    private static final String ERROR_LOAD_STORAGE = "Error: Unable to load data.";
    private static final String ERROR_SAVE_STORAGE = "Error: Unable to save data.";
    private static final String NEW_FILE_CREATED = " New data file created.";
    private static final String ERROR_PARSE_STORAGE = "Error: Unable to parse data file.";

    /**
     * Constructor for Storage class. Attempts to access data file,
     * creates a new data file if it is not found.
     *
     * @throws DukeException if unable to create or load data file
     */
    public Storage() throws DukeException {
        try {
            FILE.getParentFile().mkdirs();
            FILE.createNewFile();
        } catch (IOException e) {
            throw new DukeException(ERROR_LOAD_STORAGE);
        }
    }

    /**
     * Loads data from data file into taskList.
     *
     * @param taskList TaskList where loaded data will be stored in.
     * @throws DukeException if unable to open data.txt file
     */
    public void loadData(TaskList taskList) throws DukeException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(FILE);
            while (scanner.hasNext()) {
                Task task = readTask(scanner.nextLine());
                taskList.addTask(task);
            }
        } catch (FileNotFoundException | DukeException e) {
            taskList.deleteAllTasks();
            saveData(taskList);
            throw new DukeException(e.getMessage() + NEW_FILE_CREATED);
        }
    }

    /**
     * Reads task information from taskList and stores it into data/data.txt file.
     *
     * @param taskList the TaskList to be stored.
     * @throws DukeException if unable to save data.
     */
    public void saveData(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(PATH_NAME);
            fw.write(taskList.getStorageString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(ERROR_SAVE_STORAGE);
        }
    }

    /**
     * Creates a Task containing the information specified in string.
     *
     * @param storageLine String corresponding to a line that was read from storage file to be parsed into a Task.
     * @return Task containing the information stored in string.
     * @throws DukeException if information in data.txt is in the incorrect format.
     */
    private Task readTask(String storageLine) throws DukeException {
        try {
            String[] words = splitWords(storageLine);
            Task task = parseTask(words);
            markCompleted(words, task);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_PARSE_STORAGE);
        }
    }

    /**
     * Splits the given string using on the | character as a separator
     *
     * @param storageLine line that was read from storage file to be parsed into a Task
     * @return Array of Strings after storageLine was split
     */
    private static String[] splitWords(String storageLine) {
        String[] words = storageLine.split(STORAGE_SEPARATOR);
        return words;
    }

    /**
     * Returns a Task corresponding to the values stored in words.
     *
     * @param words String array containing the information of the Task
     * @return Task corresponding to the information in words
     * @throws DukeException             if words contains invalid values
     * @throws IndexOutOfBoundsException if words is missing some Task information
     */
    private static Task parseTask(String[] words) throws DukeException, IndexOutOfBoundsException {
        try {
            switch (words[0]) {
            case "T":
                return new ToDo(words[2]);
            case "E":
                return new Event(words[2], words[3]);
            case "D":
                ZoneOffset zone = ZoneOffset.UTC;
                LocalDateTime byDateTime = LocalDateTime.ofEpochSecond(Long.parseLong(words[3]), 0, zone);
                return new Deadline(words[2], byDateTime);
            default:
                throw new DukeException(ERROR_PARSE_STORAGE);
            }
        } catch (NumberFormatException | DateTimeException e) {
            throw new DukeException(ERROR_PARSE_STORAGE);
        }
    }

    /**
     * Sets the given Task as completed based on the values stored in words.
     *
     * @param words String array containing the information of the Task
     * @param task  Task to be marked as read if indicated in words
     * @throws IndexOutOfBoundsException if words is missing some Task information
     */
    private static void markCompleted(String[] words, Task task) throws IndexOutOfBoundsException {
        if (words[1].equals("1")) {
            task.setCompleted();
        }
    }
}

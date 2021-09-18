package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Storage {

    public static final String CURRENT_DIRECTORY = "user.dir";
    public static final String STRING_IP = "ip";

    public static final int BEGIN_INDEX = 0;
    public static final int NOT_FOUND_INDEX = -1;

    public static final String DIRECTORY = "data";
    public static final String STORAGE_FILE = "duke.txt";
    public static final String DELIMITER = "\\|";
    public static final String TODO_TYPE = "T";
    public static final String EVENT_TYPE = "E";
    public static final String DEADLINE_TYPE = "D";

    public static final int MAX_INDEX = 3;
    public static final int DESCRIPTION_INDEX = 2;
    public static final int MAX_EVENT_DEADLINE = 4;

    private Path storagePath;

    /**
     * Use the current directory and tries to find the string ip and maps out the data directory
     */
    public Storage() {
        String path = System.getProperty(CURRENT_DIRECTORY);
        int findIpIndex = path.indexOf(STRING_IP);
        if (findIpIndex != NOT_FOUND_INDEX) {
            path = path.substring(BEGIN_INDEX, findIpIndex + STRING_IP.length());
        }
        storagePath = Paths.get(path, DIRECTORY, STORAGE_FILE);
    }

    /**
     * Check directory and try to create the data directory to store the data
     *
     * @throws IOException failed to create directory
     */
    private void createDataDirectory() throws IOException {
        if (!Files.exists(storagePath.getParent())) {
            Files.createDirectories(storagePath.getParent());
        }
    }

    /**
     * Check storage file and try to create the storage file to store the data
     *
     * @throws IOException failed to create file
     */
    private void createStorageFile() throws IOException {
        if (!Files.exists(storagePath)) {
            Files.createFile(storagePath);
        }
    }

    /**
     * Parse a string to a task object
     *
     * @param task the task as a string
     * @return the task object
     * @throws IllegalArgumentException file does not follow format
     */
    private Task parseTask(String task) throws IllegalArgumentException {
        String[] taskInfo = Arrays.stream(task.split(DELIMITER))
                .map(String::strip)
                .toArray(String[]::new);
        if (taskInfo.length < MAX_INDEX) {
            throw new IllegalArgumentException();
        }
        Task singleTask;
        if (taskInfo[BEGIN_INDEX].equals(TODO_TYPE) && taskInfo.length == MAX_INDEX) {
            singleTask = new Todo(taskInfo[DESCRIPTION_INDEX]);
        } else if (taskInfo[BEGIN_INDEX].equals(EVENT_TYPE) && taskInfo.length == MAX_EVENT_DEADLINE) {
            singleTask = new Event(taskInfo[DESCRIPTION_INDEX], taskInfo[MAX_INDEX]);
        } else if (taskInfo[BEGIN_INDEX].equals(DEADLINE_TYPE) && taskInfo.length == MAX_EVENT_DEADLINE) {
            singleTask = new Deadline(taskInfo[DESCRIPTION_INDEX], taskInfo[MAX_INDEX]);
        } else {
            throw new IllegalArgumentException();
        }
        if (taskInfo[1].equals("1")) {
            singleTask.markAsDone();
        }
        return singleTask;
    }

    /**
     * loads the data file from and parses it
     *
     * @param taskList the TaskManager to store all the loaded task
     * @throws IOException              Unable to read file
     * @throws IllegalArgumentException parsing of file failed
     */
    public void loadFile(TaskManager taskList) throws IOException, IllegalArgumentException {
        createDataDirectory();
        createStorageFile();
        try (Stream<String> stream = Files.lines(storagePath)) {
            stream.map(this::parseTask).forEach(taskList::addTask);
        } catch (IOException fileException) {
            throw new IOException();
        }
    }

    /**
     * Stores data to storage file
     *
     * @param taskList TaskManager containing the task we want to store
     * @throws IOException Unable to write to file
     */
    public void saveFile(TaskManager taskList) throws IOException {
        FileWriter storageFile = new FileWriter(storagePath.toFile());
        for (int i = BEGIN_INDEX; i < taskList.getNumberOfTasks(); i++) {
            storageFile.write(taskList.getTask(i).saveToText() + "\n");
            storageFile.flush();
        }
        storageFile.close();
    }
}

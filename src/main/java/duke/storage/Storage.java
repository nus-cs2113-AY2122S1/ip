package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Stream;

public class Storage {

    private static final String CURRENT_DIRECTORY = "user.dir";
    private static final String DATA_DIRECTORY = "data";
    private static final String STORAGE_FILE = "duke.txt";

    private static final String DELIMITER = "\\|";

    private static final String TODO_TYPE = "T";
    private static final String EVENT_TYPE = "E";
    private static final String DEADLINE_TYPE = "D";
    private static final String TASK_COMPLETED_STATUS = "1";

    private static final int BEGIN_INDEX = 0;
    private static final int MAX_INDEX = 3;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int MAX_EVENT_DEADLINE_LENGTH = 4;


    protected Path storagePath;

    /**
     * Use the current directory and tries to find the string ip and maps out the data directory
     *
     * @param storageFilePath the file path to store the file at, if not specified defaults is used
     */
    public Storage(String storageFilePath) {
        String path = System.getProperty(CURRENT_DIRECTORY);
        if (storageFilePath != null && !storageFilePath.isEmpty()) {
            storagePath = Paths.get(path, storageFilePath);
        } else {
            storagePath = Paths.get(path, DATA_DIRECTORY, STORAGE_FILE);
        }
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
     * @throws DateTimeParseException   unable to parse datetime according to the format
     */
    private Task parseTask(String task)
            throws IllegalArgumentException, DateTimeParseException {
        String[] taskInfo = Arrays.stream(task.split(DELIMITER))
                .map(String::strip)
                .toArray(String[]::new);
        if (taskInfo.length < MAX_INDEX) {
            throw new IllegalArgumentException();
        }
        Task singleTask;
        if (taskInfo[BEGIN_INDEX].equals(TODO_TYPE) && taskInfo.length == MAX_INDEX) {
            singleTask = new Todo(taskInfo[DESCRIPTION_INDEX]);
        } else if (taskInfo[BEGIN_INDEX].equals(EVENT_TYPE) && taskInfo.length == MAX_EVENT_DEADLINE_LENGTH) {
            singleTask = new Event(taskInfo[DESCRIPTION_INDEX], taskInfo[MAX_INDEX]);
        } else if (taskInfo[BEGIN_INDEX].equals(DEADLINE_TYPE) && taskInfo.length == MAX_EVENT_DEADLINE_LENGTH) {
            singleTask = new Deadline(taskInfo[DESCRIPTION_INDEX], taskInfo[MAX_INDEX]);
        } else {
            throw new IllegalArgumentException();
        }
        if (taskInfo[1].equals(TASK_COMPLETED_STATUS)) {
            singleTask.markAsDone();
        }
        return singleTask;
    }

    /**
     * loads the data file from and parses it
     *
     * @param taskList the TaskList to store all the loaded task
     * @throws IOException              Unable to read file
     * @throws IllegalArgumentException parsing of file failed
     */
    public void loadFile(TaskList taskList) throws IOException, IllegalArgumentException {
        createDataDirectory();
        createStorageFile();
        Stream<String> stream = Files.lines(storagePath);
        stream.map(this::parseTask).forEach(taskList::addTask);
    }

    /**
     * Writes data to storage file
     *
     * @param taskList TaskList containing the task we want to store
     * @throws IOException Unable to write to file
     */
    public void saveFile(TaskList taskList) throws IOException {
        FileWriter storageFile = new FileWriter(storagePath.toFile());
        for (int i = BEGIN_INDEX; i < taskList.getNumberOfTasks(); i++) {
            storageFile.write(taskList.getTask(i).saveToText() + "\n");
            storageFile.flush();
        }
        storageFile.close();
    }
}

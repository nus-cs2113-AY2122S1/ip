package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

// Code below inspired by
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java

/**
 * Represents the file that stores the task list data, and contains the methods that parses the file and encodes the
 * data into the file.
 */
public class Storage {
    private static final Path DATA_DIRECTORY_PATH = Paths.get("data");
    private static final String DATA_FILE_NAME = "duke.txt";
    private static final Path DATA_FILE_PATH = DATA_DIRECTORY_PATH.resolve(DATA_FILE_NAME);
    public static final String DATA_FILE_SEPARATOR = " ` ";

    private static final String MESSAGE_DATA_FILE_ACCESS_ERROR = "There was an error accessing the data file: '"
            + DATA_FILE_PATH + "'";
    private static final String MESSAGE_DATA_FILE_PARSE_ERROR = "There was an error parsing the data file:";
    private static final String MESSAGE_TASK_FORMAT_ERROR = "Unrecognised task format.";

    private static final String MESSAGE_UNRECOGNISED_TASK_TYPE_ICON = "Unrecognised task type icon: '%1$s'";
    private static final String MESSAGE_UNRECOGNISED_TASK_STATUS_ICON = "Unrecognised task status icon: '%1$s'";
    private static final String MESSAGE_UNRECOGNISED_EVENT_FORMAT = "Unrecognised event format.\n"
            + "Please ensure you provide the date/time of the event.";
    private static final String MESSAGE_UNRECOGNISED_DEADLINE_FORMAT = "Unrecognised deadline format.\n"
            + "Please ensure you provide the date/time of the deadline.";

    private final Path path;
    private boolean isUsingNewFile = false;

    public Storage(String filePath) {
        path = Paths.get(filePath);
    }

    public String getPath() {
        return path.toString();
    }

    public boolean isUsingNewFile() {
        return isUsingNewFile;
    }

    public TaskList loadData() throws StorageException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            isUsingNewFile = true;
            return new TaskList();
        }
        try {
            List<String> taskDataStrings = Files.readAllLines(path);
            return parseData(taskDataStrings);
        } catch (IOException | DukeException e) {
            e.printStackTrace();
            throw new StorageException(MESSAGE_DATA_FILE_PARSE_ERROR + "\n" + e.getMessage());
        }
    }

    private static TaskList parseData(List<String> taskDataStrings) throws DukeException {
        final TaskList tasks = new TaskList();
        for (String taskString : taskDataStrings) {
            final String[] args = taskString.split(DATA_FILE_SEPARATOR);
            Task task = decodeTaskFromString(args);
            tasks.addTask(task);
        }
        return tasks;
    }

    private static Task decodeTaskFromString(String[] args) throws DukeException {
        if (args.length < 3) {
            throw new DukeException(MESSAGE_TASK_FORMAT_ERROR);
        }
        final String taskTypeIcon = args[0];
        final String statusString = args[1];
        final String description = args[2];
        Task task;
        switch (taskTypeIcon) {
        case ToDo.TASK_TYPE_ICON:
            task = new ToDo(description);
            break;
        case Event.TASK_TYPE_ICON:
            if (args.length < 4) {
                throw new DukeException(MESSAGE_UNRECOGNISED_EVENT_FORMAT);
            }
            final String at = args[3];
            task = new Event(description, at);
            break;
        case Deadline.TASK_TYPE_ICON:
            if (args.length < 4) {
                throw new DukeException(MESSAGE_UNRECOGNISED_DEADLINE_FORMAT);
            }
            final String by = args[3];
            task = new Deadline(description, by);
            break;
        default:
            throw new DukeException(String.format(MESSAGE_UNRECOGNISED_TASK_TYPE_ICON, taskTypeIcon));
        }
        if (statusString.equals("1")) {
            task.setAsDone();
        } else if (!statusString.equals("0")) {
            throw new DukeException(String.format(MESSAGE_UNRECOGNISED_TASK_STATUS_ICON, statusString));
        }
        return task;
    }

    public void saveData(TaskList tasks) throws StorageException {
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, formatTasksAsDataOutput(tasks));
        } catch (IOException e) {
            throw new StorageException(MESSAGE_DATA_FILE_ACCESS_ERROR + path + "\n" + e.getMessage());
        }
    }

    private static ArrayList<String> formatTasksAsDataOutput(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        ArrayList<String> taskDataStrings = new ArrayList<>();
        for (Task task : tasks) {
            taskDataStrings.add(task.toDataString());
        }
        return taskDataStrings;
    }

    /** Signals that some error occurred while trying to load or store the data file. */
    public static class StorageException extends Exception {
        public StorageException(String message) {
            super(message);
        }
    }
}

package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.parser.Parser;
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
    // Capturing group variables store the name of the named capturing group.
    private static final String CAPTURING_GROUP_TASK_TYPE_ICON = "taskTypeIcon";
    private static final String CAPTURING_GROUP_IS_DONE = "isDone";
    private static final String CAPTURING_GROUP_TASK_DETAILS = "taskDetails";
    /** Used for initial separation of task type icon, whether task is done, and the rest of the task details. */
    private static final Pattern BASIC_TASK_FORMAT = Pattern.compile(
            "(?<" + CAPTURING_GROUP_TASK_TYPE_ICON + ">\\S+) "
                    + "(?<" + CAPTURING_GROUP_IS_DONE + ">\\S+) "
                    + "(?<" + CAPTURING_GROUP_TASK_DETAILS + ">.+)");

    private static final String SYMBOL_TASK_DONE = "1";
    private static final String SYMBOL_TASK_NOT_DONE = "0";

    private static final String MESSAGE_DATA_FILE_ACCESS_ERROR = "There was an error accessing the data file '%1$s'\n"
            + "%2$s";
    private static final String MESSAGE_DATA_FILE_PARSE_ERROR = "There was an error parsing the data file:\n"
            + "%1$s";

    private static final String MESSAGE_TASK_FORMAT_ERROR = "Unrecognised task format.";
    private static final String MESSAGE_UNRECOGNISED_TASK_TYPE_ICON = "Unrecognised task type icon: '%1$s'";
    private static final String MESSAGE_UNRECOGNISED_TASK_STATUS_ICON = "Unrecognised task status icon: '%1$s'";
    private static final String MESSAGE_UNRECOGNISED_EVENT_FORMAT = "Unrecognised event format.\n"
            + "Please ensure you provide the date/time of the event.";
    private static final String MESSAGE_UNRECOGNISED_DEADLINE_FORMAT = "Unrecognised deadline format.\n"
            + "Please ensure you provide the date/time of the deadline.";

    private final Path path;
    private boolean isUsingNewFile = false;

    /**
     * Initialises storage and stores {@code filePath}.
     *
     * @param filePath Path of the file that stores the data.
     */
    public Storage(String filePath) {
        path = Paths.get(filePath);
    }

    /**
     * Returns path of the file that stores the data.
     *
     * @return Path of the data file as a string.
     */
    public String getPath() {
        return path.toString();
    }

    /**
     * Returns whether a new data file is being used.
     *
     * @return {@code true} if there is no existing data file and a new file is being used; false otherwise;
     */
    public boolean isUsingNewFile() {
        return isUsingNewFile;
    }

    /**
     * Loads task data from the data file.
     *
     * @return Task list parsed from the data file.
     * @throws StorageException If there is an error parsing the data file.
     */
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
            throw new StorageException(String.format(MESSAGE_DATA_FILE_PARSE_ERROR, e.getMessage()));
        }
    }

    /**
     * Parses task data as a task list.
     *
     * @param taskDataStrings Task data to be parsed.
     * @return Parsed task list.
     * @throws DukeException If there is an error parsing the data.
     */
    private TaskList parseData(List<String> taskDataStrings) throws DukeException {
        final TaskList tasks = new TaskList();
        for (String taskString : taskDataStrings) {
            tasks.addTask(decodeTaskFromString(taskString));
        }
        return tasks;
    }

    /**
     * Parses task string as a task.
     *
     * @param taskString Task string to be parsed.
     * @return Parsed task.
     * @throws DukeException If there is an error parsing the task string (i.e. not in the right format).
     */
    private Task decodeTaskFromString(String taskString) throws DukeException {
        final Matcher basicTaskMatcher = BASIC_TASK_FORMAT.matcher(taskString);
        if (!basicTaskMatcher.matches()) {
            throw new DukeException(MESSAGE_TASK_FORMAT_ERROR);
        }
        final String taskTypeIcon = basicTaskMatcher.group(CAPTURING_GROUP_TASK_TYPE_ICON);
        final String isDone = basicTaskMatcher.group(CAPTURING_GROUP_IS_DONE);
        final String taskDetails = basicTaskMatcher.group(CAPTURING_GROUP_TASK_DETAILS);

        final Matcher taskDetailsMatcher = Parser.TASK_ARGS_FORMAT.matcher(taskDetails);
        if (!taskDetailsMatcher.matches()) {
            throw new DukeException(MESSAGE_TASK_FORMAT_ERROR);
        }
        final String description = taskDetailsMatcher.group(Parser.CAPTURING_GROUP_DESCRIPTION);

        Task task;

        switch (taskTypeIcon) {
        case ToDo.TASK_TYPE_ICON:
            task = new ToDo(description);
            break;
        case Event.TASK_TYPE_ICON:
            final String at = taskDetailsMatcher.group(Parser.CAPTURING_GROUP_AT);
            if (description == null || description.isBlank() || at == null || at.isBlank()) {
                throw new DukeException(MESSAGE_UNRECOGNISED_EVENT_FORMAT);
            }
            task = new Event(description, Parser.parseDateTime(at.trim()));
            break;
        case Deadline.TASK_TYPE_ICON:
            final String by = taskDetailsMatcher.group(Parser.CAPTURING_GROUP_BY);
            if (description == null || description.isBlank() || by == null || by.isBlank()) {
                throw new DukeException(MESSAGE_UNRECOGNISED_DEADLINE_FORMAT);
            }
            task = new Deadline(description, Parser.parseDateTime(by.trim()));
            break;
        default:
            throw new DukeException(String.format(MESSAGE_UNRECOGNISED_TASK_TYPE_ICON, taskTypeIcon));
        }
        if (isDone.equals(SYMBOL_TASK_DONE)) {
            task.setAsDone();
        } else if (!isDone.equals(SYMBOL_TASK_NOT_DONE)) {
            throw new DukeException(String.format(MESSAGE_UNRECOGNISED_TASK_STATUS_ICON, isDone));
        }
        return task;
    }

    /**
     * Saves task data into the data file.
     *
     * @param tasks Task list to be saved.
     * @throws StorageException If there is an error accessing the data file.
     */
    public void saveData(TaskList tasks) throws StorageException {
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, formatTasksAsDataOutput(tasks));
        } catch (IOException e) {
            throw new StorageException(String.format(MESSAGE_DATA_FILE_ACCESS_ERROR, path, e.getMessage()));
        }
    }

    /**
     * Formats a task list as per how it should be saved in the data file.
     *
     * @param taskList Task list to be formatted.
     * @return Formatted task list.
     */
    private List<String> formatTasksAsDataOutput(TaskList taskList) {
        final ArrayList<Task> tasks = taskList.getAllTasks();
        return tasks.stream()
                .map(this::formatTaskAsDataOutput)
                .collect(Collectors.toList());
    }

    /**
     * Formats a task as per how it should be saved in the data file.
     *
     * @param task Task to be formatted.
     * @return Formatted task string.
     */
    private String formatTaskAsDataOutput(Task task) {
        final StringBuilder taskStringBuilder = new StringBuilder();
        taskStringBuilder.append(task.getTaskTypeIcon()).append(" ");
        taskStringBuilder.append(task.isDone() ? SYMBOL_TASK_DONE : SYMBOL_TASK_NOT_DONE).append(" ");
        taskStringBuilder.append(task.getDescription()).append(" ");
        if (task instanceof Deadline) {
            final String deadline = ((Deadline) task).getBy().format(Parser.DATE_TIME_INPUT_FORMATTER);
            taskStringBuilder.append(Parser.DELIMITER_BY).append(" ").append(deadline);
        } else if (task instanceof Event) {
            final String eventDateTime = ((Event) task).getAt().format(Parser.DATE_TIME_INPUT_FORMATTER);
            taskStringBuilder.append(Parser.DELIMITER_AT).append(" ").append(eventDateTime);
        }
        return taskStringBuilder.toString();
    }

    /** Signals that some error occurred while trying to load or store the data file. */
    public static class StorageException extends Exception {
        public StorageException(String message) {
            super(message);
        }
    }
}

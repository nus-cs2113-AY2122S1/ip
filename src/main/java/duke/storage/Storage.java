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
    private static final Path DATA_DIRECTORY_PATH = Paths.get("data");
    private static final String DATA_FILE_NAME = "duke.txt";
    private static final Path DATA_FILE_PATH = DATA_DIRECTORY_PATH.resolve(DATA_FILE_NAME);

    private static final String CAPTURING_GROUP_TASK_TYPE_ICON = "taskTypeIcon";
    private static final String CAPTURING_GROUP_IS_DONE = "isDone";
    private static final String CAPTURING_GROUP_TASK_DETAILS = "taskDetails";
    private static final Pattern BASIC_TASK_FORMAT = Pattern.compile(
            "(?<" + CAPTURING_GROUP_TASK_TYPE_ICON + ">\\S+) "
                    + "(?<" + CAPTURING_GROUP_IS_DONE + ">\\S+) "
                    + "(?<" + CAPTURING_GROUP_TASK_DETAILS + ">.+)");

    private static final String SYMBOL_TASK_DONE = "1";
    private static final String SYMBOL_TASK_NOT_DONE = "0";

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

    private TaskList parseData(List<String> taskDataStrings) throws DukeException {
        final TaskList tasks = new TaskList();
        for (String taskString : taskDataStrings) {
            tasks.addTask(decodeTaskFromString(taskString));
        }
        return tasks;
    }

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

    public void saveData(TaskList tasks) throws StorageException {
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, formatTasksAsDataOutput(tasks));
        } catch (IOException e) {
            throw new StorageException(MESSAGE_DATA_FILE_ACCESS_ERROR + path + "\n" + e.getMessage());
        }
    }

    private List<String> formatTasksAsDataOutput(TaskList taskList) {
        final ArrayList<Task> tasks = taskList.getAllTasks();
        return tasks.stream()
                .map(this::formatTaskAsDataOutput)
                .collect(Collectors.toList());
    }

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

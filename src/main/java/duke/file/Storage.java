package duke.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.TaskList;
import duke.exception.TaskException;

/**
 * The Storage class deals with file-related operations.
 */
public class Storage {

    /* List of Task type icons */
    private static final String TODO_ICON = "T";
    private static final String DEADLINE_ICON = "D";
    private static final String EVENT_ICON = "E";

    /* Constants used for parsing */
    private static final int FIELD_COUNT = 4;
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_STATUS_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;
    private static final int TASK_OTHERS_INDEX = 3;
    private static final String DELIMITER = "|";
    private static final String ESCAPED_DELIMITER = "\\|";
    private static final String COMPLETE_NUMBER_STATUS = "1";
    private static final String INCOMPLETE_NUMBER_STATUS = "0";
    private static final String NEW_LINE = "\n";

    /* Name of directory where files are saved to */
    private String dataDirPath;

    /**
     * Instantiates a new file storage handler.
     */
    public Storage(String dataDir) {
        this.dataDirPath = dataDir;
    }

    /**
     * Overwrites the file at the given file path with the contents of the contents of the given task manager.
     *
     * @param taskManager Task Manager to write to the file.
     * @param filePath    Path of the file to write to.
     * @throws IOException If a file-related operation has errors.
     */
    public void writeTaskManagerToFile(TaskList taskManager, String filePath) throws IOException {
        File dataDir = new File(dataDirPath);
        dataDir.mkdir();

        FileWriter writer = new FileWriter(new File(dataDirPath, filePath));
        ArrayList<Task> taskList = taskManager.getTaskList();

        for (Task task : taskList) {
            try {
                writer.write(convertTaskToFileString(task) + NEW_LINE);
            } catch (TaskException err) {
                // if unable to stringify the task, skip it.
            }
        }

        writer.close();
    }

    /**
     * Returns the task manager parsed from the given file.
     *
     * @param filePath File to parse from.
     * @return Task Manager that is extracted from the given file.
     * @throws IOException If a file-related operation has errors.
     */
    public TaskList readTaskManagerFromFile(String filePath) throws IOException {
        File file = new File(dataDirPath, filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(String.format("%s does not exist", filePath));
        }
        if (!file.isFile()) {
            throw new IOException(String.format("%s is not a file", filePath));
        }

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            try {
                String line = in.nextLine();
                Task newTask = convertFileStringToTask(line);
                taskList.add(newTask);
            } catch (TaskException err) {
                // if unable to parse the line, skip it.
            }
        }

        in.close();
        return new TaskList(taskList);
    }

    /**
     * Returns the string representation of a given task for saving into a file.
     *
     * @param task Task object to represent as a string.
     * @return The string representation.
     * @throws TaskException If given task is of unsupported task type.
     */
    private static String convertTaskToFileString(Task task) throws TaskException {
        Todo todo;
        Deadline deadline;
        Event event;
        String output;
        String numberStatus = task.isCompleted() ? COMPLETE_NUMBER_STATUS : INCOMPLETE_NUMBER_STATUS;

        switch (task.getTaskIcon()) {
        case TODO_ICON:
            todo = (Todo) task;
            output = String.join(DELIMITER, todo.getTaskIcon(), numberStatus, todo.getDescription(), "");
            break;
        case DEADLINE_ICON:
            deadline = (Deadline) task;
            output = String.join(DELIMITER, deadline.getTaskIcon(), numberStatus, deadline.getDescription(),
                    deadline.getDateTime());
            break;
        case EVENT_ICON:
            event = (Event) task;
            output = String.join(DELIMITER, event.getTaskIcon(), numberStatus, event.getDescription(),
                    event.getDateTime());
            break;
        default:
            throw new TaskException("Unknown task");
        }
        return output;
    }

    /**
     * Returns the task parsed from the given string.
     *
     * @param inputLine String to parse.
     * @return Task object converted from the string.
     * @throws TaskException If there is a problem parsing the given string.
     */
    public static Task convertFileStringToTask(String inputLine) throws TaskException {
        String strippedLine = inputLine.strip();
        String[] tokens = strippedLine.split(ESCAPED_DELIMITER, -1);
        if (tokens.length != FIELD_COUNT) {
            throw new TaskException("Invalid number of fields");
        }

        String type = tokens[TASK_TYPE_INDEX].strip();
        String status = tokens[TASK_STATUS_INDEX].strip();
        String description = tokens[TASK_DESCRIPTION_INDEX].strip();
        String others = tokens[TASK_OTHERS_INDEX].strip();
        Task newTask;

        switch (type) {
        case TODO_ICON:
            newTask = new Todo(description);
            break;
        case DEADLINE_ICON:
            newTask = new Deadline(description, others);
            break;
        case EVENT_ICON:
            newTask = new Event(description, others);
            break;
        default:
            throw new TaskException("Unknown task type");
        }

        switch (status) {
        case COMPLETE_NUMBER_STATUS:
            newTask.markAsDone();
            break;
        case INCOMPLETE_NUMBER_STATUS:
            break;
        default:
            throw new TaskException("Invalid status");
        }

        return newTask;
    }
}

package duke.storage;

import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;

import static duke.constants.DukeCommandStrings.DATE_TIME_INPUT_FORMAT;
import static duke.constants.DukeDataStorageConstants.DONE;
import static duke.constants.DukeDataStorageConstants.ENCODER_ATTRIBUTE_SEPARATOR;
import static duke.constants.DukeDataStorageConstants.NOT_DONE;

/**
 * Encodes {@code Task} objects in a task list into strings to be saved in storage file.
 */
public class TaskEncoder {

    /**
     * Encodes the {@code Task}s in {@code taskList} into a list of strings which can be decoded and written to the
     * storage file.
     *
     * @param task task to be encoded
     * @param fileWriter writes the string representing the decoded task to the storage file
     * @param formattedTask a formatted string which represents the encoded task
     * @throws IOException if there is an error writing to the file
     */
    public static void encodeTask(Task task, FileWriter fileWriter, StringBuilder formattedTask) throws IOException {
            appendTaskType(formattedTask, task.getTaskType());
            appendDoneStatus(formattedTask, task.isDone());
            appendTaskDescription(formattedTask, task);
            fileWriter.write(formattedTask + System.lineSeparator());
    }

    /**
     * Appends the type of task to the formatted string. A {@code Todo} is represented by "T".
     * A {@code Deadline} is represented by an "D" while an {@code Event} is represented by an "E".
     *
     * @param formattedTask a formatted string which represents the encoded task
     * @param taskType a string representing the task type
     */
    public static void appendTaskType(StringBuilder formattedTask, String taskType) {
        formattedTask.append(taskType).append(ENCODER_ATTRIBUTE_SEPARATOR);
    }

    /**
     * Appends the done status of the task to the formatted string. A done task is represented by "X" while a task not
     * done is represented by an "O".
     *
     * @param formattedTask a formatted string which represents the encoded task
     * @param isDone a boolean representing the done status of a task
     */
    public static void appendDoneStatus(StringBuilder formattedTask, boolean isDone) {
        if (isDone) {
            formattedTask.append(DONE).append(ENCODER_ATTRIBUTE_SEPARATOR);
        } else {
            formattedTask.append(NOT_DONE).append(ENCODER_ATTRIBUTE_SEPARATOR);
        }
    }

    /**
     * Appends the task description the task to the formatted string. Appends date and time in the format
     * dd-MM-yyyy HH:mm for {@code Event}s and {@code Deadline}s.
     *
     * @param formattedTask a formatted string which represents the encoded task
     * @param task {@code Task} object to provide description, date and time
     */
    public static void appendTaskDescription(StringBuilder formattedTask, Task task) {
        if (task instanceof Todo) {
            formattedTask.append(task.getDescription());
        } else if (task instanceof Deadline) {
            formattedTask.append(task.getDescription()).append(ENCODER_ATTRIBUTE_SEPARATOR);

            /* by attribute of Deadline is of LocalDateTime type, hence need to be converted to a string */
            String by = Parser.dateTimeToString(((Deadline) task).getBy(), DATE_TIME_INPUT_FORMAT);
            formattedTask.append(by);
        } else if (task instanceof Event) {
            formattedTask.append(task.getDescription()).append(ENCODER_ATTRIBUTE_SEPARATOR);

            /* when attribute of Event is of LocalDateTime type, hence need to be converted to a string */
            String when = Parser.dateTimeToString(((Event) task).getWhen(), DATE_TIME_INPUT_FORMAT);
            formattedTask.append(when);
        }
    }
}

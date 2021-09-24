package duke.datasaver;

import duke.exception.InvalidFileDataException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static duke.constants.DukeCommandStrings.DATE_TIME_INPUT_FORMAT;
import static duke.constants.DukeDataStorageConstants.DECODER_ATTRIBUTE_SEPARATOR;
import static duke.constants.DukeDataStorageConstants.DONE;
import static duke.constants.DukeDataStorageConstants.NOT_DONE;

/**
 * Decodes data in storage file into a {@code Task} object.
 */
public class TaskDecoder {
    /**
     * Decodes a task in the form of a string into a {@code Task} object.
     *
     * @param task a formatted string containing the attributes of a {@code Task} object
     * @return a decoded {@code Task} with the same attributes of those in the storage file
     * @throws InvalidFileDataException if any of the task strings contain invalid attributes or are in an invalid format
     * @throws DateTimeParseException if date and time of {@code Event}/{@code Deadline} objects are of invalid format
     */
    public static Task decodeTask(String task) throws InvalidFileDataException, DateTimeParseException {
        String[] taskAttributes = task.split(DECODER_ATTRIBUTE_SEPARATOR);
        String taskType = taskAttributes[0].trim();
        String doneStatus = taskAttributes[1].trim();

        /* An encoded {@code Todo} has 3 fields while an encoded {@code Deadline}/{@code Event} has 4 fields */
        boolean hasInvalidTaskAttributes = (taskAttributes.length < 3 || taskAttributes.length > 4);
        boolean hasInvalidDoneStatus = (!doneStatus.equals(DONE) && !doneStatus.equals(NOT_DONE));
        if (hasInvalidTaskAttributes || hasInvalidDoneStatus) {
            throw new InvalidFileDataException();
        }

        String taskDescription = taskAttributes[2].trim();
        boolean isDone = doneStatus.equals(DONE);

        Task newTask;

        switch (taskType) {
        case "T":
            newTask = new Todo(taskDescription);
            break;
        case "D":
            String deadlineDeadline = taskAttributes[3].trim();
            LocalDateTime deadlineDateTime = Parser.parseDateTime(deadlineDeadline, DATE_TIME_INPUT_FORMAT);
            newTask = new Deadline(taskDescription, deadlineDateTime);
            break;
        case "E":
            String eventTime = taskAttributes[3].trim();
            LocalDateTime eventDateTime = Parser.parseDateTime(eventTime, DATE_TIME_INPUT_FORMAT);
            newTask = new Event(taskDescription, eventDateTime);
            break;
        default:
            throw new InvalidFileDataException();
        }
        newTask.setDone(isDone);
        return newTask;
    }
}

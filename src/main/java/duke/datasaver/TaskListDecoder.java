package duke.datasaver;

import duke.exception.InvalidFileDataException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static duke.constants.DukeCommandStrings.DATE_TIME_INPUT_FORMAT;
import static duke.constants.DukeDataStorageConstants.DECODER_ATTRIBUTE_SEPARATOR;
import static duke.constants.DukeDataStorageConstants.DONE;
import static duke.constants.DukeDataStorageConstants.NOT_DONE;

public class TaskListDecoder {

    public static void decodeTask(ArrayList<Task> taskList, String task) throws InvalidFileDataException, DateTimeParseException {
        String[] taskAttributes = task.split(DECODER_ATTRIBUTE_SEPARATOR);
        String taskType = taskAttributes[0].trim();
        String doneStatus = taskAttributes[1].trim();

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

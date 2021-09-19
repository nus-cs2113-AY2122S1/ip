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

        boolean isDone = doneStatus.equals(DONE);
        switch (taskType) {
        case "T":
            String todoDescription = taskAttributes[2].trim();
            Todo newTodo = new Todo(todoDescription);
            newTodo.setDone(isDone);
            taskList.add(newTodo);
            break;
        case "D":
            String deadlineDescription = taskAttributes[2].trim();
            String deadlineDeadline = taskAttributes[3].trim();
            LocalDateTime deadlineDateTime = Parser.parseDateTime(deadlineDeadline, DATE_TIME_INPUT_FORMAT);
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDateTime);
            newDeadline.setDone(isDone);
            taskList.add(newDeadline);
            break;
        case "E":
            String eventDescription = taskAttributes[2].trim();
            String eventTime = taskAttributes[3].trim();
            LocalDateTime eventDateTime = Parser.parseDateTime(eventTime, DATE_TIME_INPUT_FORMAT);
            Event newEvent = new Event(eventDescription, eventDateTime);
            newEvent.setDone(isDone);
            taskList.add(newEvent);
            break;
        default:
            throw new InvalidFileDataException();
        }
    }
}

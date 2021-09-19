package duke.datasaver;

import duke.exception.InvalidFileDataException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

import static duke.constants.DukeDataStorageConstants.*;

public class TaskListDecoder {

    public static void decodeTask(ArrayList<Task> taskList, String task) throws InvalidFileDataException {
        String[] taskAttributes = task.split(DECODER_ATTRIBUTE_SEPARATOR);
        String taskType = taskAttributes[0].trim();
        String doneStatus = taskAttributes[1].trim();

        boolean hasInvalidTaskAttributes = (taskAttributes.length < 3 || taskAttributes.length > 4);
        boolean hasInvalidDoneStatus = (!doneStatus.equals(DONE) && !doneStatus.equals(NOT_DONE));
        if (hasInvalidTaskAttributes || hasInvalidDoneStatus) {
            throw new InvalidFileDataException();
        }

        boolean isDone = (doneStatus.equals(DONE));
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
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDeadline);
            newDeadline.setDone(isDone);
            taskList.add(newDeadline);
            break;
        case "E":
            String eventDescription = taskAttributes[2].trim();
            String eventTime = taskAttributes[3].trim();
            Event newEvent = new Event(eventDescription, eventTime);
            newEvent.setDone(isDone);
            taskList.add(newEvent);
            break;
        default:
            throw new InvalidFileDataException();
        }
    }
}

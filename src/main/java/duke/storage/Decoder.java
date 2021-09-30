package duke.storage;

import duke.data.exception.DukeException;
import duke.data.exception.InvalidDateInputException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;
import duke.data.exception.EmptyTaskException;


public class Decoder {
    /**
     * Decodes information in a stored string into task to load them at the beginning of the program.
     *
     * @return task
     */
    public static Task decoder(String s) throws DukeException {
        String[] taskSplit = s.split(" \\| ");
        if (taskSplit.length < 2) {
            throw new EmptyTaskException();
        }
        String taskType = taskSplit[0].trim();
        String completionStatus = taskSplit[1].trim();
        String task = taskSplit[2].trim();
        Task savedTask;
        switch (taskType) {
        case "T":
            savedTask = new ToDo(task);
            break;
        case "D":
            savedTask = new Deadline(task, taskSplit[3].trim());
            break;
        case "E":
            savedTask = new Event(task, taskSplit[3].trim());
            break;
        default:
            savedTask = null;
        }
        if (completionStatus.equals("1")) {
            savedTask.markAsDone();
        }
        return savedTask;

    }
}


package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Data {

    private String[] parameters;
    private static final String CORRUPTED_DATA_ERROR =
            "OH NO! Your data is corrupted, starting a new file for you...";

    private boolean isValidDateTime(String date) {
        try {
            LocalDateTime.parse(date);
        } catch (DateTimeParseException exception) {
            return false;
        }
        return true;
    }

    private boolean hasCorruptedData(String[] parameters) {
        switch (parameters[0]) {
        case "T":
            return (parameters.length < 3);
        case "D":
            // Fallthrough
        case "E":
            if (parameters.length < 4) {
                return true;
            }
            return !isValidDateTime(parameters[3]);
        default:
            return true;
        }
    }

    public Data(String ... parameters) throws DukeException {

        if (hasCorruptedData(parameters)) {
            throw new DukeException(CORRUPTED_DATA_ERROR);
        }

        this.parameters = parameters;
    }

    public Task toTask() throws DukeException {
        Task task;
        switch (parameters[0]) {
        case "T":
            task = new ToDo(parameters[2]);
            break;
        case "D":
            task = new Deadline(parameters[2], LocalDateTime.parse(parameters[3]));
            break;
        case "E":
            task = new Event(parameters[2], LocalDateTime.parse(parameters[3]));
            break;
        default:
            throw new DukeException(CORRUPTED_DATA_ERROR);
        }

        if (parameters[1].equals("1")) {
            task.setDone();
        }
        return task;
    }
}

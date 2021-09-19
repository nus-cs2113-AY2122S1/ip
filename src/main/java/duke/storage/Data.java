package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Represents the individual information of a <code>Task</code> retrieved from storage, before adding
 * them to the <code>Task</code> list.
 */
public class Data {

    private String[] parameters;
    private static final String CORRUPTED_DATA_ERROR =
            "OH NO! Your data is corrupted, starting a new file for you...";

    /**
     * <p>Checks if the data that is returned after parsing is corrupted.</p>
     * <p>For example, the array [D, 0, description, date] will return <code>false</code> while the array
     * [D, 0, description] will return <code>true</code> since <code>Deadline</code> is expected to have a
     * date.</p>
     *
     * @param parameters <code>String</code> array that is returned after the information of a <code>Task</code>
     *                   in storage is parsed
     * @return <p><code>true</code> - if data is corrupted</p>
     *         <p><code>false</code> - otherwise</p>
     */
    private boolean hasCorruptedData(String[] parameters) {
        switch (parameters[0]) {
        case "T":
            return (parameters.length < 3);
        case "D":
            // Fallthrough
        case "E":
            return (parameters.length < 4);
        default:
            return true;
        }
    }

    /**
     * Constructs a <code>Data</code> object from the information read from the storage.
     *
     * @param parameters <p>Variable argument that contains the information of a <code>Task</code></p>
     *                   <p>For <code>ToDo</code> - Entries of the array represent the <code>Task</code>
     *                   type, done status, and description.</p>
     *                   <p>For <code>Deadline, Event</code> - Entries of the array represent the <code>Task</code>
     *                   type, done status, description, and the date and time.</p>
     * @throws DukeException If information read from the storage is corrupted
     */
    public Data(String ... parameters) throws DukeException {

        if (hasCorruptedData(parameters)) {
            throw new DukeException(CORRUPTED_DATA_ERROR);
        }

        this.parameters = parameters;
    }

    /**
     * <p>Converts a <code>Data</code> object to a <code>Task</code> object, based on the <code>parameters</code>
     * used for construction.</p>
     * <p>One example of <code>parameters</code> can be [E, 1, attend wedding, 2021-09-01T19:00], where the entries
     * represent the <code>Task</code> type, done status, description, and date and time (if any).</p>
     *
     * @return <code>Task</code> object based on the <code>parameters</code>
     * @throws DukeException if data is found to be corrupted
     */
    public Task toTask() throws DukeException {
        Task task;
        switch (parameters[0]) {
        case "T":
            task = new ToDo(parameters[2]);
            break;
        case "D":
            task = new Deadline(parameters[2], parameters[3]);
            break;
        case "E":
            task = new Event(parameters[2], parameters[3]);
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

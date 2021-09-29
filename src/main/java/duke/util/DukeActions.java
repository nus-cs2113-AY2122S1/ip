package duke.util;

import duke.exception.DukeException;
import duke.task.*;

public interface DukeActions {
    void addTask(Task t);
    void markDone(int id, boolean status) throws DukeException;
    void loadData(String data);
}

package duke.util;

import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.task.Task;

public interface DukeActions {
    void addTask(Task t);
    void markDone(int id, boolean status) throws InvalidIndexException, EmptyListException;
    void loadData(String data);
}

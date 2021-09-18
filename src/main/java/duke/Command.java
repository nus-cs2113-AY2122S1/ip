package duke;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException, IOException;
}

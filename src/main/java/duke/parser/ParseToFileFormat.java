package duke.parser;

import duke.task.Task;
import duke.task.Todo;

public class ParseToFileFormat extends Parser {
    public ParseToFileFormat(Task task) {
        handleTaskFormat(task);
    }

    private void handleTaskFormat(Task task) {
        if (task instanceof Todo) {
            fileFormat = task.getTaskIcon().concat(" | ")
                    .concat(task.getStatusIcon()).concat(" | ")
                    .concat(task.getDescription()).concat(System.lineSeparator());
        } else {
            fileFormat = task.getTaskIcon().concat(" | ")
                    .concat(task.getStatusIcon()).concat(" | ")
                    .concat(task.getDescription()).concat(" | ")
                    .concat(task.getDue()).concat(System.lineSeparator());
        }
    }
}

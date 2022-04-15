package duke.parser;

import duke.task.Task;
import duke.task.Todo;

/**
 * Parse text from Task format to file format.
 */
public class ParseToFileFormat extends Parser {

    /**
     * Creates a ParseToFileFormat object.
     *
     * @param task The task to be converted.
     */
    public ParseToFileFormat(Task task) {
        handleTaskFormat(task);
    }

    /**
     * Convert the task to file format.
     *
     * @param task The task to be converted.
     */
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

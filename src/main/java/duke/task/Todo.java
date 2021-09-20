package duke.task;

import duke.parser.Parser;
import duke.exception.DukeInvalidAddTaskException;

public class Todo extends Task {

    /**
     * Constructor of todo objects by initializing a task object.
     *      
     * @param description task description from user's input.
     * @throws DukeInvalidAddTaskException if task description contains "--", which
     * is a format reserved for only when saving the task to the text file.
     */
    public Todo(String description) throws DukeInvalidAddTaskException {
        super(Parser.getDescription(description));
    }

    /**
     * Return this todo's representation as a string.
     * 
     * @return string representation.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}

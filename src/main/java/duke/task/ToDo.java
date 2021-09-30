package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the to-do task as a string to be written in a data file.
     *
     * @return Task in text format e.g. "T | 0 | [description]".
     */
    @Override
    public String toText() {
        return "T " + super.toText();
    }

    /**
     * Returns the to-do task as a string to be displayed in the terminal for the user
     * to read the task in string format.
     *
     * @return Task in string format e.g. "[T][X] [description]".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

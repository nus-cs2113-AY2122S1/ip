package duke.task;

import duke.exceptions.EmptyField;

public class Todo extends Task {
    protected static String SYMBOL = "T";

    /**
     * Convenience Todo constructor using raw values
     *
     * @param description description of the Todo
     * @throws EmptyField if description is blank
     */
    public Todo(String description) throws EmptyField {
        setDescription(description);
    }

    /**
     * Convenience Todo constructor using raw values
     *
     * @param description description of the Todo
     * @param done status of the Todo
     * @throws EmptyField if one or more param is missing or of wrong format
     */
    public Todo(String description, boolean done) throws EmptyField {
        setDescription(description);
        setStatus(done);
    }

    @Override
    public String getSYMBOL() {
        return SYMBOL;
    }

    @Override
    public String getSaveFormat() {
        return super.getSaveFormat();
    }
}

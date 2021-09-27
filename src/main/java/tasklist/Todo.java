package tasklist;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Returns additional information which is the type icon when toString
     * method is called.
     * @return the type icon followed by the usual toString method of Task
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + super.toString();
    }
}

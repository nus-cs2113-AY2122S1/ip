package tasklist;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * This method overrides the toString method in Task
     * @return the type icon followed by the usual toString method of Task
     */
    @Override
    public String toString() {
        return "[" + getType() + "]" + super.toString();
    }
}

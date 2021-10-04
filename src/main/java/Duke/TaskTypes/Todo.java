package Duke.TaskTypes;

public class Todo extends Task{

    /**
     * Constructor for todo class
     *
     * @param description deadline description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String getType() {
        return "T";
    }
}

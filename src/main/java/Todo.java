/**
 * Class to contain all TODO type tasks, inherits the TASK class.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the getType() method, returning the string "T" representing TODO tasks when called.
     *
     * @return string "T" representing TODO tasks.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Overrides the toString() method, returning the
     * type of class "T", the status of the object (done/not done) and the object's description.
     *
     * @return string "T" representing TODO tasks, the status of the object and the object's description.
     */
    @Override
    public String toString() {
        return ("[T]" + "[" + getStatusIcon() + "] " + description);
    }
}

package alfred.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getType() {
        return "T";
    }

    /**
     * This method retrieves the date specified for Todo.
     * However, as Todos have no date, it only returns an empty string.
     * @return String Empty string to be returned
     */
    public String getDate() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

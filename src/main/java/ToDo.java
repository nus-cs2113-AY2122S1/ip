public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return description;
    }

    public String getTaskType() {
        return "T";
    }

    public String getDate() {
        return "nil";
    }
}

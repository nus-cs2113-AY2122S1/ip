public class Todo extends Task {

    public Todo(String description, char taskType) {
        super(description, taskType);
    }

    @Override
    public String toString() {
        return " [T][" + getStatusIcon() + "] " + super.toString();
    }

}

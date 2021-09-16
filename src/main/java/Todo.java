public class Todo extends Task {

    public Todo(String description, String tasksToDo) {
        super(description);
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "]" + " " + description;
    }

    @Override
    public String getStoreDataString() {
        String checkDone = isDone ? "1" : "0";
        return "todo "  +  description + " | "  + checkDone + System.lineSeparator();
    }
}

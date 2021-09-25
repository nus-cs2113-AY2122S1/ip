package itachi.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDataForFind() {
        return description;
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public String getStoreDataString() {
        String checkDone = isDone ? "1" : "0";
        return "T | " + checkDone + " | " + description;
    }
}

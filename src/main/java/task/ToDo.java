package task;

public class ToDo extends Task {
    public ToDo(String desc, Boolean status) {
        super(desc, status);
    }

    @Override
    public String toString() {
        return "[t] [" + getStatus() + "] " + getDescription();
    }
}

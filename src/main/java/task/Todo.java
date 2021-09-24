package task;

import java.time.LocalDateTime;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toFileFormat() {
        return "T # " + (isDone ? "1" : "0") + " # " + description + "\n";
    }

    @Override
    public String toString() {
        return "[T] [" + getStatus() + "] " + getDescription();
    }

    @Override
    public LocalDateTime getDT() {
        return null;
    }
}
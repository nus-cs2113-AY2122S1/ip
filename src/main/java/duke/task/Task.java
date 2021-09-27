package duke.task;

import duke.Message;

public class Task {
    private boolean isDone;
    private String description;
    final Type type;

    private static final String MARK_AS_DONE_STRING = "Nice! I've marked this task as done:\n";
    private static final String TO_STRING_REGEX = "[%c][%c] %s";
    private static final String SAVE_FILE_FORMAT = "%c|%d|%s";

    Task(String description, Type type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    Task(boolean isDone, String description, Type type) {
        this.isDone = isDone;
        this.description = description;
        this.type = type;
    }

    String getDescription() {
        return description;
    }

    private char getIsDoneChar() {
        return isDone ? 'X' : ' ';
    }

    void markAsDone() {
        isDone = true;
        Message.printWithSpacers(MARK_AS_DONE_STRING + this);
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_REGEX, type.getChar(), getIsDoneChar(), description);
    }

    String getFormattedString() {
        return String.format(SAVE_FILE_FORMAT, type.getChar(), isDone ? 1 : 0, description);
    }

}

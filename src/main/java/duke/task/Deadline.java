package duke.task;

class Deadline extends TimedTask {
    private static final Type type = Type.DEADLINE;

    Deadline(String description, String dateTime) {
        super(description, dateTime, type);
    }

    Deadline(boolean isDone, String description, String dateTime) {
        super(isDone, description, dateTime, type);
    }
}

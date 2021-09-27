package duke.task;

class Event extends TimedTask {
    private static final Type type = Type.EVENT;

    Event(String description, String dateTime) {
        super(description, dateTime, type);
    }

    Event(boolean isDone, String description, String dateTime) {
        super(isDone, description, dateTime, type);
    }
}

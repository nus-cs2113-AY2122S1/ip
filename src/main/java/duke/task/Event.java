package duke.task;

public class Event extends Todo {
    String time;

    public Event(String name, String time) {
        super(name);
        setTime(time);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        String boolString = super.getIsDone() ? "X" : " ";
        return String.format("[E][%s] %s (at: %s)", boolString, super.getName(), time);
    }


}

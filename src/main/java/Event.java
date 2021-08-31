public class Event extends Todo {
    String time;

    public Event(String name, int taskNumber, String time) {
        super(name, taskNumber);
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
        return String.format("%d.[E][%s] %s (at: %s)", super.getTaskNumber(), boolString, super.getName(), time);
    }


}

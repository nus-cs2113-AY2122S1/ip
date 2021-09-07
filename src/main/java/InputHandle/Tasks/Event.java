package InputHandle.Tasks;

public class Event extends Task{
    String completeTime;

    public Event (String taskName, String completeTime, boolean isCompleted) {
        super(taskName, isCompleted);
        this.completeTime = completeTime;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + this.completeTime + ")";
    }
}

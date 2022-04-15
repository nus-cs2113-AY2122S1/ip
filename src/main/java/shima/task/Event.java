package shima.task;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    protected String period;

    public Event() {
        super();
        period = "";
    }

    public Event(String task, String period) {
        this.task = task;
        this.isDone = false;
        this.period = period;

    }

    /**
     * Getter for the class type, retrieved from the enum TaskType
     *
     * @return Returns the type of the current class, 'E' for event class type
     */
    public String getClassType() {
        return TaskType.E.toString();
    }

    /**
     * Method inherits from its parent class, returns the period in string
     *
     * @return Returns the attribute period in string
     */
    public String getTime() {
        return this.period;
    }

    /**
     * Formats the string when printing an event
     *
     * @return Returns the string with fixed format that contains the task name and the period
     */
    @Override
    public String toString() {
        return task + " (at: " + period + ")";
    }
}

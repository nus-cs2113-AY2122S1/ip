package shima.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Stores task with deadline specified
public class Deadline extends Task {
    private LocalDateTime endTime;

    public Deadline() {
        super();
        endTime = null;
    }

    public Deadline(String task, String endTime) {
        this.task = task;
        this.isDone = false;
        this.endTime = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Getter for private attribute endTime
     * @return Returns the LocalDateTime type value attribute endTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Getter for the class type, retrieved from the enum TaskType
     * @return Returns the type of the current class, 'D' for deadline
     */
    public String getClassType() {
        return TaskType.D.toString();
    }

    /**
     * Method inherits from its superclass, display the LocalDateTime in string format
     * @return Returns the LocalDateTime attribute endTime in string and fixed format
     */
    public String getTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return endTime.format(dateTimeFormatter);
    }

    /**
     * This method will prints task description and the date time in different format
     * @return Returns the deadline description with different format of date and time
     */
    @Override
    public String toString() {
        String amPm = "am";
        String hour = Integer.toString(endTime.getHour());
        if (endTime.getHour() > 12) {
            amPm = "pm";
            hour = Integer.toString(endTime.getHour() - 12);
        }
        return task + " (by: " + endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " + hour + "." +
                endTime.format(DateTimeFormatter.ofPattern("mm")) + " " + amPm + ")";
    }
}

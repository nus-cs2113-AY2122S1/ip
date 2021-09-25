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

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getClassType() {
        return TaskType.D.toString();
    }

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

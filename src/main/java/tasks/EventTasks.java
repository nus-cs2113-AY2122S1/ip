package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTasks extends Tasks {
    LocalDateTime Date;
    String strDate;

    protected EventTasks(String name, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
        this.name = name;
        this.strDate = date;
        this.Date = LocalDateTime.parse(date.substring(date.indexOf(" ") + 1), formatter);
        this.isCompleted = false;
    }

    @Override
    protected String getName() {
        return "[E][" + getComplete() + "] " + name + "(" + strDate.substring(0,strDate.indexOf(" "))
                + ": " + Date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a")) + ")";
    }

    @Override
    protected String getTaskData(){
        return "E," + isCompleted + "," + name + "," + strDate;
    }

}

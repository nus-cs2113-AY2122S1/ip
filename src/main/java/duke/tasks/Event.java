package duke.tasks;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {

    protected LocalDate localDate;
    protected String time;

    public Event(String name, String dates, String time){
        super(name);
        this.time = time;
        this.localDate = LocalDate.parse(dates);
    }

    public String getTime(){
        return this.time;
    }
    public LocalDate getDates(){
        return this.localDate;
    }

    public String toString(){
        return "[E][" + super.getStatus() + "]" + super.name + "at: "
                + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +" " + time;
    }
}

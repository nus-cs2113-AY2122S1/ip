package duke.tasks;

public class Event extends Task {

    protected String year;
    protected String month;
    protected String day;
    protected String time;

    public Event(String name, String[] dates, String time){
        super(name);
        this.year = dates[2];
        this.month = dates[1];
        this.day = dates[0];
        this.time = time;
    }

    public String getTime(){
        return this.time;
    }
    public String[] getDates(){
        String[] dates = new String[3];
        dates[0] = day;
        dates[1] = month;
        dates[2] = year;
        return dates;
    }

    public String toString(){
        return "[E][" + super.getStatus() + "]" + super.name + "at: " + day + "-" + month +"-"
                + year + " " + time;
    }
}

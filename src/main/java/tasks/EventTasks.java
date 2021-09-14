package tasks;

public class EventTasks extends Tasks {
    String Date;

    public EventTasks(String name, String date) {
        this.name = name;
        this.Date = date;
        this.isCompleted = false;
    }

    @Override
    public String getName() {
        return "[E][" + super.mark() + "] " + name + "(" + Date.substring(0,Date.indexOf(" "))
                + ": " + Date.substring(Date.indexOf(" ") + 1) + ")";
    }

}

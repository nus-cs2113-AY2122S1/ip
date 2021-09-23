package tasks;

/**
 * Represents event tasks specifically
 */
public class EventTasks extends Tasks {
    String Date;

    protected EventTasks(String name, String date) {
        this.name = name;
        this.Date = date;
        this.isCompleted = false;
    }

    @Override
    protected String getName() {
        return "[E][" + getComplete() + "] " + name + "(" + Date.substring(0,Date.indexOf(" "))
                + ": " + Date.substring(Date.indexOf(" ") + 1) + ")";
    }

    @Override
    protected String getTaskData(){
        return "E," + isCompleted + "," + name + "," + Date;
    }

}

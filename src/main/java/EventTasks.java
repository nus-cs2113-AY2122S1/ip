public class EventTasks extends Tasks {
    String Date;

    public EventTasks(String input, String date) {
        super(input);
        this.Date = date;
    }

    @Override
    public String getName() {
        return "[E][" + super.mark() + "] " + super.name + "(" + Date.substring(0,Date.indexOf(" ")) + ": " + Date.substring(Date.indexOf(" ") + 1) + ")";
    }

}




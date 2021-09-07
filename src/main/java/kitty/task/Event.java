package kitty;

public class Event extends Task{
    protected String eventDate;

    public Event(String taskName, String eventDate) {
        super(taskName);
        this.eventDate = eventDate;
    }

    // Methods
    public static void addEventTask(Task[] tasks, String line) {
        // Get Event Name
        String taskName;
        if(Parser.hasEventDate(line)) {
            taskName = Parser.getEventTaskName(line);
        } else {
            taskName = Parser.getTaskName(line);
        }

        // Get Event Date
        String EventDate;
        if (Parser.hasEventDate(line)) {
            EventDate = Parser.getEventDate(line);
        } else {
            EventDate = "No Event Date!";
        }

        // Add Event Task
        tasks[Task.totalTasksCount] = new Event(taskName, EventDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " [at: " + eventDate + "]";
    }
}

public class Event extends Task{
    private static String SYMBOL = "E";
    private String timeslot;

    public Event(String name, String timeslot) {
        super(name);
        this.timeslot = timeslot;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + timeslot + ")";
    }
}

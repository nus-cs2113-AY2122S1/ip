public class Event extends Task{
    /** The timing this event occurs at */
    private String timing;

    public Event(String name, String timing) {
        super(name);
        this.timing = timing;
        this.typeOfTask = "[E]";
    }

    public String getTiming() {
        return timing;
    }

    @Override
    public String printTask() {
        return this.getTypeOfTask() + this.getDoneStatusAsSymbol() + " "
                + this.getName() + " (" + this.getTiming() + ")";
    }
}

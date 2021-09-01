public class Event extends Task{
    public static final String AT = "at: ";
    protected String dateAndTime;
    private static final String IDENTIFIER = "E";

    public Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    public String getStatusIconAndDescription() {
        String icon = (isDone ? "X" : " ");
        return addSquareBrackets(IDENTIFIER) + addSquareBrackets(icon) + " " + description + " " + addBrackets(AT + dateAndTime);
    }
}

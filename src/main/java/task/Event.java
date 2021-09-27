package task;

/**
 * A Task Class that saves the description of the task and the period of
 * date and time in which the task needs to be done.
 */
public class Event extends Task{
    public static final String AT = "at: ";
    protected String dateAndTime;
    private static final String IDENTIFIER = "E";

    public Event(String description, String dateAndTime) {
        this.description = description;
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String getStatusIconAndDescription() {
        String icon = (isDone ? "X" : " ");
        return addSquareBrackets(IDENTIFIER) + addSquareBrackets(icon) + " " + description + " " +
                addBrackets(AT + dateAndTime);
    }

    @Override
    public String getStatusIconAndDescriptionForFile() {
        String icon = (isDone ? "1" : "0");
        return  IDENTIFIER + SEPARATOR + icon + SEPARATOR + description + SEPARATOR + dateAndTime;
    }
}

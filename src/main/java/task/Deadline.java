package task;

/**
 * A Task class which contains the description of the task and date in which
 * the task needs to be done.
 */
public class Deadline extends Task{
    public static final String BY = "by: ";
    protected String date;
    private static final String IDENTIFIER = "D";

    public Deadline(String description,String date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public String getStatusIconAndDescription() {
        String icon = (isDone ? "X" : " ");
        return addSquareBrackets(IDENTIFIER) + addSquareBrackets(icon) + " " + description + " "
                + addBrackets(BY + date);
    }

    @Override
    public String getStatusIconAndDescriptionForFile() {
        String icon = (isDone ? "1" : "0");
        return  IDENTIFIER + SEPARATOR + icon + SEPARATOR + description + SEPARATOR + date;
    }
}

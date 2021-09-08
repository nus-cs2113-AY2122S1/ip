package task;

public class Deadline extends Task{
    public static final String BY = "by: ";
    protected String date;
    private static final String IDENTIFIER = "D";

    public Deadline(String description,String date) {
        super(description);
        this.date = date;
    }

    public String getStatusIconAndDescription() {
        String icon = (isDone ? "X" : " ");
        return addSquareBrackets(IDENTIFIER) + addSquareBrackets(icon) + " " + description + " " + addBrackets(BY + date);
    }
}

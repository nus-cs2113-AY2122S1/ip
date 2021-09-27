package task;

/**
 * A Task class that saves the description of the task.
 */
public class ToDo extends Task{
    public static final String IDENTIFIER = "T";

    public ToDo(String description) {
        this.description = description;
    }

    @Override
    public String getStatusIconAndDescription() {
        String icon = (isDone ? "X" : " ");
        return addSquareBrackets(IDENTIFIER) + addSquareBrackets(icon) + " " + description;
    }

    @Override
    public String getStatusIconAndDescriptionForFile() {
        String icon = (isDone ? "1" : "0");
        return  IDENTIFIER + SEPARATOR + icon + SEPARATOR + description;
    }
}

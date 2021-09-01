public class ToDo extends Task{
    private static final String IDENTIFIER = "T";

    public ToDo(String description) {
        super(description);
    }

    public String getStatusIconAndDescription() {
        String icon = (isDone ? "X" : " ");
        return addSquareBrackets(IDENTIFIER) + addSquareBrackets(icon) + " " + description;
    }
}

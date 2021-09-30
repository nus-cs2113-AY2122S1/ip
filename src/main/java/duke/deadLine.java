package duke;/*
deadLine class formats the message base on user's input
*/
public class deadLine extends task{
    protected String by,description;

    public deadLine(String description, String by) {
        this.description=description;
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)",this.description, this.by);
    }
}
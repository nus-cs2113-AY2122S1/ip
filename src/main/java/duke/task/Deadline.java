package duke.task;

public class Deadline extends Task {
    protected String by;
    private static final String TYPE = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setBy(String by){
        this.by = by;
    }
    public String getBy() {
        String []newBy = by.split(" ",2);
        return newBy[1];
    }


    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + "(by: "+ getBy()  +")";
    }
}
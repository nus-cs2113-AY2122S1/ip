package duke;

public class Events extends Task {

    //variables
    protected  String timeAllocation;

    //constructors
    public Events(String description, String timeAllocation) {
        super(description);
        this.timeAllocation = timeAllocation;
    }

    //methods
    @Override
    public String toString() {
        return("[E]" + super.toString() + " (at: " + timeAllocation + ")");
    }


}

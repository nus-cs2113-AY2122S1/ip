

public class Events extends Task{

    protected  String timeAllocation;

    public Events(String description, String timeAllocation) {
        super(description);
        this.timeAllocation = timeAllocation;
    }

    public String toString() {
        return( "[E]" + super.toString() + " (at: " + timeAllocation + ")");
    }



}

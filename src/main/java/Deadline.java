public class Deadline extends Task{
    protected String dueDate;
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }
    /**
     * Show the deadline.
     */   
    @Override
    public String toString(){
        return "[D]"  + super.toString() + " (by:" + dueDate + ")";
    }
}
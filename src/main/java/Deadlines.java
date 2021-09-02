public class Deadlines  extends  Task{

    //variables
    protected  String by;

    //constructors
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    //methods
    @Override
    public String toString() {
        return("[D]" + super.toString() + " (by: " + by + ")");
    }

}

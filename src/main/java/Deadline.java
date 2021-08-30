public class Deadline extends Task {

    protected String by;

    public Deadline(String name, String by) {
        super(name);
        setBy(by);
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    // prints Deadline name and by to system output
    public void printTask() {
        System.out.print("[D][");

        if (super.isDone()) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }

        System.out.println("] " + super.getName() + " (by: " + getBy() + ")");
    }

}

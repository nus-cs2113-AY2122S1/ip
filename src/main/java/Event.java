public class Event extends Task {

    protected String at;

    public Event(String name, String at) throws EmptyStringException{
        super(name);
        setAt(at);
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    // prints Event name and at to system output
    public void printTask() {
        System.out.print("[E][");

        if (super.isDone()) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }

        System.out.println("] " + super.getName() + " (at: " + getAt() + ")");
    }

}

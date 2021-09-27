package duke.tasks;

public class Deadline extends Task {

    protected String by;
    /**
     * Represents a Deadline made by the user.
     *
     * @param name Description of Deadline.
     * @param by Description of when the Deadline is.
     */
    public Deadline(String name, String by){
        super(name);
        this.by = by;
    }

    public String getTime(){
        return this.by;
    }

    public String toString(){
        return "[D][" + super.getStatus() + "]" + super.name + "by: " + by;
    }
}

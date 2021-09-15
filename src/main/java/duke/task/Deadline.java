package duke.task;

public class Deadline extends Task {
    private String by;
    public Deadline(String description,String by){
        super(description);
        this.by = by;
    }

    public Deadline(String taskDescription,String by, boolean isDone){
        super (taskDescription,isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

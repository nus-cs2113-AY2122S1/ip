package duke;

public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone,String description, String by){
        super(isDone, description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String saveFormat() {
        return String.format(super.saveFormat() + "," + by);
    }

    @Override
    public String toString(){
        return super.toString() + String.format(" (%s)",this.getBy());
    }
}

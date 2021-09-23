package karen.tasklist.task;

public class Deadline extends Task{
    private String by;
    private String deadlineTask;
    private String formattedDescription;

    public Deadline(String fullTaskDescription, String by, String deadlineTask) {
        super(fullTaskDescription);
        this.by = by;
        this.deadlineTask = deadlineTask;
        this.formattedDescription = getFormattedFileDescription();
    }

    public String getType() {
        return "Deadline";
    }

    public String getBy() {
        return by;
    }

    // get formatted description to print out for list and task commands, eg "return book (by: Sunday)"
    public String getFormattedDescription() {
        return String.format("%s (by: %s)", this.deadlineTask, this.by);
    }

    public String getFormattedFileDescription() {
        return String.format("Deadline,%s,%s,%s",getStatusIcon(), deadlineTask, this.by);
    }


    //obtain the task to do from the input description
    public String getTask() {
        return deadlineTask;
    }
}

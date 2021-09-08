package task;

public class Deadline extends Task{
    private String by;

    public Deadline(String fullTaskDescription) {
        super(fullTaskDescription);
        this.by = getBy();
    }

    public String getType() {
        return "Deadline";
    }

    public String getBy() {
        String[] descriptionWords = fullTaskDescription.split("by");
        String by = descriptionWords[1];
        return by;
    }

    // get formatted description to print out for list and task commands, eg "return book (by: Sunday)"
    public String getFormattedDescription() {
        return String.format("%s(by:%s)", getTask(),this.by);
    }


    //obtain the task to do from the input description
    public String getTask() {
        int endIndex = this.fullTaskDescription.indexOf("by") - 1;
        String task = this.fullTaskDescription.substring(0, endIndex);
        return task;
    }
}

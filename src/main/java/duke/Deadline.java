package duke;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, int index, String deadline) {
        super(description, index);
        this.type = "D";
        this.deadline = deadline;
    }
    public void printTask(){
        System.out.println(index + ". [" + type + "] [" + getStatusIcon()
                + "] " + description + " (by: " + deadline + ")");
    }

    public String fileFormat() {
        return (type + " | " + getStatusIcon() + " | " + description + " | " + deadline);
    }
}

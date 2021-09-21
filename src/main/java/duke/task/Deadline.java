package duke.task;

public class Deadline extends Task{
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        if (description.contains("|")) {
            description = description.replace("|", ",");
        }
        return "[D]" + getStatusIcon() + description + " (by:" + deadline + ")";
    }

    @Override
    public String convertToCSV() {
        if (description.contains(",")) {
            description = description.replace(",", "|");
        }
        return ("D," + getStatusIcon() + "," + description + "," + deadline);
    }
}

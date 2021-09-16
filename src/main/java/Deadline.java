public class Deadline extends Task {

    public String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }

    @Override
    public String getStoredDataString() {
        String checkDone = isDone ? "1" : "0";
        return "deadline " + description + " /by " + by + " | " + checkDone + System.lineSeparator();
    }

}
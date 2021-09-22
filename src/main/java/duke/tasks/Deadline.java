package duke.tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                (isDate(by)? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : by ) + ")";
    }

    private boolean isDate(String args) {
        if (!args.contains("-")) {
            return false;
        } else {
            String[] dateArray = args.split("-");
            if (dateArray.length < 3) {
                return false;
            }
            if (!(dateArray[0].length() == 2 && dateArray[1].length() == 2 && dateArray[2].length() == 4)) {
                return false;
            }
        }
        return true;
    }
}

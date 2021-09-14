package duke.task;

public class ToDos extends Task{

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (description.contains("|")) {
            description = description.replace("|", ",");
        }
        return "[T]" + getStatusIcon() + description;
    }

    @Override
    public String convertToCSV() {
        if (description.contains(",")) {
            description = description.replace(",", "|");
        }
        return ("T," + getStatusIcon() + "," + description);
    }
}

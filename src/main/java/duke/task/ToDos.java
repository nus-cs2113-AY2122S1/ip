package duke.task;

/**
 * Class representing Todo task.
 */
public class ToDos extends Task{

    /**
     * Constructor for Todo task initialises the description for the todo task
     * @param description Todo task description
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Converts the current Todo task into a String
     * @return Todo task in the form of a String
     */
    @Override
    public String toString() {
        if (description.contains("|")) {
            description = description.replace("|", ",");
        }
        return "[T]" + getStatusIcon() + description;
    }

    /**
     * Converts the current Todo task object into a format that can be stored in a CSV file.
     * @return Todo task in the form of a comma separated value
     */
    @Override
    public String convertToCSV() {
        if (description.contains(",")) {
            description = description.replace(",", "|");
        }
        return ("T," + getStatusIcon() + "," + description);
    }
}

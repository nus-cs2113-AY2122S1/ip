package duke.task;

public class Event extends Task {

    /**
     * Constructor that initialises Event type tasks.
     * Splits the description into its descriptive fields before storing into variables within the Task object.
     *
     * @param description User's input in the Command Line.
     */
    public Event(String description) {
        super(description);
        String trimString = trimUserInput(description);
        this.date = extractDate(trimString);
        this.specificDescription = extractDescription(trimString);
        this.description = String.format("%s (at: %s)", extractDescription(trimString), extractDate(trimString));
        taskType = "E";
    }

    /**
     * Returns event date from user's input description, splitting the description with placeholder '/at'.
     * Deadline is after the task description (second element in array).
     *
     * @param description Description of task after removing command key word.
     * @return Task description extracted from description.
     */
    private String extractDate(String description) {
        String[] splitByRegex = description.trim().split("/at", 2);
        String date = splitByRegex[1];
        return date.trim();
    }

    /**
     * Returns event date from user's input description, splitting the description with placeholder '/at'.
     * Description is before the event date (first element in array).
     *
     * @param description Description of task after removing command key word.
     * @return Event date extracted from description.
     */
    private String extractDescription(String description) {
        String[] splitByRegex = description.trim().split("/at", 2);
        String newDescription = splitByRegex[0];
        return newDescription.trim();
    }
}

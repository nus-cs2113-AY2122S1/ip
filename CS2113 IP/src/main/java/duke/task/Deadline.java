package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /**
     * Constructor that initialises Deadline type tasks.
     * Splits the description into its descriptive fields before storing into variables within the Task object.
     * Formats the deadline before storing it into the description.
     *
     * @param description User's input in the Command Line.
     */
    public Deadline(String description) {
        super(description);
        String trimString = trimUserInput(description);
        String unformattedDeadline = extractDeadline(trimString);
        String formattedDeadline = formatDeadline(unformattedDeadline);
        this.deadline = unformattedDeadline;
        this.specificDescription = extractDescription(trimString);
        this.description = String.format("%s (by: %s)", extractDescription(trimString), formattedDeadline);
        taskType = "D";
    }

    /**
     * Returns deadline from user's input description, splitting the description with placeholder '/by'.
     * Deadline is after the task description (second element in array).
     *
     * @param description Description of task after removing command key word.
     * @return Deadline extracted from description.
     */
    private String extractDeadline(String description) {
        String[] splitByRegex = description.trim().split("/by", 2);
        String deadline = splitByRegex[1];
        return deadline.trim();
    }

    /**
     * Returns deadline from user's input description, splitting the description with placeholder '/by'.
     * Task description is before the deadline (first element in array).
     *
     * @param description Description of task after removing command key word.
     * @return Task description extracted from description.
     */
    private String extractDescription(String description) {
        String[] splitByRegex = description.trim().split("/by", 2);
        String newDescription = splitByRegex[0];
        return newDescription.trim();
    }

    /**
     * Returns a formatted string for the deadline to be included in the description to be printed for the user.
     * Parses the unformatted deadline with the format d/M/yyyy HHmm (e.g. 25/9/2021 1510)
     * Reformats the deadline to give the formatted string with the format, MMM dd yyyy HH:mm.
     * (e.g. Sep 25 15:10)
     *
     * @param unformattedDeadline Deadline that is pre-formatted.
     * @return Formatted deadline string.
     */
    private String formatDeadline(String unformattedDeadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime deadlineDateTime = LocalDateTime.parse(unformattedDeadline, formatter);
        String formattedDeadline = deadlineDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return formattedDeadline;
    }
}

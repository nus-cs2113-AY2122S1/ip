package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(String description, int index) {
        super(description, index);
        String trimString = trimUserInput(description);
        String unformattedDeadline = extractDeadline(trimString);
        String formattedDeadline = formatDeadline(unformattedDeadline);
        this.deadline = unformattedDeadline;
        this.specificDescription = extractDescription(trimString);
        this.description = String.format("%s (by: %s)", extractDescription(trimString), formattedDeadline);
        taskType = "D";
    }

    private String trimUserInput(String description) {
        String[] splitStringBySpace = description.trim().split("\\s+", 2);
        String trimString = splitStringBySpace[1];
        return trimString;
    }

    private String extractDeadline(String description) {
        String[] splitByRegex = description.trim().split("/by", 2);
        String deadline = splitByRegex[1];
        return deadline.trim();
    }

    private String extractDescription(String description) {
        String[] splitByRegex = description.trim().split("/by", 2);
        String newDescription = splitByRegex[0];
        return newDescription.trim();
    }

    private String formatDeadline(String unformattedDeadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime deadlineDateTime = LocalDateTime.parse(unformattedDeadline, formatter);
        String formattedDeadline = deadlineDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return formattedDeadline;
    }
}

package duke.task;

public class Deadline extends Task {

    public Deadline(String description, int index) {
        super(description, index);
        String trimString = trimUserInput(description);
        this.deadline = extractDeadline(trimString);
        this.specificDescription = extractDescription(trimString);
        this.description = String.format("%s (by: %s)", extractDescription(trimString), extractDeadline(trimString));
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
}

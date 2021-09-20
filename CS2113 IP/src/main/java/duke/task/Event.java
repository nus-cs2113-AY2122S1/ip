package duke.task;

public class Event extends Task {

    public Event(String description, int index) {
        super(description, index);
        String trimString = trimUserInput(description);
        this.date = extractDate(trimString);
        this.specificDescription = extractDescription(trimString);
        this.description = String.format("%s (at: %s)", extractDescription(trimString), extractDate(trimString));
        taskType = "E";
    }

    private String trimUserInput(String description) {
        String[] splitStringBySpace = description.trim().split("\\s+", 2);
        String trimString = splitStringBySpace[1];
        return trimString;
    }

    private String extractDate(String description) {
        String[] splitByRegex = description.trim().split("/at", 2);
        String date = splitByRegex[1];
        return date.trim();
    }

    private String extractDescription(String description) {
        String[] splitByRegex = description.trim().split("/at", 2);
        String newDescription = splitByRegex[0];
        return newDescription.trim();
    }
}

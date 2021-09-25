package duke.task;

public class Task {
    public String deadline;
    public String date;
    public String description;
    public String specificDescription;
    public String taskType = "";
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns description of task within user's input after splitting user's input string by spaces.
     *
     * @param description User's input in the Command Line.
     * @return Description of task within user's input.
     */
    protected String trimUserInput(String description) {
        String[] splitStringBySpace = description.trim().split("\\s+", 2);
        String trimString = splitStringBySpace[1];
        return trimString;
    }

}

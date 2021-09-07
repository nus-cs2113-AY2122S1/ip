package kitty;

public class Deadline extends Task{
    protected String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public static void addDeadlineTask(Task[] tasks, String line) {
        // Get Deadline Name
        String taskName;
        if (Parser.hasDeadline(line)) {
            taskName = Parser.getDeadlineTaskName(line);
        } else {
            taskName = Parser.getTaskName(line);
        }

        // Get Deadline Date
        String deadline;
        if (Parser.hasDeadline(line)) {
            deadline = Parser.getDeadlineDate(line);
        } else {
            deadline = "No Deadline!";
        }

        // Add Deadline Task
        tasks[Task.totalTasksCount] = new Deadline(taskName, deadline);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " [by: " + deadline + "]";
    }
}

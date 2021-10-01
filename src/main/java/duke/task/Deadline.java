package duke.task;

public class Deadline extends Todo {
    String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        setDeadline(deadline);
    }


    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String boolString = super.getIsDone() ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", boolString, super.getName(), deadline);
    }
}

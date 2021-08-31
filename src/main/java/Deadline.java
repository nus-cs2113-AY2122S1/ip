public class Deadline extends Todo {
    String deadline;

    public Deadline(String name, int taskNumber, String deadline) {
        super(name, taskNumber);
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
        return String.format("%d.[D][%s] %s (by: %s)", super.getTaskNumber(), boolString, super.getName(), deadline);
    }
}

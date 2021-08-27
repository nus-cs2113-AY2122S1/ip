public class Deadline extends Task{
    /**
     * Represents the date this task should be done by
     */
    private String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
        this.typeOfTask = "[D]";
    }

    public String getDueDate() {
        return dueDate;
    }

    public String printTask() {
        return this.getTypeOfTask() + this.getDoneStatusAsSymbol() + " "
                + this.getName() + " (" + this.getDueDate() + ")";
    }
}

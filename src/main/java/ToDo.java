public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }
    public void printAddingStatus(int numberOfTasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Got it. I've added this task:\n" +
                "      " + this.toString() + "\n" +
                "    " + "Now you have " + (numberOfTasks + 1) + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
public class Deadline extends Task {

    private String name; //name of task only (eg return book)
    protected String by; //due date

    public Deadline(String name, String by) {
        description = name + " (by: " + by + ")"; //name + due date
        this.by = by;
        type = "D";
        this.name = name;
    }

    @Override
    public void printTaskNotif() {
        System.out.println("Got it. I've added this task:");
        System.out.println("[D] [ ] " + description);

    }
}

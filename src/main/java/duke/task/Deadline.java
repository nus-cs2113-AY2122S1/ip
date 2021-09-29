package duke.task;

public class Deadline extends Task {

    /*ATTRIBUTES*/

    private String name; //name of duke.task only (eg return book)
    protected String by; //due date

    /*CONSTRUCTOR*/

    public Deadline(String name, String by) {
        description = name + " (by: " + by + ")"; //name + due date
        this.by = by;
        type = "D";
        this.name = name;
    }

    /*METHODS*/

    @Override
    public void printTaskDisplay() {
        System.out.println("Got it. I've added this duke.task:");
        System.out.println("[D] [ ] " + description);

    }
}

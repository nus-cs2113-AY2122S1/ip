public class Deadline extends Task {

    /*ATTRIBUTES*/

    private String name; //name of task only (eg return book)
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
    public void printTaskNotif() {
        System.out.println("Got it. I've added this task:");
        System.out.println("[D] [ ] " + description);

    }
}

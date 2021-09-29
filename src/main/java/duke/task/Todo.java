package duke.task;

public class Todo extends Task {

    /*CONSTRUCTOR*/

    public Todo(String description) {
        super(description);
        type = "T";
    }

    /*METHODS*/

    @Override
    public void printTaskDisplay() {
        System.out.println("Got it. I've added this duke.task:");
        System.out.println("[T] [ ] " + description);
    }
}

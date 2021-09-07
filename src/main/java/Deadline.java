public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
        this.description = this.description.replace("[]", "[D]");
        this.isDone = false;
    }
}

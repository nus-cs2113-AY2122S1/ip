public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.description = this.description.replace("[]", "[T]");
        this.isDone = false;
    }

}

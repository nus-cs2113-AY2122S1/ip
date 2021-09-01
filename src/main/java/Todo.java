public class Todo extends Task{
    protected String type = "T";
    public Todo(String description){
        super(description);
    }

    public String toString() {
        return "[" + this.type + "]" + "[" + this.done + "] " + this.description;
    }
}

public class Todo extends Task {
    protected boolean isDone;

    //Constructor of todo object
    public Todo(String description) {
        super(description);
        isDone = false;
    }

    //Setter of isDone var
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    //Getter of isDone var
    public boolean isDone() {
        return this.isDone;
    }

    //toString method
    public String toString() {
        return "[T]" + super.toString();
    }
}
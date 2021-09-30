public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        isDone = false;
    }   //Constructor of todo object

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }   //Setter of isDone var

    public boolean isDone() {
        return this.isDone;
    }   //Getter of isDone var

    public String toString() {
        return "[T]" + super.toString();
    }   //toString method
}
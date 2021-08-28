public class Todo extends Task implements Timetable {

    public Todo(String description) {
        super(description);
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String getDescription() {
        return "[T]" + super.toString();
    }

    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }

}
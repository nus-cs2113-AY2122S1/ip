public class Todo extends Task implements Timetable {

    public Todo(String description) {
        super(description);
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String getDescription() {
        return super.description;
    }

    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }

}
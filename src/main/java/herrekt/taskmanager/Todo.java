package herrekt.taskmanager;

public class Todo extends Task implements Timetable {

    public Todo(String description) {
        super(description);
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toSave() {
        String spacer = " / ";
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "T" + spacer + done + spacer + this.description;
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
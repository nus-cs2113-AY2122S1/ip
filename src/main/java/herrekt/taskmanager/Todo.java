package herrekt.taskmanager;

public class Todo extends Task implements Timetable {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSave() {

        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "T" + SAVE_FILE_SPACER
                + done + SAVE_FILE_SPACER
                + this.description;
    }

    public String getDescription() {
        return super.description;
    }

    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }

}
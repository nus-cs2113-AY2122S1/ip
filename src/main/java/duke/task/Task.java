package duke.task;

public class Task {
    protected final String description;
    protected boolean isDone;
    protected char type = 'T';

    public Task(String name) {
        this.description = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.description = name;
        this.isDone = isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    /**
     * Formats the data in the required format to be written into text file
     * @return a string containing all the details of the task in required format
     */
    public String formatData() {
        return type + "|" + isDone + "|" + description;
    }

    public String getDataForFind() {
        return this.description;
    }

    @Override
    public String toString() {
        String done = isDone ? "X" : " " ;
        return "[" + type + "]"+ "["+ done +"] " + description;
    }
}

package duke.task;
public class Todo {
    private String name;
    private boolean isDone = false;

    public Todo(String name) {
        setName(name);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsDone() {

        return isDone;
    }

    public void setIsDone() {

        isDone = true;
    }

    public String toString() {
        String boolString = isDone ? "X" : " ";
        return String.format("[T][%s] %s", boolString, name);
    }

}

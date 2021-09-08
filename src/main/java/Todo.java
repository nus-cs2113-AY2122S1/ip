public class Todo {
    private String name;
    private boolean isDone = false;
    private int taskNumber;

    public Todo(String name, int taskNumber) {
        setName(name);
        setTaskNumber(taskNumber);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getTaskNumber() {

        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {

        this.taskNumber = taskNumber;
    }

    public boolean getIsDone() {

        return isDone;
    }

    public void setIsDone() {

        isDone = true;
    }

    public String toString() {
        String boolString = isDone ? "X" : " ";
        return String.format("%d.[T][%s] %s", taskNumber, boolString, name);
    }

}

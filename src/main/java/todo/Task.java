package todo;


public class Task {

    private String taskDescription;
    protected Boolean isDone;


    protected Task(String description) {
        setTaskDescription(description);
        isDone = false;
    }

    public void setDone(Boolean status) {
        this.isDone = status;
    }

    public Boolean getStatus() {
        return isDone;
    }

    private String getStatusIcon(Boolean status) {
        if (status == true) {
            return "X";
        }
        return "";
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void printLine() {
        System.out.println(getTaskDescription());
    }

    public String toString() {
        return "[" + getStatusIcon(isDone) + "] " + getTaskDescription();
    }


    public String addDate(String inputLine) {
        String[] words = inputLine.split("/");
        if (inputLine.contains("/")) {
            return words[1];
        }
        return "";

    }
}

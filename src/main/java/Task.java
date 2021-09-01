public class Task {
    protected String description;
    protected boolean isDone;

    public Task(){
        this.description = "";
        isDone = false;
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription(){
        return this.description;
    }

    public void setDone(){
        this.isDone = true;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getLetter() {
        return ' ';
    }

    public String getDate() {
        return " ";
    }
}

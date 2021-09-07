public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public boolean status() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString(){
        String done = this.status()? "X" : " ";
        String taskType = this.getClass().getName().substring(0,1).toUpperCase();
        return String.format("[%s][%s] %s",taskType, done, this.getDescription());
    }
}

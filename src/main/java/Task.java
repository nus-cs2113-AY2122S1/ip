public class Task {
    public Boolean completed;
    public String name;
    public Task(){
        this(false, null);
    }

    public Task(Boolean completed, String name) {
        this.completed = completed;
        this.name = name;
    }
}

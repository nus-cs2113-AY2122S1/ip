
public class Task {
    private String name;
    private int status;

    public Task(String name, int status){
        this.name = name;
        this.status = status;
    }

    public void updateStatus(int newStatus) {
        status = newStatus;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }
}
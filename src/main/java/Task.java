import java.util.List;

public class Task {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public Task() {
        this("Unnamed");
    }

    public String getTaskName() {
        return name;
    }
}

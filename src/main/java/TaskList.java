import java.io.*;
import java.util.ArrayList;

public class TaskList  {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public static ArrayList<Task> getTasksInstance() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        return tasks;
    }
}

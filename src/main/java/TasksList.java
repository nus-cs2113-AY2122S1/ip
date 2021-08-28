import java.util.ArrayList;

public class TasksList {
    protected ArrayList<Task> tasks = new ArrayList<>();

    public int getSize() { return tasks.size(); }

    public String getTaskString(int taskIndex) {
        Task task = tasks.get(taskIndex);
        return task.toString();
    }

    public void addTask(String taskType, String description, String additionalInfo) {
        switch(taskType) {
        case "todo":
            tasks.add(new ToDo(description));
            break;
        case "deadline":
            tasks.add(new Deadline(description, additionalInfo));
            break;
        case "event":
            tasks.add(new Event(description, additionalInfo));
            break;
        default:
            System.out.println("ERROR, INVALID TASK TYPE");
            break;
        }
    }

    public Task markTaskAsDone(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        return task;
    }

    public void printTasks() {
        for (int i=0; i<tasks.size(); i++) {
            System.out.println("\t" + (i+1) + "." + getTaskString(i));
        }
    }
}
import java.util.ArrayList;

public class TaskList {

    public void addTodo(ArrayList<Task> Task, int count, String userInput) {
        Task.add(count, new Todo(userInput));
    }

    public void addDeadline(ArrayList<Task> Task, int count, String userInput, String date) {
        Task.add(count, new Deadline(userInput, date, false));
    }

    public void addEvent(ArrayList<Task> Task, int count, String userInput, String date) {
        Task.add(count, new Event(userInput, date));
    }

    public void removeTask(ArrayList<Task> Task, int count) {
        Task.remove(count);
    }
}

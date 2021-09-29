package task.type;
import ui.UI;
import exception.DukeException;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public void addTaskToList(Task task) {
        taskList.add(task);
        UI.printAddTaskMessage(taskList);
    }

    public void deleteTaskFromList(int index) {
        String deletedTask = taskList.get(index - 1).toString();
        taskList.remove(index - 1);
        int size = taskList.size();
        UI.printDeleteTaskMessage(deletedTask, size);
    }

    public void markTaskAsDone(int index) throws DukeException {
        try {
            taskList.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException();
        }
        String description = taskList.get(index - 1).toString();
        UI.printDoneTaskMessage(description);
    }

    public ArrayList<Task> findTasks(String keyword){
        return taskList.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }
}

package duke;
import duke.Storage;
import duke.task.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;

class TaskList implements Serializable {
    private List<Task> tasks = new ArrayList<>();

    public void add(Task t) {
        tasks.add(t);
        Storage.saveToFile(this);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getByIndex(int index) {
        return tasks.get(index);
    }

    public Task removeByIndex(int index) {
        Task target = tasks.remove(index);
        Storage.saveToFile(this);
        return target;
    }

    public List<Task> findByKeyword(String keyword) {
        return tasks.stream()
            .filter(t -> t.getTitle().contains(keyword))
            .collect(Collectors.toList());
    }
}

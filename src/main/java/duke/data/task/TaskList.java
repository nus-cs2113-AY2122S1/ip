package duke.data.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {

    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initialises  based on a list of existing tasks.
     *
     * @param tasks The lists of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Fetch the task at index taskId
     *
     * @param taskId The ID of the task
     */
    public Task getTask(int taskId) {
        return this.tasks.get(taskId - 1);
    }

    public boolean addTask(Task task) {
        return this.tasks.add(task);
    }

    public Task deleteTask(int taskId) {
        return this.tasks.remove(taskId - 1);
    }

    public TaskList search(String keyword) {
        List<Task> filteredList = tasks.stream()
                .filter((task) -> task.containsKeyword(keyword))
                .collect(Collectors.toList());
        return new TaskList(filteredList);
    }

    public int size() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        if(this.tasks.size() == 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return IntStream.range(0, tasks.size())    // form an ordered intstream with size equals to task lists
                .mapToObj(i -> String.format("%d. %s", i + 1, tasks.get(i)))   /*format all the elements in task lists
                                                                                with form "index, task description" */
                .collect(Collectors.joining("\n")).trim();  //join all the tasks and ignore the spaces
    }

}
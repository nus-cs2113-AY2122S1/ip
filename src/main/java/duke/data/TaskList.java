package duke.data;

import duke.exception.DukeEmptyParaException;
import duke.exception.DukeException;
import duke.exception.DukeOutOfRangeException;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task>{
    // Attributes
    private final ArrayList<Task> tasks = new ArrayList<>(); // using Java Collections classes

    public TaskList(){
        // Constructs empty task list
    }

    public TaskList(Collection<Task> sourceList){
        tasks.addAll(sourceList);
    }

    public TaskList(TaskList source) {
        tasks.addAll(source.tasks);
    }

    public Task getTask(int index){
        return this.tasks.get(index);
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void deleteTask(int index){
        this.tasks.remove(index);
    }

    public void doneTask(int index){
        this.tasks.get(index).markAsDone();
    }

    public TaskList search(String keyword) {
        final List<Task> matchingTasks = new ArrayList<>();

        for(Task task : this.tasks) {
            if(task.containsKeyword(keyword)){
                matchingTasks.add(task);
            }
        }

        return new TaskList(matchingTasks);
    }

    public void printList(){
        for(int i=0; i< tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public int getSize() {
        return tasks.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}

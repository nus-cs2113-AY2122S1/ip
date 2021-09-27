package duke.tasks;
import duke.tasks.Task;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public void add(Task t){
        this.taskList.add(t);
    }

    public int size(){
        return this.taskList.size();
    }

    public Task get(int index){
        return this.taskList.get(index);
    }

    public void print(){
        for(int i = 0; i < taskList.size(); i++){
            System.out.print(i +  1);
            System.out.println(taskList.get(i));
        }
    }

    public TaskList find(String description){
        TaskList possibleTasks = new TaskList();

        for (Task task : this.taskList) {
            if (task.getName().contains(description)) {
                possibleTasks.add(task);
            }
        }
        return possibleTasks;
    }

    public void  remove(Task t){
        taskList.remove(t);
    }
}

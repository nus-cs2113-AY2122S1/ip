package tasks;
import tasks.Task;
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

    public void  remove(Task t){
        taskList.remove(t);
    }
}

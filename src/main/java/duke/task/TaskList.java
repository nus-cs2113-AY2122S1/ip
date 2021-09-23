package duke.task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> TaskList;
    public int taskIndex;

    public TaskList() {
        this.TaskList = new ArrayList<>();
        this.taskIndex = 0;
    }

    public TaskList(ArrayList<Task> arrayList) {
        this.TaskList = new ArrayList<>();
        for (Task t: arrayList) {
            add(t);
        }
        System.out.println("Num of existing tasks = " + this.taskIndex + "\n");
    }

    public void add(Task t) {
        this.TaskList.add(t);
        this.taskIndex++;
    }

    public Task get(int i) {
        return this.TaskList.get(i);
    }

    public void remove(int i) {
        this.TaskList.remove(i);
        this.taskIndex--;
    }

    public void getTaskIndex() {
        System.out.println(this.taskIndex);
    }
}

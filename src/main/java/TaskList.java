import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }


    public void addTask(Task t) {
        taskList.add(t);
    }



    public Task markDone(int doneIndex) throws IllegalDoneException {
        if(doneIndex >= taskList.size()) {
            throw new IllegalDoneException();
        } else {
            taskList.get(doneIndex).setDone();
            return taskList.get(doneIndex);
        }
    }

    public Task deleteTask(int delIndex) throws IllegalDoneException {
        if (delIndex >= taskList.size()) {
            throw new IllegalDoneException();
        } else {
            Task delTask = taskList.get(delIndex);
            taskList.remove(delIndex);
            return delTask;
        }
    }
}

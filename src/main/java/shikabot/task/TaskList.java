package shikabot.task;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * This function directly adds a task to tasks without printing any messages. Used for loading saved tasks.
     * @param type type of task.
     * @param atBy at/by of task, if applicable.
     * @param name name of task.
     * @param done if the task is done or not.
     */
    public void addSavedTask(char type, String atBy, String name, String done) {
        switch(type) {
        case 'T':
            taskList.add(new Todo(name));
            break;
        case 'D':
            taskList.add(new Deadline(name, atBy));
            break;
        case 'E':
            taskList.add(new Event(name, atBy));
            break;
        default:
            return;
        }
        if (done.equals("true")) {
            taskList.get(taskList.size() - 1).markAsDone();
        }
    }
}

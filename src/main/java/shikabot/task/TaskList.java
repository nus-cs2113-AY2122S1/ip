package shikabot.task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    /**
     * This function directly adds a task to tasks without printing any messages. Used for loading saved tasks.
     * @param type type of task.
     * @param atBy at/by of task, if applicable.
     * @param name name of task.
     * @param done if the task is done or not.
     */
    public void addSavedTask(char type, LocalDate atBy, String name, String done) {
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

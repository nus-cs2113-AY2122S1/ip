package duke;

import duke.tasks.Task;
import duke.exceptions.DukeException;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> list;

    /**
     * Constructor for TaskList which initialises an ArrayList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList which initialises an ArrayList with given size.
     * @param size TaskList size
     */
    public TaskList(int size) {
        list = new ArrayList<>(100);
    }

    /**
     * Constructor for TaskList which clones itself.
     * @param taskList a TaskList object
     */
    public TaskList(TaskList taskList) {
        list = (ArrayList<Task>) taskList.list.clone();
    }

    /**
     * Retrieves size of task list
     * @return size
     */
    public int size() {
        return list.size();
    }

    /**
     * Retrieves task with given index in task list
     * @param index index of task to be retrieved
     * @return task object with given index
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Adds a task to the task list
     * @param t task to be added
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Removes a task with given index from the task list. Exception will be thrown if given index is out of range.
     * @param index index of task to be removed
     * @return task to be removed
     * @throws DukeException
     */
    public Task remove(int index) throws DukeException {

        if (index > list.size() - 1) {
            throw new DukeException("Task number " + (list.size() + 1) + " is invalid!\nEnter a valid task number.");
        }

        return list.remove(index);
    }

    /*
    public Task remove(Task t) {
        return list.remove(list.indexOf(t));
    }*/

    /**
     * Marks a task in the task list as done. Exception will be thrown if given index is out of range.
     * @param index index of task to be marked as done
     * @throws DukeException
     */
    public void setTaskDone(int index) throws DukeException {
        if (index < 0 || index > list.size()) {
            throw new DukeException("Task number " + (index + 1) + " is invalid!\nEnter a valid task number.");
        }

        list.get(index).setDone();
    }

    /**
     * Lists all tasks in the task list. Will prompt user if task list is empty.
     * @return string of the task list
     */
    public String listTasks() {

        String text;

        if (list.size() == 0) {
            text = "Empty task list!";
        } else {
            text = "Your task list:\n";
            for (int i = 0; i < list.size(); i++) {
                text = text.concat((i + 1) + ". " + list.get(i).toString() + "\n");
            }

            // erase last newline character
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }
}

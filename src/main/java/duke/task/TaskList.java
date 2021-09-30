package duke.task;

import java.util.ArrayList;
import duke.ui.Ui;

/**
 * Represents the ArrayList of tasks
 */
public class TaskList {
    
    private ArrayList<Task> list;
    private Ui ui;

    public TaskList(Ui ui) {
        this.ui = ui;
        list = new ArrayList<Task>();
    }

    /**
     * returns the size of the ArrayList
     *
     * @return the size of the list
     */
    public int size() {
        return list.size();
    }

    /**
     * returns the ArrayList of tasks
     *
     * @return the ArrayList of tasks
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Prints the content of the ArrayList
     */
    public void printList() {
        if (list.size() == 0) {
            ui.printEmptyListMessage();
        } else {
            ui.printTasks(list);
        }
    }

    /**
     * Accesses the task at the given taskIndex as marks it as done
     *
     * @param taskIndex the task index of the task to be marked as done
     */
    public void markTaskAsDone(int taskIndex) {
        list.get(taskIndex).markAsDone();
    }


    /**
     * Deletes the task at the given task index from the ArrayList
     *
     * @param taskIndex the task index of the task to be deleted
     */
    public void deleteTask(int taskIndex) {
        list.remove(taskIndex);
    }

    /**
     * Prints the most recently added task
     */
    public void printAddedTask() {
        ui.printTaskAddedMessage(list);
    }

    /**
     * Adds a todo task to the ArrayList
     *
     * @param taskName name of the todo to be added
     */
    public void addTodo(String taskName) {
        list.add(new Todo(taskName));
    }

    /**
     * Adds a deadline task to the ArrayList
     *
     * @param taskName name of the deadline task to be added
     * @param deadline deadline of the deadline task
     */
    public void addDeadline(String taskName, String deadline) {
        list.add(new Deadline(taskName, deadline));
    }

    /**
     * Adds an event task to the ArrayList
     *
     * @param taskName name of the event task to be added
     * @param at timing of the event task
     */
    public void addEvent(String taskName, String at) {
        list.add(new Event(taskName, at));
    }
}

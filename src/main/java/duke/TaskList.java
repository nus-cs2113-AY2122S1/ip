package duke;

import java.util.ArrayList;

/**
 * Stores the array of tasks and modify its contents
 */
public class TaskList {
    protected ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds a todo
     *
     * @param description the name of the task
     * @param count the index of the task
     */
    public void addTodo (String description, int count) {
        Todo todo = new Todo(description, count);
        todo.printAdded(count);
        list.add(todo);
    }

    /**
     * Adds a event
     *
     * @param description the name of the event
     * @param count the index of the event
     * @param time the time of the event
     */
    public void addEvent (String description, String time, int count) {
        Event event = new Event(description, count, time);
        event.printAdded(count);
        list.add(event);
    }

    /**
     * Adds a deadline
     *
     * @param description the name of the deadline
     * @param count the index of the deadline
     * @param time the time of the deadline
     */
    public void addDeadline (String description, String time, int count) {
        Deadline deadline = new Deadline(description, count, time);
        deadline.printAdded(count);
        list.add(deadline);
    }

    /**
     * Deletes a task
     *
     * @param description the index of the task
     * @param count the current number of tasks
     */
    public void delete (String description, int count) {
        int index = Integer.parseInt(description) - 1;
        list.get(index).printTaskDelete(count);
        list.remove(index);
        for (int i=index; i<count; i++) {
            list.get(i).index--;
        }
    }

    /**Lists out the tasks */
    public void list () {
        if (list.isEmpty()) {
            throw new NullPointerException();
        }
        for(Task task: list){
            task.printTask();
        }
    }

    /**
     * Marks a task as done
     *
     * @param description the index of the task
     */
    public void done (String description) {
        int index = Integer.parseInt(description) - 1;
        list.get(index).printMarkAsDone();
    }

    /**
     * Searches for a task that contains a string of characters
     *
     * @param description the string of characters to find
     */
    public void find (String description) {
        if (list.isEmpty()) {
            throw new NullPointerException();
        }
        System.out.println("Here are the matching tasks in your list:");
        for(Task task: list){
            if (task.description.contains(description)) {
                task.printTask();
            }
        }
    }

}

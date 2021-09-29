package duke.util;

import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.exception.NoKeywordException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

import static duke.ui.PrintBot.print;

/*
 * A class that contains the task list and its manipulations
 * such as add, delete and list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    /*
     * Find the task with index i from the task list.
     * <p>
     * Return Error if the index is not specified or not within the range of
     * the task list.
     */
    public Task getTask(int i) throws InvalidIndexException,EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }
        if (i == -1 || i >= tasks.size()) {
            throw new InvalidIndexException();
        }

        return tasks.get(i);
    }

    public Todo addTodo(String description) {
        Todo t = new Todo(description);
        addTask(t);
        return t;
    }

    public Deadline addDeadline (String description, String deadline) throws NoKeywordException {
        try {
            Deadline d = new Deadline(description, deadline);
            addTask(d);
            return d;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoKeywordException();
        }
    }

    public Event addEvent (String description, String duration) throws NoKeywordException {
        try {
            Event e = new Event(description, duration);
            addTask(e);
            return e;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoKeywordException();
        }
    }

    /*
     * Changes the status of the task whose index is id to the boolean value
     * specified in the parameter.
     *
     * @param id the id of the task
     * @param isDone status of the task, either true or false
     * @return a task whose status has been updated.
     */
    public Task markDone(int id, boolean isDone) throws EmptyListException, InvalidIndexException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }

        int i = id -1;
        //index in string
        Task t = getTask(i);
        if (isDone) {
            t.setDone(true);
        }
        return t;
    }

    /*
     * Deletes a task in the tasks list with id.
     * Returns the particular task.
     */
    public Task delete(int id) throws InvalidIndexException, EmptyListException {
        int i = id - 1;
        Task t = getTask(i);
        tasks.remove(i);
        return t;
    }

    /*
     * Prints all the tasks there are in the task list.
     * Report Exception if there is no task in the list.
     */
    public void printList() throws EmptyListException {
        print("Here are the tasks in your list:\n");
        int id = 1;
        for (Task t : tasks) {
            System.out.println(id + ". " + t);
            id++;
        }
    }

    /*
     * Searches within the tasks list for any task with
     * description that contains the same word as specified by user.
     *
     * @param searchPhrase user's input on what the words to search/
     * @return an arraylist of string to be printed to ui, inside the list
     * are every task with description containing the search phrase.
     */
    public ArrayList<String> searchList(String searchPhrase) {
        ArrayList<String> searchResult = new ArrayList<>();
        int count = 0;
        for (Task t : tasks) {
            if (t.isContain(searchPhrase)) {
                count++;
                searchResult.add(count + "." + t.toString());
            }
        }
        return searchResult;
    }

    public int getSize() {
        return tasks.size();
    }
}

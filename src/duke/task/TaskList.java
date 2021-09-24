package duke.task;

import duke.exception.InvalidIndexException;
import ui.UI;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an instance of a TaskList to store all Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a given Task into the TaskList and prints a confirmation message to the console.
     *
     * @param newTask Task to be added into the TaskList.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        UI.printAddTask(newTask);
    }

    /**
     * Marks the task at the specified index as completed and prints a confirmation message
     * to the console.
     *
     * @param input String containing the index of the task to complete.
     * @throws InvalidIndexException when the index specified exceeds the number of tasks in
     *                               the TaskList
     */
    public void completeTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        tasks.get(index).finishTask();
        System.out.println("Completed task: " + tasks.get(index).getTaskName());
    }

    /**
     * Deletes the task at the specified index and prints a confirmation message to the console.
     *
     * @param input String containing the index of the task to complete.
     * @throws InvalidIndexException when the index specified exceeds the number of tasks in
     *                               the TaskList
     */
    public void deleteTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        System.out.println("Removed task: " + tasks.get(index).getTaskName());
        tasks.remove(index);
    }

    /**
     * Finds all tasks containing the given query and prints the list of tasks found to
     * the console.
     *
     * @param input String containing the search query.
     */
    public void findTask(String input) {
        if (input.trim().isEmpty()) {
            System.out.println("Please enter a valid input");
            return;
        }
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getTaskName().contains(input.trim())) {
                searchResults.add(t);
            }
        }
        if (searchResults.isEmpty()) {
            System.out.println("No results found");
        }
        else {
            System.out.println("Tasks containing " + input.trim() + ":");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println((i + 1) + ". " + searchResults.get(i).toString());
            }
        }
    }


    /**
     * Lists all the tasks in the TaskList with their respective index and completion status.
     */
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getStatus() + tasks.get(i).toString());
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}

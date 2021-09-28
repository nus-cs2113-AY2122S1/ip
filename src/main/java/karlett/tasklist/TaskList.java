package karlett.tasklist;

import karlett.storage.TaskListEncoder;
import karlett.task.Task;
import karlett.ui.TextUi;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private int numberOfTasks;
    private ArrayList<Task> tasks;
    private TaskListEncoder taskListEncoder;

    /**
     * Return a TaskList object from data loaded from a file.
     *
     * @param tasksArrayList An array list of tasks
     * @param filePath file path to the file from which data is loaded
     */
    public TaskList(ArrayList<Task> tasksArrayList, String filePath) {
        tasks = tasksArrayList;
        numberOfTasks = tasksArrayList.size();
        taskListEncoder = new TaskListEncoder(filePath);
    }

    /**
     * Return a TaskList object with an empty task array list.
     *
     * @param filePath to the file to which data will be stored.
     */
    public TaskList(String filePath) {
        tasks = new ArrayList<Task>();
        numberOfTasks = 0;
        taskListEncoder = new TaskListEncoder(filePath);
    }

    public TaskList() {
        tasks = new ArrayList<Task>();
        numberOfTasks = 0;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void add(Task task) throws IOException {
        tasks.add(task);
        numberOfTasks++;
    }

    /**
     * Delete a task in the array list by overwritting
     * it with tasks behind it.
     *
     * @param index index of the task counting from 1 (NOT 0)
     */
    public void remove(int index) {
        for (int i = index; i < tasks.size(); i++) {
            tasks.set(i - 1, tasks.get(i));
        }
        numberOfTasks--;
    }

    public void printList() {
        TextUi.drawDivider();
        if (numberOfTasks == 0) {
            System.out.println("You have done everything! Time to relax with Karlett meow ʕ♡ﻌ♡ʔ");
        } else {
            for (int i = 0; i < numberOfTasks; i++) {
                System.out.println("ฅ" + (i + 1) + " " + tasks.get(i));
            }
        }
        TextUi.drawDivider();
    }

    /**
     * Return a task object from the array list.
     *
     * @param i index of the task counting from 0
     * @return return the task of index i
     */
    public Task get(int i) {
        return tasks.get(i);
    }
}

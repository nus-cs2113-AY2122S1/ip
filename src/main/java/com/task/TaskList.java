package com.task;

import java.util.ArrayList;

/**
 * Represent a list of <code>Task</code> object, use <code>ArrayList<Task> list</code> to store information,
 * can add to the list and get the list.
 */
public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * Add a task item to the list.
     *
     * @param item Task object representing a task.
     */
    public void addTask(Task item) {
        list.add(item);
    }

    /**
     * Getter method, returns the list
     *
     * @return ArrayList<Task> list
     */
    public ArrayList<Task> getList() {
        return list;
    }
}

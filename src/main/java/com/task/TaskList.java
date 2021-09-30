package com.task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    public void addTask(Task item) {
        list.add(item);
    }

    public ArrayList<Task> getList() {
        return list;
    }
}

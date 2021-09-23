package duke.task;

import duke.exception.InvalidIndexException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.println("Added: " + newTask.getTaskName());
    }

    public void completeTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        tasks.get(index).finishTask();
        System.out.println("Completed task: " + tasks.get(index).getTaskName());
    }

    public void deleteTask(String input) {
        int index = Integer.parseInt(input) - 1;
        System.out.println("Removed task: " + tasks.get(index).getTaskName());
        tasks.remove(index);
    }

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

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getStatus() + tasks.get(i).toString());
        }
    }
}

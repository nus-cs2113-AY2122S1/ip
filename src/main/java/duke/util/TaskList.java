package duke.util;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void listTasks() {
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    public void markTaskDone(int index) throws DukeException {
        taskList.get(index).markComplete();
        System.out.println("Task " + taskList.get(index).getDescription() + " marked as complete.");
    }

    public void deleteTask(int index) {
        String taskStorage = taskList.get(index).toString();
        taskList.get(index).decrementTaskNumber();
        taskList.remove(index);

        System.out.println("Alright. I've deleted this task:");
        System.out.println(taskStorage);
        System.out.println("You have a total of " + Task.getTotalTasks() + " tasks now.");
    }

    public void addNewTask(Task task) throws DukeException {
        taskList.add(task);

        System.out.println("Gotcha. I've added this task:");
        System.out.println(taskList.get(Task.getTotalTasks() - 1));
        System.out.println("You have a total of " + Task.getTotalTasks() + " tasks now.");
    }
}

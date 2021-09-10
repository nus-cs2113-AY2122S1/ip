package xRoss;

import xRoss.task.Task;

import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();
    private int completedTasksCount = 0;
    private int tasksCount = 0;

    public void printTasks() {
        if (tasksCount == 0) {
            System.out.println("\tYou have no tasks in your to-do list at the moment\n");
            return;
        }
        System.out.println("\tThis is your current to-do list");
        for (int i = 0; i < tasksCount; i++) {
            System.out.print("\t" + (i + 1) + ".");
            tasks.get(i).printTask();
        }
        System.out.println("\tThere are " + tasksCount + " task(s) in your to-do list\n");
    }

    public void addTask(Task task) {
        // add new task to tasks array
        tasks.add(task);

        // print name of task to system output
        System.out.print("\tGot it! I've added this task for you:\n\t\t");
        tasks.get(tasksCount).printTask();

        // increment total tasksCount
        tasksCount++;
        System.out.println("\tThere are " + tasksCount + " task(s) in your to-do list\n");
    }

    public void markAsDone(int inputTaskIndex) {
        int taskIndex = inputTaskIndex - 1;

        // check if taskIndex is out of bounds of current tasks list
        if (taskIndex < 0 || taskIndex >= tasksCount) {
            System.out.println("\tYou have chosen an invalid task number.\n");
            printTasks();
            return;
        }

        // check if task is already marked as done
        if (tasks.get(taskIndex).isDone()) {
            System.out.println("\tThis task has already been completed and marked as done.\n");
            return;
        }

        tasks.get(taskIndex).setDone();
        completedTasksCount++;

        // Print name of task to system output
        System.out.print("\tGood job! I have marked your task as done.\n\t\t");
        tasks.get(taskIndex).printTask();
        System.out.println("\tYou have " + (tasksCount - completedTasksCount) + " uncompleted task(s) left in your to-do list\n");
    }
}

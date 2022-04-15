package herrekt.command;

import herrekt.taskmanager.Task;
import herrekt.taskmanager.TaskList;

import java.util.HashMap;

public class CommandUi {

    protected void printMarkTaskAsDone(int taskNumber, TaskList tasks) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.getTask(taskNumber - 1).toString());
    }

    protected void printTaskAlreadyMarkedAsDone(int taskNumber, TaskList tasks) {
        System.out.println("Task has already been marked as done.");
        System.out.println("Task in question: " + tasks.getTask(taskNumber - 1).toString());
    }

    protected void printTaskDeleted(int taskNumber, TaskList tasks) {
        int index = taskNumber - 1;
        System.out.println("Alright. I've removed this task: "
                + "\n" + "  " + tasks.getTask(index).toString());
        System.out.println("Now you have "
                + (tasks.getSize() - 1)
                + " tasks left to do");
    }

    protected void printMatchingTaskList(HashMap<Integer, Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("Couldn't find any matches");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (Integer taskNumber : tasks.keySet() ) {
                System.out.println(taskNumber + ". " + tasks.get(taskNumber).toString());
            }
        }
    }

    protected void printNumberOfTasks(TaskList tasks) {
        if (tasks.getSize() <= 1) {
            System.out.println("For now, you have "
                    + tasks.getSize()
                    + " task on the list");
        } else {
            System.out.println("For now, you have "
                    + tasks.getSize()
                    + " tasks on the list");
        }
    }

    protected void printTaskAdded(Task task) {
        System.out.println("Task added: " + task.toString());
    }

    protected void printCommandList() {
        System.out.println("Please refer to the README.md for the features available");
        System.out.println("Commands available: todo, deadline, event, list, done, delete, find, help, bye");
    }
}

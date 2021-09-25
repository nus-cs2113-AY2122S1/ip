package herrekt.command;

import herrekt.taskmanager.TaskList;

public class CommandUi {

    void printMarkTaskAsDone(int taskNumber, TaskList tasks) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.getTask(taskNumber - 1).toString());
    }

    void printTaskAlreadyMarkedAsDone(int taskNumber, TaskList tasks) {
        System.out.println("Task has already been marked as done.");
        System.out.println("Task in question: " + tasks.getTask(taskNumber - 1).toString());
    }

    void printTaskDeleted(int taskNumber, TaskList tasks) {
        int index = taskNumber - 1;
        System.out.println("Alright. I've removed this task: "
                + "\n" + "  " + tasks.getTask(index).toString());
        System.out.println("Now you have "
                + (tasks.getSize() - 1)
                + " tasks left to do");
    }

    void printMatchingTaskList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("Couldn't find any matches");
        } else {
            System.out.println("Here are the matching tasks in your list:" + "\n" + tasks.toString());
        }
    }

    void printNumberOfTasks(TaskList tasks) {
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
}

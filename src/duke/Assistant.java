package duke;

import duke.task.Task;

public class Assistant {
    public static final int MAX_TASKS = 100;

    private int taskIndex;
    private Task[] tasks;

    public Assistant() {
        taskIndex = 0;
        tasks = new Task[MAX_TASKS];
    }

    public void listTasks() {
        for (int i = 0; i < taskIndex; i++) {
            System.out.println((i+1) + ". " + tasks[i].getStatus() + tasks[i].toString()
            );
        }
    }

    public void addTask(Task newTask) {
        tasks[taskIndex] = newTask;
        taskIndex++;
        System.out.println("added: " + newTask.getTaskName());
    }

    public void completeTask(int index) {
        tasks[index].finishTask();
        System.out.println("Completed task: " + tasks[index].getTaskName());
    }
}

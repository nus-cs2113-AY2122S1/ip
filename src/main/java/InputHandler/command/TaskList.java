package InputHandler.command;

import InputHandler.exception.TaskEmptyException;
import InputHandler.exception.TimeMissingException;

public class TaskList {
    private Task[] tasks = new Task[100];
    private int totalTasks = 0;

    public Task addList(String userInput) throws TimeMissingException, TaskEmptyException {
        Task newTask;
        newTask = getTask(userInput);
        this.tasks[totalTasks] = newTask;
        totalTasks ++;
        return newTask;
    }

    public void listTasks() {
        for (int i = 0; i < totalTasks; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i].toString());
        }
    }

    public int getTotalTaskNumber() {
        return this.totalTasks;
    }

    public Task markAsDone(int index) throws NullPointerException{
        this.tasks[index].setCompleted();
        return this.tasks[index];
    }

    private Task getTask(String userInput) throws TaskEmptyException, TimeMissingException {
        String taskType, taskName, deadline;
        int taskTypeIndex = userInput.indexOf(" ");
        int deadlineIndex = userInput.lastIndexOf("/");

        try {
            taskType = userInput.substring(0, taskTypeIndex);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TaskEmptyException(userInput);
        }

        if (taskType.equals("todo")) {
            taskName = userInput.substring(taskTypeIndex + 1);
            return new Todo(taskName, false);
        }

        if (deadlineIndex == -1) {
            throw new TimeMissingException(taskType);
        }

        deadline = userInput.substring(deadlineIndex + 1);
        taskName = userInput.substring(taskTypeIndex, deadlineIndex);
        if (taskType.equals("deadline")) {
            return new Deadline(taskName, deadline, false);
        }
        return new Event(taskName, deadline, false);
    }


}

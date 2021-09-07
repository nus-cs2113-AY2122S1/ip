package InputHandle.Tasks;

import InputHandle.exception.TaskEmptyException;
import InputHandle.exception.TimeMissingException;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private List<Task> tasks = new ArrayList<>();

    public Task addList(String userInput) throws TimeMissingException, TaskEmptyException {
        Task newTask;
        newTask = getTask(userInput);
        this.tasks.add(newTask);
        return newTask;
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }

    public int getTotalTaskNumber() {
        return this.tasks.size();
    }

    public Task markAsDone(int index) throws IndexOutOfBoundsException{
        Task completedTask = this.tasks.get(index);
        completedTask.setCompleted();
        return completedTask;
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

package karlett.storage;

import karlett.task.Deadline;
import karlett.task.Event;
import karlett.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskListDecoder {

    public TaskListDecoder() {
    }

    /**
     * Interpret details of the input string representing a task, including its type, description,
     * possibly deadline/time and status. Turn it into a Task object and add to the array list of
     * tasks.
     *
     * @param tasks an array list of tasks
     * @param task a string that represents a task
     * @return an updated array list with the new task added
     * @throws IOException output or input exception
     */
    public ArrayList<Task> processFileData(ArrayList<Task> tasks, String task) throws IOException {
        char taskType;
        char taskStatus;
        String taskDescription;
        int indexOfDelimeter;
        taskType = task.charAt(0);
        taskStatus = task.charAt(4);
        boolean isDone = taskStatus == '1';
        if (taskType == 'T') {
            taskDescription = task.substring(8).trim();
            tasks.add(tasks.size(), new Task(taskDescription, isDone));
        } else {
            indexOfDelimeter = task.indexOf('|', 8);
            taskDescription = task.substring(8, indexOfDelimeter).trim();
            String time = task.substring(indexOfDelimeter + 1).trim();
            if (taskType == 'D') {
                tasks.add(tasks.size(), new Deadline(taskDescription, time, isDone));
            } else {
                tasks.add(tasks.size(), new Event(taskDescription, time, isDone));
            }
        }
        return tasks;
    }
}

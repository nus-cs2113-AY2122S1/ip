package karlett.storage;

import karlett.task.Deadline;
import karlett.task.Event;
import karlett.task.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskListDecoder {

    public TaskListDecoder() {
    }

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
            String time = task.substring(indexOfDelimeter + 1)
                    .trim().replace('T', ' ');
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

            LocalDateTime taskTime = LocalDateTime.parse(time, formatter);

            if (taskType == 'D') {
                tasks.add(tasks.size(), new Deadline(taskDescription, taskTime, isDone));
            } else {
                tasks.add(tasks.size(), new Event(taskDescription, taskTime, isDone));
            }
        }
        return tasks;
    }
}

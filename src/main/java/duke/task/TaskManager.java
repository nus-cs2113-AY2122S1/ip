package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

import java.io.BufferedReader;
import java.io.IOException;

public class TaskManager {
    // Constants
    private static final int MAX_TASKS = 100;
    private static final String DELIMITER = " | ";

    // Task list
    private final ArrayList<Task> tasks = new ArrayList<>();

    // Class variable for counting number of tasks
    private static int taskCount = 0;

    public int getTasksCount() {
        return taskCount;
    }

    // Add new task to the task list
    public Task addTask(Task task) {
        tasks.add(task);
        taskCount++;
        return task;
    }

    public Task deleteTask(int id) {
        Task task = tasks.get(id);
        tasks.remove(id);
        taskCount--;
        return task;
    }

    public Task addTodo(String description) {
        Todo todo = new Todo(description);
        return this.addTask(todo);
    }

    public Task addEvent(String description, String at) {
        Event event = new Event(description, at);
        return this.addTask(event);
    }

    public Task addDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        return this.addTask(deadline);
    }

    // Mark the specified task as done
    public Task markAsDone(int id) {
        Task task = tasks.get(id);
        task.markAsDone();
        return task;
    }

    // @@author brendanlsz-reused
    // Reused from https://www.techiedelight.com/how-to-read-a-file-using-bufferedreader-in-java/
    // with modifications
    public void preloadTasks(BufferedReader reader) throws IOException {
        int preloadTaskCount = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] description = line.trim().split("\\s*[|]\\s*");
            Task task;
            switch (description[0]) {
            case "T":
                task = this.addTodo(description[2]);
                break;
            case "D":
                task = this.addDeadline(description[2], description[3]);
                break;
            case "E":
                task = this.addEvent(description[2], description[3]);
                break;
            default:
                return;
            }
            if (description[1].strip().equals("1")) {
                task.markAsDone();
            }
            preloadTaskCount++;
        }
        System.out.println("Successfully preloaded " + preloadTaskCount + " tasks");
    }

    public String currentTasks() throws DukeException {
        StringBuilder lines = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            String taskType, isDone, description, time;
            boolean hasTime = false;
            Task task = tasks.get(i);
            isDone = task.getIsDone() ? "1" : "0";
            description = task.getDescription();
            if (task instanceof Todo) {
                taskType = "T";
                time = "";
            } else if (task instanceof Deadline) {
                hasTime = true;
                taskType = "D";
                time = ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                hasTime = true;
                taskType = "E";
                time = ((Event) task).getAt();
            } else {
                throw new DukeException("Error saving task no.: " + i);
            }
            lines.append(taskType + DELIMITER + isDone + DELIMITER + description +
                    (hasTime ? DELIMITER + time : "") + System.lineSeparator());
        }
        return lines.toString();
    }


    // Print out task list
    public void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." +
                    tasks.get(i).toString());
        }
    }
}

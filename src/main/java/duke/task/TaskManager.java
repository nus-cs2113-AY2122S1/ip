package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class TaskManager {
    // Constants
    private static final int MAX_TASKS = 100;
    private static final String DELIMITER = " | ";
    private static final String SYMBOL_TODO = "T";
    private static final String SYMBOL_DEADLINE = "D";
    private static final String SYMBOL_EVENT = "E";
    private static final String SYMBOL_DONE = "1";
    private static final String SYMBOL_NOT_DONE = "0";

    // Task list
    private final ArrayList<Task> tasks = new ArrayList<>();

    public int getTasksCount() {
        return tasks.size();
    }

    // Add new task to the task list
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task deleteTask(int id) {
        Task task = tasks.get(id);
        tasks.remove(id);
        return task;
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
            case SYMBOL_TODO:
                task = new Todo(description[2]);
                break;
            case SYMBOL_DEADLINE:
                task = new Deadline(description[2], description[3]);
                break;
            case SYMBOL_EVENT:
                task = new Event(description[2], description[3]);
                break;
            default:
                return;
            }
            this.addTask(task);
            if (description[1].strip().equals(SYMBOL_DONE)) {
                task.markAsDone();
            }
            preloadTaskCount++;
        }
        System.out.println("Successfully preloaded " + preloadTaskCount + " tasks");
    }

    public String currentTasks() throws DukeException {
        StringBuilder lines = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String taskType, isDone, description, time;
            boolean hasTime = false;
            Task task = tasks.get(i);
            isDone = task.getIsDone() ? SYMBOL_DONE : SYMBOL_NOT_DONE;
            description = task.getDescription();
            if (task instanceof Todo) {
                taskType = SYMBOL_TODO;
                time = "";
            } else if (task instanceof Deadline) {
                hasTime = true;
                taskType = SYMBOL_DEADLINE;
                time = ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                hasTime = true;
                taskType = SYMBOL_EVENT;
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
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(" " + i + "." +
                    tasks.get(i - 1).toString());
        }
    }

    public void findTasks(String keyword, Ui ui) throws DukeException {
        if (keyword.equals("")) {
            throw new DukeException("Please provide a valid keyword");
        }
        ArrayList<Task> matchingTasks =
                (ArrayList<Task>) tasks.stream()
                        .filter(t -> t.getDescription().contains(keyword))
                        .collect(Collectors.toList());
        ui.printMatchingTasks(matchingTasks);
    }
}

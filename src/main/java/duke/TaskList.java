package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }
    public TaskList(String tasksString) {
        tasks = new ArrayList<Task>();
        String[] tasksList = tasksString.split(System.lineSeparator());
        for (String task : tasksList) {
            writeToList(task);
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    private void writeToList(String line) {
        String lineData[] = line.split("~");
        if (lineData[0].equals("T")) {
            addTodoToTasks(lineData);
        } else if (lineData[0].equals("D")) {
            addDeadlineToTasks(lineData);
        } else if (lineData[0].equals("E")) {
            addEventToTasks(lineData);
        }
    }

    private void addEventToTasks(String[] lineData) {
        tasks.add(new Event(lineData[2], lineData[3]));
        if (lineData[1].equals("1")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    private void addDeadlineToTasks(String[] lineData) {
        tasks.add(new Deadline(lineData[2], lineData[3]));
        if (lineData[1].equals("1")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    private void addTodoToTasks(String[] lineData) {
        tasks.add(new Todo(lineData[2]));
        if (lineData[1].equals("1")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }
}

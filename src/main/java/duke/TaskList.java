package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;

/**
 * Class that contains the ArrayList of all tasks in the programme, and corresponding methods.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initializes new TaskList with an empty ArrayList of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Initializes new TaskList.
     * Takes and processes a string of information from the memory file and adds the corresponding tasks to the TaskList.
     * @param tasksString memory file containing all stored information as a string
     */
    public TaskList(String tasksString) {
        tasks = new ArrayList<Task>();
        String[] tasksList = tasksString.split(System.lineSeparator());
        for (String task : tasksList) {
            writeToList(task);
        }
    }

    /**
     * Returns all tasks as an ArrayList.
     * @return ArrayList of all tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the task with specified index in the ArrayList of tasks.
     * @param taskIndex index of task (starting from 0)
     * @return specified task of index taskIndex
     */
    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    /**
     *
     * @param line
     */
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

    /**
     * Parses the memory string input and adds it to the TaskList as an event task.
     * @param lineData single-line memory string input from memory file
     */
    private void addEventToTasks(String[] lineData) {
        tasks.add(new Event(lineData[2], lineData[3]));
        if (lineData[1].equals("1")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    /**
     * Parses the memory string input and adds it to the TaskList as a deadline task.
     * @param lineData single-line memory string input from memory file
     */
    private void addDeadlineToTasks(String[] lineData) {
        tasks.add(new Deadline(lineData[2], lineData[3]));
        if (lineData[1].equals("1")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    /**
     * Parses the memory string input and adds it to the TaskList as a todo task.
     * @param lineData single-line memory string input from memory file
     */
    private void addTodoToTasks(String[] lineData) {
        tasks.add(new Todo(lineData[2]));
        if (lineData[1].equals("1")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }
}

package task;

import exception.DukeException;
import storage.Storage;
import ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private static ArrayList<Task> tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Class constructor taking in an Ui Object.
     *
     * @param uiObject Ui object
     */
    public TaskList(Ui uiObject) {
        ui = uiObject;
        storage = new Storage();
        tasks = new ArrayList<>();
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return Size of Tasks
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the Task at the specified index.
     *
     * @param index The index where the task can be found at
     * @return Task object
     */
    public Task getTaskAtIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new Task to list of tasks after loading from file.
     *
     * @param newTask The Task constructed after loaded from file
     */
    public void loadTaskFromFile(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Adds a new TodoObject to the list of Tasks.
     *
     * @param taskName The name of the task
     */
    public void addNewTodoObject(String taskName) {
        Todo newTodo = new Todo(taskName);
        tasks.add(newTodo);
        ui.printAddedTaskMessage(newTodo.toString());
        storage.saveTasksToFile(this);
    }

    /**
     * Adds a new EventObject to the list of Tasks.
     *
     * @param taskName The name of the task
     * @param atWhen   The date and time of the event
     */
    public void addNewEventObject(String taskName, String atWhen) {
        Event newEvent = new Event(taskName, atWhen);
        tasks.add(newEvent);
        ui.printAddedTaskMessage(newEvent.toString());
        storage.saveTasksToFile(this);
    }

    /**
     * Adds a new DeadlineObject to the list of Tasks.
     *
     * @param taskName The name of the task
     * @param byWhen   The date and time of the deadline
     */
    public void addNewDeadlineObject(String taskName, String byWhen) {
        LocalDate byWhenDateTime = LocalDate.parse(byWhen);
        Deadline newDeadline = new Deadline(taskName, byWhenDateTime);
        tasks.add(newDeadline);
        ui.printAddedTaskMessage(newDeadline.toString());
        storage.saveTasksToFile(this);
    }

    /**
     * Removes the Task at the specified index.
     *
     * @param index The index where the task can be found at
     */
    public void removeTaskAtIndex(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        storage.saveTasksToFile(this);
        ui.printDeletedTaskMessage(deletedTask, tasks.size());
    }

    /**
     * Mark the task as done and calls Ui to print out marked as done message.
     *
     * @param index The index of the Task that the user wants to mark as done in the list
     * @throws DukeException If tasks is empty, or the index given is out of bounds
     */
    public void markTaskAsDone(int index) throws DukeException {
        try {
            if (tasks.size() == 0) {
                throw new DukeException(DukeException.NO_TASK_MESSAGE);
            } else if (tasks.size() <= index) {
                throw new DukeException("OVERFLOWED INDEX PLEASE REPLACE ME");
            }
            Task task = tasks.get(index);
            task.setDone();
            ui.printMarkedTaskDoneMessage(task);
            storage.saveTasksToFile(this);
        } catch (NullPointerException e) {
            throw new DukeException("TOBECHANGED");
        }
    }

    /**
     * Returns a list of tasks formatted as strings for saving to file.
     *
     * @return ArrayList of string-formatted Tasks
     */
    public static ArrayList<String> getTasksAsStringArrayList(TaskList tasks) {
        ArrayList<String> stringFormattedTasks = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currentTaskPtr = tasks.getTaskAtIndex(i);
            char taskIdentifier = currentTaskPtr.toString().charAt(1);
            String temp = taskIdentifier + "||" + currentTaskPtr.isDone()
                    + "||" + currentTaskPtr.getTaskName();
            if (taskIdentifier == 'D') {
                temp += "||" + ((Deadline) currentTaskPtr).getByWhen();
            } else if (taskIdentifier == 'E') {
                temp += "||" + ((Event) currentTaskPtr).getAtWhen();
            }
            stringFormattedTasks.add(temp);
        }
        return stringFormattedTasks;
    }

    /**
     * Creates and returns a Task from the read file line.
     *
     * @param fileLine The line of string read from file
     * @return A Task created from specified file line.
     */
    public static Task createSavedTask(String fileLine) {
        String[] tokens = fileLine.split("\\|\\|");
        String taskType = tokens[0];
        String taskName = tokens[2];
        boolean isDone = Boolean.parseBoolean(tokens[1]);
        Task savedTask;
        switch (taskType) {
        case "D":
            LocalDate byWhenDateTime = LocalDate.parse(tokens[3]);
            savedTask = new Deadline(taskName, isDone, byWhenDateTime);
            break;
        case "E":
            savedTask = new Event(taskName, isDone, tokens[3]);
            break;
        default:
            savedTask = new Todo(taskName, isDone);
        }
        return savedTask;
    }

    /**
     * Returns a filtered list of Tasks with task name containing the keyword.
     *
     * @param keyword The keyword specified by user
     * @return A list of tasks with task name matching the keyword
     */
    public static ArrayList<Task> getAllTasksByName(String keyword) {
        ArrayList<Task> filteredTasks = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getTaskName().contains(keyword))
                .collect(Collectors.toList());
        return filteredTasks;
    }
}

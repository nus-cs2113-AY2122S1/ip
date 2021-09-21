package task;

import exception.DukeException;
import storage.Storage;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Ui ui;
    private final Storage storage;

    public TaskList(Ui uiObject) {
        ui = uiObject;
        storage = new Storage();
        tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTaskAtIndex(int index) {
        return tasks.get(index);
    }

    public void loadTaskFromFile(Task newTask) {
        tasks.add(newTask);
    }

    public void addNewTodoObject(String taskName) {
        Todo newTodo = new Todo(taskName);
        tasks.add(newTodo);
        ui.printAddedTaskMessage(newTodo.toString());
        storage.saveTasksToFile(this);
    }

    public void addNewEventObject(String taskName, String atWhen) {
        Event newEvent = new Event(taskName, atWhen);
        tasks.add(newEvent);
        ui.printAddedTaskMessage(newEvent.toString());
        storage.saveTasksToFile(this);
    }

    public void addNewDeadlineObject(String taskName, String byWhen) {
        LocalDate byWhenDateTime = LocalDate.parse(byWhen);
        Deadline newDeadline = new Deadline(taskName, byWhenDateTime);
        tasks.add(newDeadline);
        ui.printAddedTaskMessage(newDeadline.toString());
        storage.saveTasksToFile(this);
    }

    public void removeTaskAtIndex(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        storage.saveTasksToFile(this);
        ui.printDeletedTaskMessage(deletedTask, tasks.size());
    }

    /**
     * Mark the task as done and print out marked as done message.
     *
     * @param index task index of task that user wants to mark as done in the list
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
     * Formats the list of Tasks and
     * returns a List of Tasks formatted as string
     *
     * @return ArrayList of String formatted Tasks
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
}

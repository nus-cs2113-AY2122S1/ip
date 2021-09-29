package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.MissingParameterException;
import duke.exception.TaskNotFoundException;
import duke.task.*;

import java.util.ArrayList;

/**
 * The list to store and manage all tasks
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private DukeStorage storage = new DukeStorage();

    public TaskList() {
        storage.loadData(tasks);
    }

    /**
     * Prints all current tasks in the list
     */
    public void displayTaskList() {
        DukeUI.printTaskList(tasks,false);
    }

    /**
     * Adds the task given by the user to the list
     *
     * @param input the command given by the user
     * @param taskType the Type of this additional task
     */
    public void addTask(String input, Action taskType) {
        try {
            String[] parameters = Parser.parseTask(input, taskType);
            Task newTask = null;
            switch (taskType) {
            case TO_DO:
                newTask = new ToDo(parameters[0]);
                break;
            case DEADLINE:
                newTask = new Deadline(parameters[0], parameters[1]);
                break;
            case EVENT:
                newTask = new Event(parameters[0], parameters[1]);
                break;
            }
            if (newTask != null) {
                tasks.add(newTask);
                DukeUI.printCompleteAddTask(newTask, tasks.size());
                storage.saveData(tasks);
            }
        } catch (EmptyDescriptionException | MissingParameterException e) {
            DukeUI.printError(e);
        }
    }

    /**
     * Marks the task specified by the user as done
     *
     * @param taskNumber the task to mark done
     * @throws TaskNotFoundException when the specified task is not found
     */
    public void markTaskDone(int taskNumber) throws TaskNotFoundException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new TaskNotFoundException();
        }
        int realIndex = taskNumber - 1;

        tasks.get(realIndex).setDone();
        storage.saveData(tasks);
        DukeUI.printMarkTaskDone(tasks.get(realIndex));
    }

    /**
     * Finds all tasks with matching keywords
     *
     * @param keyword the keywords to find relevant tasks
     */
    public void findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task: tasks) {
            if(task.getDescription().contains(keyword)){
                foundTasks.add(task);
            }
        }
        DukeUI.printTaskList(foundTasks,true);
    }


    /**
     * Deletes the task specified by the user
     *
     * @param taskNumber the task to delete
     * @throws TaskNotFoundException when the specified task is not found
     */
    public void deleteTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new TaskNotFoundException();
        }
        int realIndex = taskNumber - 1;

        Task removedTask = tasks.get(realIndex);
        tasks.remove(realIndex);
        storage.saveData(tasks);
        DukeUI.printCompleteDeleteTask(removedTask, tasks.size());
    }
}

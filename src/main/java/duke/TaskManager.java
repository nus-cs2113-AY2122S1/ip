package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.MissingParameterException;
import duke.exception.TaskNotFoundException;
import duke.task.*;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private DukeStorage storage = new DukeStorage();

    public TaskManager() {
        storage.loadData(tasks);
    }

    public void displayTaskList() {
        DukeUI.printTaskList(tasks);
    }

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

    public void markTaskDone(int taskNumber) throws TaskNotFoundException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new TaskNotFoundException();
        }
        int realIndex = taskNumber - 1;

        tasks.get(realIndex).setDone();
        storage.saveData(tasks);
        DukeUI.printMarkTaskDone(tasks.get(realIndex));
    }


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

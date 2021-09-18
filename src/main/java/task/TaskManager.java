package task;

import console.InputParser;
import error.DukeTaskNameEmptyException;
import error.Error;
import task.subtask.Deadline;
import task.subtask.Event;
import task.subtask.Todo;
import ui.Display;

import java.util.ArrayList;

public class TaskManager {

    public static final String EMPTY_TASK_NAME = "";
    public static final int NAME_INDEX = 0;
    public static final int DATE_INDEX = 1;
    public static final int INDEX_OFFSET = 1;

    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static int taskCount;

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public void increaseTaskCount() {
        taskCount++;
    }

    public void decreaseTaskCount() {
        taskCount--;
    }

    public String getTaskName(String taskName) throws DukeTaskNameEmptyException {
        if (taskName.equals(EMPTY_TASK_NAME)) {
            throw new DukeTaskNameEmptyException();
        }
        return taskName;
    }

    public String getTaskNameComponent(String taskInformation) throws DukeTaskNameEmptyException {
        String[] taskComponents = InputParser.getTaskWithDateComponents(taskInformation);
        return getTaskName(taskComponents[NAME_INDEX]);
    }

    public String getDateComponent(String taskInformation) {
        String[] taskComponents = InputParser.getTaskWithDateComponents(taskInformation);
        return taskComponents[DATE_INDEX];
    }

    public void addTodoTask(String taskInformation) {
        try {
            allTasks.add(new Todo(getTaskName(taskInformation)));
            increaseTaskCount();
            Display.displayTaskCreation(allTasks.get(taskCount - INDEX_OFFSET), Display.TASK_NAME_TODO, taskCount);
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        }
    }

    public void addDeadlineTask(String taskInformation) {
        try {
            allTasks.add(new Deadline(getTaskNameComponent(taskInformation), getDateComponent(taskInformation)));
            increaseTaskCount();
            Display.displayTaskCreation(allTasks.get(taskCount - INDEX_OFFSET), Display.TASK_NAME_DEADLINE, taskCount);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        }
    }

    public void addEventTask(String taskInformation) {
        try {
            allTasks.add(new Event(getTaskNameComponent(taskInformation), getDateComponent(taskInformation)));
            increaseTaskCount();
            Display.displayTaskCreation(allTasks.get(taskCount - INDEX_OFFSET), Display.TASK_NAME_EVENT, taskCount);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        }
    }

    public void deleteTask(String[] commandComponents) {
        try {
            int taskNumber = InputParser.getTaskNumber(commandComponents);
            Task deletedTask = allTasks.get(taskNumber);
            allTasks.remove(taskNumber);
            decreaseTaskCount();
            Display.displayTaskDeleted(deletedTask, taskCount);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskNonExistentError();
        } catch (NumberFormatException e) {
            Error.displayNotANumberError();
        }
    }

    public void markTaskAsCompleted(String[] commandComponents) {
        try {
            int taskNumber = InputParser.getTaskNumber(commandComponents);
            allTasks.get(taskNumber).setTaskCompleted();
            Display.displayTaskCompleted(allTasks.get(taskNumber).getTask());
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskNonExistentError();
        } catch (NumberFormatException e) {
            Error.displayNotANumberError();
        }
    }

    public void listTask() {
        Display.printListTaskLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + INDEX_OFFSET + ". " + allTasks.get(i));
        }
        Display.printListTaskLine();
    }

    public void addSavedTodoTask(Boolean isCompleted, String taskDetails) {
        try {
            allTasks.add(new Todo(getTaskName(taskDetails)));
            if (isCompleted) {
                allTasks.get(taskCount).setTaskCompleted();
            }
            increaseTaskCount();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayFileSavedTaskNameEmptyError();
        }
    }

    public void addSavedDeadlineTask(Boolean isCompleted, String taskDetails) {
        try {
            allTasks.add(new Deadline(getTaskNameComponent(taskDetails), getDateComponent(taskDetails)));
            if (isCompleted) {
                allTasks.get(taskCount).setTaskCompleted();
            }
            increaseTaskCount();
        } catch (IndexOutOfBoundsException e) {
            Error.displayFileSavedTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayFileSavedTaskNameEmptyError();
        }
    }

    public void addSavedEventTask(Boolean isCompleted, String taskDetails) {
        try {
            allTasks.add(new Event(getTaskNameComponent(taskDetails), getDateComponent(taskDetails)));
            if (isCompleted) {
                allTasks.get(taskCount).setTaskCompleted();
            }
            increaseTaskCount();
        } catch (IndexOutOfBoundsException e) {
            Error.displayFileSavedTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayFileSavedTaskNameEmptyError();
        }
    }
}

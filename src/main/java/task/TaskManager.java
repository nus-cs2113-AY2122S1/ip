package task;

import console.InputParser;
import error.DukeTaskNameEmptyException;
import error.Error;
import task.subtask.Deadline;
import task.subtask.Event;
import task.subtask.Todo;
import utils.Display;

import java.util.ArrayList;

public class TaskManager {

    public static final String EMPTY_TASK_NAME = "";
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static int taskCount;

    public String getTaskName(String taskName) throws DukeTaskNameEmptyException {
        if (taskName.equals(EMPTY_TASK_NAME)) {
            throw new DukeTaskNameEmptyException();
        }
        return taskName;
    }

    public void addTodoTask(String taskInformation) {
        try {
            allTasks.add(new Todo(getTaskName(taskInformation)));
            taskCount++;
            Display.displayTaskCreation(allTasks.get(taskCount - 1), Display.TASK_NAME_TODO, taskCount);
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        }
    }

    public void addDeadlineTask(String taskInformation) {
        try {
            String[] taskComponents = InputParser.getTaskComponents(taskInformation);
            String taskName = getTaskName(taskComponents[0]);
            String deadline = taskComponents[1];
            allTasks.add(new Deadline(taskName, deadline));
            taskCount++;
            Display.displayTaskCreation(allTasks.get(taskCount - 1), Display.TASK_NAME_DEADLINE, taskCount);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        }
    }

    public void addEventTask(String taskInformation) {
        try {
            String[] taskComponents = InputParser.getTaskComponents(taskInformation);
            String taskName = getTaskName(taskComponents[0]);
            String eventTime = taskComponents[1];
            allTasks.add(new Event(taskName, eventTime));
            taskCount++;
            Display.displayTaskCreation(allTasks.get(taskCount - 1), Display.TASK_NAME_EVENT, taskCount);
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
            taskCount--;
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
            System.out.println(i + 1 + ". " + allTasks.get(i));
        }
        Display.printListTaskLine();
    }
}

package task;

import console.InputParser;
import error.DukeTaskNameEmptyException;
import error.Error;
import task.subtask.Deadline;
import task.subtask.Event;
import task.subtask.Todo;
import utils.Display;

public class TaskManager {

    public static final int MAX_NUMBER_OF_TASKS = 100;
    public static final String EMPTY_TODO_NAME = "";
    private Task[] allTasks = new Task[MAX_NUMBER_OF_TASKS];
    private int taskCount;

    public String getTaskName(String taskName) throws DukeTaskNameEmptyException {
        if (taskName.equals(EMPTY_TODO_NAME)) {
            throw new DukeTaskNameEmptyException();
        }
        return taskName;
    }

    public void addTodoTask(String taskInformation) {
        try {
            allTasks[taskCount] = new Todo(getTaskName(taskInformation));
            taskCount++;
            Display.displayTaskCreation(allTasks[taskCount - 1], Display.TASK_NAME_TODO, taskCount);
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        }
    }

    public void addDeadlineTask(String taskInformation) {
        try {
            String[] taskComponents = InputParser.getTaskComponents(taskInformation);
            String taskName = getTaskName(taskComponents[0]);
            String deadline = taskComponents[1];
            allTasks[taskCount] = new Deadline(taskName, deadline);
            taskCount++;
            Display.displayTaskCreation(allTasks[taskCount - 1], Display.TASK_NAME_DEADLINE, taskCount);
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
            allTasks[taskCount] = new Event(taskName, eventTime);
            taskCount++;
            Display.displayTaskCreation(allTasks[taskCount - 1], Display.TASK_NAME_EVENT, taskCount);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        }
    }

    public void markTaskAsCompleted(String[] commandComponents) {
        try {
            int taskNumber = InputParser.getTaskNumber(commandComponents);
            allTasks[taskNumber].setTaskCompleted();
            Display.displayTaskCompleted(allTasks[taskNumber].getTask());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            Error.displayTaskNonExistentError();
        } catch (NumberFormatException e) {
            Error.displayNotANumberError();
        }
    }

    public void listTask() {
        Display.printListTaskLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + ". " + allTasks[i]);
        }
        Display.printListTaskLine();
    }
}

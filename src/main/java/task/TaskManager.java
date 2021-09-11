package task;

import console.InputParser;
import error.DukeTaskNameEmptyException;
import error.Error;
import task.subtask.Deadline;
import task.subtask.Event;
import task.subtask.Todo;
import utils.Display;
import utils.FileManager;

import java.io.IOException;

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
            FileManager.updateFileData(allTasks);
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        } catch (IOException e) {
            Error.displayFileUpdateError();
        }
    }

    public void addDeadlineTask(String taskInformation) {
        try {
            String[] taskComponents = InputParser.getTaskWithDateComponents(taskInformation);
            String taskName = getTaskName(taskComponents[0]);
            String deadline = taskComponents[1];
            allTasks.add(new Deadline(taskName, deadline));
            taskCount++;
            Display.displayTaskCreation(allTasks.get(taskCount - 1), Display.TASK_NAME_DEADLINE, taskCount);
            FileManager.updateFileData(allTasks);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        } catch (IOException e) {
            Error.displayFileUpdateError();
        }
    }

    public void addEventTask(String taskInformation) {
        try {
            String[] taskComponents = InputParser.getTaskWithDateComponents(taskInformation);
            String taskName = getTaskName(taskComponents[0]);
            String eventTime = taskComponents[1];
            allTasks.add(new Event(taskName, eventTime));
            taskCount++;
            Display.displayTaskCreation(allTasks.get(taskCount - 1), Display.TASK_NAME_EVENT, taskCount);
            FileManager.updateFileData(allTasks);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        } catch (IOException e) {
            Error.displayFileUpdateError();
        }
    }

    public void deleteTask(String[] commandComponents) {
        try {
            int taskNumber = InputParser.getTaskNumber(commandComponents);
            Task deletedTask = allTasks.get(taskNumber);
            allTasks.remove(taskNumber);
            taskCount--;
            Display.displayTaskDeleted(deletedTask, taskCount);
            FileManager.updateFileData(allTasks);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskNonExistentError();
        } catch (NumberFormatException e) {
            Error.displayNotANumberError();
        } catch (IOException e) {
            Error.displayFileUpdateError();
        }
    }

    public void markTaskAsCompleted(String[] commandComponents) {
        try {
            int taskNumber = InputParser.getTaskNumber(commandComponents);
            allTasks.get(taskNumber).setTaskCompleted();
            Display.displayTaskCompleted(allTasks.get(taskNumber).getTask());
            FileManager.updateFileData(allTasks);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskNonExistentError();
        } catch (NumberFormatException e) {
            Error.displayNotANumberError();
        } catch (IOException e) {
            Error.displayFileUpdateError();
        }
    }

    public void listTask() {
        Display.printListTaskLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + ". " + allTasks.get(i));
        }
        Display.printListTaskLine();
    }

    public void addSavedTodoTask(Boolean isCompleted, String taskDetails) {
        try {
            allTasks.add(new Todo(getTaskName(taskDetails)));
            if (isCompleted) {
                allTasks.get(taskCount).setTaskCompleted();
            }
            taskCount++;
        } catch (DukeTaskNameEmptyException e) {
            Error.displayFileSavedTaskNameEmptyError();
        }
    }

    public void addSavedDeadlineTask(Boolean isCompleted, String taskDetails) {
        try {
            String[] taskComponents = InputParser.getTaskWithDateComponents(taskDetails);
            String taskName = getTaskName(taskComponents[0]);
            String deadline = taskComponents[1];
            allTasks.add(new Deadline(taskName, deadline));
            if (isCompleted) {
                allTasks.get(taskCount).setTaskCompleted();
            }
            taskCount++;
        } catch (IndexOutOfBoundsException e) {
            Error.displayFileSavedTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayFileSavedTaskNameEmptyError();
        }
    }

    public void addSavedEventTask(Boolean isCompleted, String taskDetails) {
        try {
            String[] taskComponents = InputParser.getTaskWithDateComponents(taskDetails);
            String taskName = getTaskName(taskComponents[0]);
            String eventTime = taskComponents[1];
            allTasks.add(new Event(taskName, eventTime));
            if (isCompleted) {
                allTasks.get(taskCount).setTaskCompleted();
            }
            taskCount++;
        } catch (IndexOutOfBoundsException e) {
            Error.displayFileSavedTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayFileSavedTaskNameEmptyError();
        }
    }
}

package task;

import console.InputParser;
import error.DukeTaskNameEmptyException;
import error.Error;
import task.subtask.Deadline;
import task.subtask.Event;
import task.subtask.Todo;
import ui.Display;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskManager {

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

    public void addTodoTask(String taskInformation) {
        try {
            allTasks.add(new Todo(InputParser.getTaskName(taskInformation)));
            increaseTaskCount();
            Display.displayTaskCreation(allTasks.get(taskCount - INDEX_OFFSET), Display.TASK_NAME_TODO, taskCount);
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        }
    }

    public void addDeadlineTask(String taskInformation) {
        try {
            allTasks.add(new Deadline(InputParser.getTaskNameComponent(taskInformation),
                    InputParser.getDateComponent(taskInformation),
                    InputParser.getTimeComponent(taskInformation)));
            increaseTaskCount();
            Display.displayTaskCreation(allTasks.get(taskCount - INDEX_OFFSET), Display.TASK_NAME_DEADLINE, taskCount);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        } catch (DateTimeParseException e) {
            Error.displayDateFormatError();
        }
    }

    public void addEventTask(String taskInformation) {
        try {
            allTasks.add(new Event(InputParser.getTaskNameComponent(taskInformation),
                    InputParser.getDateComponent(taskInformation),
                    InputParser.getTimeComponent(taskInformation)));
            increaseTaskCount();
            Display.displayTaskCreation(allTasks.get(taskCount - INDEX_OFFSET), Display.TASK_NAME_EVENT, taskCount);
        } catch (IndexOutOfBoundsException e) {
            Error.displayTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayTaskNameEmptyError();
        } catch (DateTimeParseException e) {
            Error.displayDateFormatError();
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

    public void findTask(String taskKeyword) {
        Display.printListTaskLine();
        allTasks.stream()
                .filter((task) -> task.getTask().contains(taskKeyword))
                .forEach(System.out::println);
        Display.printListTaskLine();
    }

    public void addSavedTodoTask(Boolean isCompleted, String taskDetails) {
        try {
            allTasks.add(new Todo(InputParser.getTaskName(taskDetails)));
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
            allTasks.add(new Deadline(InputParser.getTaskNameComponent(taskDetails),
                    InputParser.getSavedDateComponent(taskDetails),
                    InputParser.getSavedTimeComponent(taskDetails)));
            if (isCompleted) {
                allTasks.get(taskCount).setTaskCompleted();
            }
            increaseTaskCount();
        } catch (IndexOutOfBoundsException e) {
            Error.displayFileSavedTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayFileSavedTaskNameEmptyError();
        } catch (DateTimeParseException e) {
            Error.displayFileSavedDateFormatError();
        }
    }

    public void addSavedEventTask(Boolean isCompleted, String taskDetails) {
        try {
            allTasks.add(new Event(InputParser.getTaskNameComponent(taskDetails),
                    InputParser.getSavedDateComponent(taskDetails),
                    InputParser.getSavedTimeComponent(taskDetails)));
            if (isCompleted) {
                allTasks.get(taskCount).setTaskCompleted();
            }
            increaseTaskCount();
        } catch (IndexOutOfBoundsException e) {
            Error.displayFileSavedTaskFormatError();
        } catch (DukeTaskNameEmptyException e) {
            Error.displayFileSavedTaskNameEmptyError();
        } catch (DateTimeParseException e) {
            Error.displayFileSavedDateFormatError();
        }
    }
}

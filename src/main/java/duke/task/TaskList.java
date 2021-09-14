package duke.task;

import duke.exception.DukeException;
import duke.exception.ExceptionMessages;
import duke.PrintUtils;

import java.util.ArrayList;

public class TaskList {

    //Output messages
    public static final String MESSAGE_TASK_ADDED_SUCCESSFULLY = "The following task has been added:";
    public static final String MESSAGE_MARK_TASK_SUCCESS = "The following task has been marked as done:";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "The following task has been deleted:";
    public static final String MESSAGE_NO_TASK_AVAILABLE = "You have no tasks yet";
    public static final String MESSAGE_PRINT_ALL_TASK_SUCCESS = "Here are all your tasks:";

    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }


    public void addTask(Task task) throws DukeException {
        if (task.getDescription().equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_DESCRIPTION);
        }
        PrintUtils.printHorizontalLine();
        taskList.add(task);
        printAddTaskSuccessMessage();
        PrintUtils.printHorizontalLine();
    }

    public void addTaskWithoutMessage(Task task) throws DukeException {
        if (task.getDescription().equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_DESCRIPTION);
        }
        taskList.add(task);
    }

    private void printAddTaskSuccessMessage() {
        final int listLength = taskList.size();
        final int lastTaskIndex = listLength - 1;
        System.out.println(MESSAGE_TASK_ADDED_SUCCESSFULLY);
        PrintUtils.printSpacing();
        System.out.println(taskList.get(lastTaskIndex));
        System.out.println("You now have " + listLength + " task(s)");
    }

    public void deleteTask(String input) throws DukeException, NumberFormatException {
        if (input.equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_TASK_NUMBER);
        }
        int taskNumber = Integer.parseInt(input.trim());
        if (!isExistingTask(taskNumber)) {
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_TASK_NUMBER);
        }
        deleteExistingTask(taskNumber);
    }

    private void deleteExistingTask(int taskNumber) {
        final int taskIndex = taskNumber - 1;
        printDeleteTaskSuccessMessage(taskList.get(taskIndex));
        taskList.remove(taskIndex);
    }

    private void printDeleteTaskSuccessMessage(Task task) {
        PrintUtils.printHorizontalLine();
        System.out.println(MESSAGE_DELETE_TASK_SUCCESS);
        PrintUtils.printSpacing();
        System.out.println(task);
        PrintUtils.printHorizontalLine();
    }

    public void markTaskAsDone(String input) throws DukeException, NumberFormatException {
        if (input.equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_TASK_NUMBER);
        }
        int taskNumber = Integer.parseInt(input.trim());
        if (!isExistingTask(taskNumber)) {
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_TASK_NUMBER);
        }
        markExistingTaskAsDone(taskList.get(taskNumber-1));
    }

    private boolean isExistingTask(int taskNumber) {
        final int listLength = taskList.size();
        return taskNumber > 0 && taskNumber <= listLength;
    }

    private void markExistingTaskAsDone(Task task) {
        task.markTaskAsDone();
        printMarkTaskSuccessMessage(task);
    }

    private void printMarkTaskSuccessMessage(Task task) {
        PrintUtils.printHorizontalLine();
        System.out.println(MESSAGE_MARK_TASK_SUCCESS);
        PrintUtils.printSpacing();
        System.out.println(task);
        PrintUtils.printHorizontalLine();
    }

    public void printAllTasks() {
        PrintUtils.printHorizontalLine();
        if (taskList.isEmpty()) {
            System.out.println(MESSAGE_NO_TASK_AVAILABLE);
            PrintUtils.printHorizontalLine();
            return;
        }
        System.out.println(MESSAGE_PRINT_ALL_TASK_SUCCESS);
        for (int i = 0; i < taskList.size(); i++) {
            PrintUtils.printSpacing();
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        PrintUtils.printHorizontalLine();
    }

    public String toFile() {
        StringBuilder fileContent = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task taskToConvert = taskList.get(i);
            if (taskToConvert instanceof Todo) {
                fileContent.append("T|").append(taskToConvert.isDone ? '1' : '0').append('|').append(taskToConvert.description);
            } else if (taskToConvert instanceof Deadline) {
                fileContent.append("D|").append(taskToConvert.isDone ? '1' : '0').append('|').append(taskToConvert.description)
                        .append('|').append(((Deadline) taskToConvert).getBy());
            } else if (taskToConvert instanceof Event) {
                fileContent.append("E|").append(taskToConvert.isDone ? '1' : '0').append('|').append(taskToConvert.description)
                        .append('|').append(((Event) taskToConvert).getAt());
            }
            //skip iteration for last task
            if (i == taskList.size() - 1) {
                continue;
            }
            fileContent.append(System.lineSeparator());
        }
        return fileContent.toString();
    }
}

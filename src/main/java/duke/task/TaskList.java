package duke.task;

import duke.exception.DukeException;
import duke.exception.ExceptionMessages;
import duke.PrintUtils;

public class TaskList {

    //Output messages
    public static final String MESSAGE_TASK_ADDED_SUCCESSFULLY = "The following task has been added:";
    public static final String MESSAGE_MARK_TASK_SUCCESS = "The following task has been marked as done:";
    public static final String MESSAGE_NO_TASK_AVAILABLE = "You have no tasks yet";
    public static final String MESSAGE_PRINT_ALL_TASK_SUCCESS = "Here are all your tasks:";


    private int taskNumber;
    private Task[] taskList;

    public TaskList() {
        taskNumber = 0;
        taskList = new Task[100];
    }


    public void addTask(Task task) throws DukeException {
        if (task.getDescription().equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_DESCRIPTION);
        }
        PrintUtils.printHorizontalLine();
        taskList[taskNumber] = task;
        printAddTaskSuccessMessage();
        taskNumber++;
        PrintUtils.printHorizontalLine();
    }

    public void addTaskWithoutMessage(Task task) throws DukeException {
        if (task.getDescription().equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_DESCRIPTION);
        }
        taskList[taskNumber] = task;
        taskNumber++;
    }

    private void printAddTaskSuccessMessage() {
        System.out.println(MESSAGE_TASK_ADDED_SUCCESSFULLY);
        PrintUtils.printSpacing();
        System.out.println(taskList[taskNumber]);
        System.out.println("You now have " + (taskNumber + 1) + " task(s)");
    }

    public void markTaskAsDone(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_TASK_NUMBER);
        }
        int taskNumber = Integer.parseInt(input.trim());
        if (!isExistingTask(taskNumber)) {
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_TASK_NUMBER);
        }
        markExistingTaskAsDone(taskList[taskNumber - 1]);
    }

    private boolean isExistingTask(int taskNumber) {
        return taskNumber > 0 && taskNumber <= this.taskNumber;
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
        if (taskNumber == 0) {
            System.out.println(MESSAGE_NO_TASK_AVAILABLE);
            PrintUtils.printHorizontalLine();
            return;
        }
        System.out.println(MESSAGE_PRINT_ALL_TASK_SUCCESS);
        for (int i = 0; i < taskNumber; i++) {
            PrintUtils.printSpacing();
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        PrintUtils.printHorizontalLine();
    }

    public String toFile() {
        StringBuilder fileContent = new StringBuilder();
        for (int i = 0; i < taskNumber; i++) {
            if (taskList[i] instanceof Todo) {
                fileContent.append("T|").append(taskList[i].isDone ? '1' : '0').append('|').append(taskList[i].description);
            } else if (taskList[i] instanceof Deadline) {
                fileContent.append("D|").append(taskList[i].isDone ? '1' : '0').append('|').append(taskList[i].description)
                        .append('|').append(((Deadline) taskList[i]).getBy());
            } else if (taskList[i] instanceof Event) {
                fileContent.append("E|").append(taskList[i].isDone ? '1' : '0').append('|').append(taskList[i].description)
                        .append('|').append(((Event) taskList[i]).getAt());
            }
            //skip iteration for last task
            if (i == taskNumber - 1) {
                continue;
            }
            fileContent.append(System.lineSeparator());
        }
        return fileContent.toString();
    }
}

package duke.task;

import duke.exception.DukeException;
import duke.exception.ExceptionMessages;
import duke.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {


    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }


    public void addTask(Task task) throws DukeException {
        if (task.getDescription().equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_DESCRIPTION);
        }
        taskList.add(task);
        final int listLength = taskList.size();
        //To check that task was added successfully
        final Task mostRecentTask = taskList.get(listLength - 1);
        Ui.printAddTaskMessage(listLength, mostRecentTask);
    }

    public void addTaskWithoutMessage(Task task) throws DukeException {
        if (task.getDescription().equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_DESCRIPTION);
        }
        taskList.add(task);
    }


    public void deleteTask(String input) throws DukeException, NumberFormatException {
        if (input.equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_TASK_NUMBER);
        }
        int taskNumber = Integer.parseInt(input.trim());
        if (isMissingTask(taskNumber)) {
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_TASK_NUMBER);
        }
        deleteExistingTask(taskNumber);
    }

    private void deleteExistingTask(int taskNumber) {
        final int taskIndex = taskNumber - 1;
        Ui.printDeleteTaskSuccessMessage(taskList.get(taskIndex));
        taskList.remove(taskIndex);
    }


    public void markTaskAsDone(String input) throws DukeException, NumberFormatException {
        if (input.equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_TASK_NUMBER);
        }
        int taskNumber = Integer.parseInt(input.trim());
        if (isMissingTask(taskNumber)) {
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_TASK_NUMBER);
        }
        markExistingTaskAsDone(taskList.get(taskNumber - 1));
    }

    private boolean isMissingTask(int taskNumber) {
        final int listLength = taskList.size();
        return taskNumber <= 0 || taskNumber > listLength;
    }

    private void markExistingTaskAsDone(Task task) {
        task.markTaskAsDone();
        Ui.printMarkTaskSuccessMessage(task);
    }

    public void listAllTasks() {
        Ui.printAllTasks(taskList);
    }

    public void findTasks(String keyword) throws DukeException {
        if (keyword.equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_EMPTY_SEARCH_QUERY);
        }
        ArrayList<Task> matchingTasks =
                (ArrayList<Task>) taskList.stream()
                        .filter(t -> t.getDescription().contains(keyword))
                        .collect(Collectors.toList());
        Ui.printMatchingTasks(matchingTasks);
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

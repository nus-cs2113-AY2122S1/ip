package duke.program;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidIndexException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;

public class TaskManager {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";

    private static final String SEPARATOR_SPACE = " ";
    private static final String SEPARATOR_BY = "/by";
    private static final String SEPARATOR_AT = "/at";

    private static final String TASK_TYPE_ICON_TODO = "T";
    private static final String TASK_TYPE_ICON_DEADLINE = "D";
    private static final String TASK_TYPE_ICON_EVENT = "E";
    private static final String ICON_DONE = "X";

    private static final int TODO_DESCRIPTION_START_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
    private static final int EVENT_DESCRIPTION_START_INDEX = 6;
    private static final int LINE_LENGTH = 40;

    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    public static void printLine() {
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void printIndexOutOfBoundsMessage() {
        System.out.println("Sorry bud, you can't check off/delete what is not yet there :/");
    }

    public static void printInvalidDoneOrDeleteMessage() {
        System.out.println("Sorry bud, that's not a valid task number to check off/delete!");
    }

    public static void printAlreadyDoneMessage() {
        System.out.println("Slow down there bud! You've already completed this task!");
    }

    public static void printEmptyIndexAfterDoneMessage() {
        System.out.println("Hey bud, the format for marking off a task is :done [index]");
    }

    public static void printEmptyIndexAfterDeleteMessage() {
        System.out.println("Hey bud, the format for marking off a task is :delete [index]");
    }

    public static void printEmptyDescriptionMessage(String command) {
        System.out.println("Sorry bud! The description after " + command + " can't be blank!");
    }

    public static void printInvalidCommandMessage() {
        System.out.println("Sorry bud, but that command is gibberish to me. I can only read 6 words!");
        System.out.println("The six words are:");
        System.out.printf("list%ndone%ntodo%ndeadline%nevent%nbye%n");
    }

    public static void printInvalidFormatMessage(String command) {
        String timeIndicatorCommand = (command.equals(COMMAND_DEADLINE)) ? "by" : "at";
        System.out.println("Sorry bud, but your formatting is gibberish to me. Here is how it should be formatted: ");
        System.out.println(command + " {description} /" + timeIndicatorCommand + " {timing}");
    }

    public static void printGenericErrorMessage() {
        System.out.println("Oops! Something went wrong :(");
    }

    public void printMarkAsDoneMessage(Task task) {

        String taskType = task.getType();
        String taskStatus = task.getStatusIcon();
        String taskDescription = task.getDescription();

        System.out.println("Nice! I've marked this task as done:");
        printIndividualTask(task, taskType, taskStatus, taskDescription);
    }

    public void printDeletedTaskMessage(Task task) {

        String taskType = task.getType();
        String taskStatus = task.getStatusIcon();
        String taskDescription = task.getDescription();

        System.out.println("Noted. I've removed this task:");
        printIndividualTask(task, taskType, taskStatus, taskDescription);
        System.out.println("Now you have " + (taskCount - 1) + " tasks in the list.");
    }

    private void printIndividualTask(Task task, String taskType, String taskStatus, String taskDescription) {
        if (taskType.equals(TASK_TYPE_ICON_TODO)) {
            System.out.printf("[%s][%s] %s%n", taskType, taskStatus, taskDescription);
        } else if (taskType.equals(TASK_TYPE_ICON_DEADLINE)) {
            String taskByTime = task.getByDateTime();
            System.out.printf("[%s][%s] %s (by: %s)%n", taskType, taskStatus, taskDescription, taskByTime);
        } else if (taskType.equals(TASK_TYPE_ICON_EVENT)) {
            String taskAtTime = task.getStartAndEndTime();
            System.out.printf("[%s][%s] %s (at: %s)%n", taskType, taskStatus, taskDescription, taskAtTime);
        } else {
            printGenericErrorMessage();
        }
    }

    public static void printTaskList(int taskCount, ArrayList<Task> tasks) {

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {

            String taskType = tasks.get(i).getType();
            String taskStatus = tasks.get(i).getStatusIcon();
            String taskDescription = tasks.get(i).getDescription();

            if (taskType.equals(TASK_TYPE_ICON_TODO)) {
                System.out.printf("%d.[%s][%s] %s%n", i+1, taskType, taskStatus, taskDescription);
            } else if (taskType.equals(TASK_TYPE_ICON_DEADLINE)) {
                String taskByTime = tasks.get(i).getByDateTime();
                System.out.printf("%d.[%s][%s] %s (by: %s)%n", i+1, taskType, taskStatus, taskDescription, taskByTime);
            } else if (taskType.equals(TASK_TYPE_ICON_EVENT)) {
                String taskAtTime = tasks.get(i).getStartAndEndTime();
                System.out.printf("%d.[%s][%s] %s (at: %s)%n", i+1, taskType, taskStatus, taskDescription, taskAtTime);
            } else {
                printGenericErrorMessage();
            }
        }
    }

    public void addNewTodo(String rawDescription) throws EmptyDescriptionException {
        String description = rawDescription.trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        tasks.add(new ToDo(description));
        taskCount++;
    }

    public void addNewDeadline(String descriptionAndTime) throws EmptyDescriptionException, InvalidFormatException {

        String[] descriptionAndByTimeArray = descriptionAndTime.split(SEPARATOR_BY);
        String description = descriptionAndByTimeArray[0].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (descriptionAndByTimeArray.length == 1) {
            throw new InvalidFormatException();
        }

        String byDateTime = descriptionAndByTimeArray[1].trim();

        tasks.add(new Deadline(description, byDateTime));
        taskCount++;
    }

    public void addNewEvent(String descriptionAndTime) throws EmptyDescriptionException, InvalidFormatException {

        String[] descriptionAndAtTimeArray = descriptionAndTime.split(SEPARATOR_AT);
        String description = descriptionAndAtTimeArray[0].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (descriptionAndAtTimeArray.length == 1) {
            throw new InvalidFormatException();
        }

        String startAndEndTime = descriptionAndAtTimeArray[1].trim();

        tasks.add(new Event(description, startAndEndTime));
        taskCount++;
    }

    public void printAddedTaskMessage(Task task) {

        String taskType = task.getType();
        String taskDescription = task.getDescription();

        System.out.println("Got it. I've added this task:");
        if (taskType.equals(TASK_TYPE_ICON_TODO)) {
            System.out.printf("[%s][ ] %s%n", taskType, taskDescription);
        } else if (taskType.equals(TASK_TYPE_ICON_DEADLINE)) {
            String byTime = task.getByDateTime();
            System.out.printf("[%s][ ] %s(by:%s)%n", taskType, taskDescription, byTime);
        } else if (taskType.equals((TASK_TYPE_ICON_EVENT))) {
            String atTime = task.getStartAndEndTime();
            System.out.printf("[%s][ ] %s(at:%s)%n", taskType, taskDescription, atTime);
        } else {
            printGenericErrorMessage();
        }

        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void markAsDone(ArrayList<Task> tasks, String[] lineArgs) throws InvalidIndexException, TaskIndexOutOfBoundsException {

        if (lineArgs.length > 2) {
            throw new InvalidIndexException();
        }

        int doneIndex = Integer.parseInt(lineArgs[1]) - 1;

        if (doneIndex >= taskCount || doneIndex < 0) {
            throw new TaskIndexOutOfBoundsException();
        } else {
            if (tasks.get(doneIndex).getStatusIcon().equals(ICON_DONE)) {
                printAlreadyDoneMessage();
            } else {
                tasks.get(doneIndex).setDone();
                printMarkAsDoneMessage(tasks.get(doneIndex));
            }
        }
    }

    public void deleteTask(ArrayList<Task> tasks, String[] lineArgs) throws InvalidIndexException, TaskIndexOutOfBoundsException {

        if (lineArgs.length > 2) {
            throw new InvalidIndexException();
        }

        int deleteIndex = Integer.parseInt(lineArgs[1]) - 1;

        if (deleteIndex >= taskCount || deleteIndex < 0) {
            throw new TaskIndexOutOfBoundsException();
        } else {
            printDeletedTaskMessage(tasks.get(deleteIndex));
            tasks.remove(deleteIndex);
        }

        taskCount--;
    }

    public void parseUserInput() {

        Scanner in = new Scanner(System.in);

        while (true) {
            String line = in.nextLine();
            if (line.equals(COMMAND_BYE)) {
                break;
            }
            printLine();
            String[] lineArgs = line.split(SEPARATOR_SPACE);
            String command = lineArgs[0];
            if (command.equals(COMMAND_LIST)) {
                printTaskList(taskCount, tasks);
            } else if (command.equals(COMMAND_DONE)) {
                markAsDoneWithExceptionHandling(lineArgs);
            } else if (command.equals(COMMAND_DELETE)) {
                deleteTaskWithExceptionHandling(lineArgs);
            } else if (command.equals(COMMAND_TODO)) {
                addNewTodoWithExceptionHandling(line);
            } else if (command.equals(COMMAND_DEADLINE)) {
                addNewDeadlineWithExceptionHandling(line);
            } else if (command.equals(COMMAND_EVENT)) {
                addNewEventWithExceptionHandling(line);
            } else {
                printInvalidCommandMessage();
            }

            printLine();
        }
    }

    private void addNewEventWithExceptionHandling(String line) {
        try {
            addNewEvent(line.substring(EVENT_DESCRIPTION_START_INDEX));
            printAddedTaskMessage(tasks.get(taskCount - 1));
        } catch (EmptyDescriptionException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            printEmptyDescriptionMessage(COMMAND_EVENT);
        } catch (InvalidFormatException e) {
            printInvalidFormatMessage(COMMAND_EVENT);
        }
    }

    private void addNewDeadlineWithExceptionHandling(String line) {
        try {
            addNewDeadline(line.substring(DEADLINE_DESCRIPTION_START_INDEX));
            printAddedTaskMessage(tasks.get(taskCount - 1));
        } catch (EmptyDescriptionException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            printEmptyDescriptionMessage(COMMAND_DEADLINE);
        } catch (InvalidFormatException e) {
            printInvalidFormatMessage(COMMAND_DEADLINE);
        }
    }

    private void addNewTodoWithExceptionHandling(String line) {
        try {
            addNewTodo(line.substring(TODO_DESCRIPTION_START_INDEX));
            printAddedTaskMessage(tasks.get(taskCount - 1));
        } catch (EmptyDescriptionException | StringIndexOutOfBoundsException e) {
            printEmptyDescriptionMessage(COMMAND_TODO);
        }
    }

    private void deleteTaskWithExceptionHandling(String[] lineArgs) {
        try {
            deleteTask(tasks, lineArgs);
        } catch (ArrayIndexOutOfBoundsException e) {
            printEmptyIndexAfterDeleteMessage();
        } catch (InvalidIndexException | NumberFormatException e) {
            printInvalidDoneOrDeleteMessage();
        } catch (TaskIndexOutOfBoundsException e) {
            printIndexOutOfBoundsMessage();
        }
    }

    private void markAsDoneWithExceptionHandling(String[] lineArgs) {
        try {
            markAsDone(tasks, lineArgs);
        } catch (ArrayIndexOutOfBoundsException e) {
            printEmptyIndexAfterDoneMessage();
        } catch (InvalidIndexException | NumberFormatException e) {
            printInvalidDoneOrDeleteMessage();
        } catch (TaskIndexOutOfBoundsException e) {
            printIndexOutOfBoundsMessage();
        }
    }
}

import java.util.Scanner;

public class TaskManager {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";

    private static final String SEPARATOR_SPACE = " ";
    private static final String SEPARATOR_BY = "/by";
    private static final String SEPARATOR_AT = "/at";

    private static final String TASK_TYPE_ICON_TODO = "T";
    private static final String TASK_TYPE_ICON_DEADLINE = "D";
    private static final String TASK_TYPE_ICON_EVENT = "E";
    private static final String ICON_DONE = "X";

    private static final int MAX_TASK_COUNT = 100;
    private static final int TODO_DESCRIPTION_START_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
    private static final int EVENT_DESCRIPTION_START_INDEX = 6;
    private static final int LINE_LENGTH = 40;

    private Task tasks[];
    private int taskCount;

    public TaskManager() {
        this.tasks = new Task[MAX_TASK_COUNT];
        this.taskCount = 0;
    }

    public static void printLine() {
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void printInvalidDoneMessage() {
        System.out.println("Sorry bud, you can't check off what is not yet there :/");
    }

    public static void printAlreadyDoneMessage() {
        System.out.println("Slow down there bud! You've already completed this task!");
    }

    public static void printEmptyIndexAfterDoneMessage() {
        System.out.println("Hey bud, the format for marking off a task is :done [index]");
    }

    public static void printEmptyArgumentMessage(String command) {
        System.out.println("Sorry bud! The description after " + command + " can't be blank!");
    }

    public static void printGenericErrorMessage() {
        System.out.println("Oops! Something went wrong :(");
    }

    public void printMarkAsDoneMessage(Task task) {

        String taskType = task.getType();
        String taskStatus = task.getStatusIcon();
        String taskDescription = task.getDescription();

        System.out.println("Nice! I've marked this task as done:");
        if (taskType.equals(TASK_TYPE_ICON_TODO)) {
            System.out.printf("[%s][%s] %s%n", taskType, taskStatus, taskDescription);
        } else if (taskType.equals(TASK_TYPE_ICON_DEADLINE)) {
            String taskByTime = task.getByDateTime();
            System.out.printf("[%s][%s] %s(by:%s)%n", taskType, taskStatus, taskDescription, taskByTime);
        } else if (taskType.equals(TASK_TYPE_ICON_EVENT)) {
            String taskAtTime = task.getStartAndEndTime();
            System.out.printf("[%s][%s] %s(at:%s)%n", taskType, taskStatus, taskDescription, taskAtTime);
        } else {
            printGenericErrorMessage();
        }
    }

    public static void printTaskList(int taskCount, Task tasks[]) {

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {

            String taskType = tasks[i].getType();
            String taskStatus = tasks[i].getStatusIcon();
            String taskDescription = tasks[i].getDescription();

            if (taskType.equals(TASK_TYPE_ICON_TODO)) {
                System.out.printf("[%s][%s] %s%n", taskType, taskStatus, taskDescription);
            } else if (taskType.equals(TASK_TYPE_ICON_DEADLINE)) {
                String taskByTime = tasks[i].getByDateTime();
                System.out.printf("[%s][%s] %s(by:%s)%n", taskType, taskStatus, taskDescription, taskByTime);
            } else if (taskType.equals(TASK_TYPE_ICON_EVENT)) {
                String taskAtTime = tasks[i].getStartAndEndTime();
                System.out.printf("[%s][%s] %s(at:%s)%n", taskType, taskStatus, taskDescription, taskAtTime);
            } else {
                printGenericErrorMessage();
            }
        }
    }

    public void addNewTodo(String description) {
        tasks[taskCount] = new ToDo(description);
        taskCount++;
    }

    public void addNewDeadline(String line) {
        String[] descriptionAndByTimeArray = line.split(SEPARATOR_BY);
        String byDateTime = descriptionAndByTimeArray[1];
        String description = descriptionAndByTimeArray[0].substring(DEADLINE_DESCRIPTION_START_INDEX);
        tasks[taskCount] = new Deadline(description, byDateTime);
        taskCount++;
    }

    public void addNewEvent(String line) {
        String[] descriptionAndAtTimeArray = line.split(SEPARATOR_AT);
        String startAndEndTime = descriptionAndAtTimeArray[1];
        String description = descriptionAndAtTimeArray[0].substring(EVENT_DESCRIPTION_START_INDEX);
        tasks[taskCount] = new Event(description, startAndEndTime);
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

    public void markAsDone(Task[] tasks, String index) {

        int doneIndex = Integer.parseInt(index) - 1;

        if (doneIndex >= taskCount || doneIndex < 0) {
            printInvalidDoneMessage();
        } else {
            if (tasks[doneIndex].getStatusIcon().equals(ICON_DONE)) {
                printAlreadyDoneMessage();
            } else {
                tasks[doneIndex].setDone();
                printMarkAsDoneMessage(tasks[doneIndex]);
            }
        }
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
                markAsDone(tasks, lineArgs[1]);
            } else if (command.equals(COMMAND_TODO)) {
                addNewTodo(line.substring(TODO_DESCRIPTION_START_INDEX));
                printAddedTaskMessage(tasks[taskCount - 1]);
            } else if (command.equals(COMMAND_DEADLINE)) {
                addNewDeadline(line);
                printAddedTaskMessage(tasks[taskCount - 1]);
            } else if (command.equals(COMMAND_EVENT)) {
                addNewEvent(line);
                printAddedTaskMessage(tasks[taskCount - 1]);
            } else {
                printGenericErrorMessage();
            }

            printLine();
        }
    }
}

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private final static String DONE_TASK_INDICATOR = "^done \\d+";
    private final static String BYE_INDICATOR = "bye";
    private final static String LIST_INDICATOR = "list";
    private final static int TASK_ARRAY_SIZE = 101;

    private final static String TASK_TYPE_TODO = "todo";
    private final static String TASK_TYPE_EVENT = "event";
    private final static String TASK_TYPE_DEADLINE = "deadline";

    private final static String EVENT_DATE_INDICATOR = "/at";
    private final static String DEADLINE_DATE_INDICATOR = "/by";

    // We split the input given by the user with a single white space
    private final static String USER_INPUT_SPLITTER = " ";
    
    private static void welcomeMessage() {
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greetings);
    }

    private static void goodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    private static void printNumberOfTask(int taskNumber) {
        String taskNoun;
        if (taskNumber == 1) {
            taskNoun = "task";
        } else {
            taskNoun = "tasks";
        }
        System.out.println("Now you have " + taskNumber + " " +
                taskNoun +" in the list.\n");
    }

    public static String getTaskType(String userInput) throws InvalidTaskTypeException {
        String[] userInputSplitArray = userInput.split(USER_INPUT_SPLITTER);
        String taskType = userInputSplitArray[0];
        if (taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event")) {
            return taskType;
        } else {
            throw new InvalidTaskTypeException();
        }
    }

    public static String getTaskDescriptor(String userInput) throws EmptyDescriptionException {
        String[] userInputSplitArray = userInput.split(USER_INPUT_SPLITTER);
        if (userInputSplitArray.length < 2) {
            String taskType = userInputSplitArray[0];
            throw new EmptyDescriptionException(taskType);
        } else {
            String taskDescription = "";
            for (int i = 1; i < userInputSplitArray.length; i++) {
                taskDescription = taskDescription + " " + userInputSplitArray[i];
            }
            taskDescription = taskDescription.stripLeading();
            return taskDescription;
        }
    }

    public static String getTaskFromTaskDescription(String taskDescription, String dateIndicator)
            throws InvalidDateIndicatorException {
        String[] taskDescriptionSplitByDate = taskDescription.split(dateIndicator);
        if (taskDescriptionSplitByDate.length < 2) {
            throw new InvalidDateIndicatorException();
        } else {
            String task = taskDescriptionSplitByDate[0];
            return task;
        }
    }

    public static String getDateFromTaskDescription(String taskDescription, String dateIndicator)
            throws InvalidDateIndicatorException {
        String[] taskDescriptionSplitByDate = taskDescription.split(dateIndicator);
        if (taskDescriptionSplitByDate.length < 2) {
            throw new InvalidDateIndicatorException();
        } else {
            String date = taskDescriptionSplitByDate[1];
            return date;
        }
    }

    public static void addTaskToTaskArray(String userInput, int taskNumber, Task[] taskArray)
            throws InvalidTaskTypeException, EmptyDescriptionException, InvalidDateIndicatorException {
        String taskType = getTaskType(userInput);
        String taskDescription = getTaskDescriptor(userInput);

        switch (taskType) {
            case TASK_TYPE_TODO:
                Todo todo = new Todo(taskDescription);
                taskArray[taskNumber] = todo;
                break;
            case TASK_TYPE_DEADLINE:
                String deadlineTask = getTaskFromTaskDescription(taskDescription, DEADLINE_DATE_INDICATOR);
                String deadlineDate = getDateFromTaskDescription(taskDescription, DEADLINE_DATE_INDICATOR);
                Deadline deadline = new Deadline(deadlineTask, deadlineDate);
                taskArray[taskNumber] = deadline;
                break;
            case TASK_TYPE_EVENT:
                String eventTask = getTaskFromTaskDescription(taskDescription, EVENT_DATE_INDICATOR);
                String eventDate = getDateFromTaskDescription(taskDescription, EVENT_DATE_INDICATOR);
                Event event = new Event(eventTask, eventDate);
                taskArray[taskNumber] = event;
                break;
        }

        printNumberOfTask(taskNumber);
    }

    public static void markTaskAsDone(String userInput, Task[] taskArray) {
        String[] userInputArray = userInput.split(USER_INPUT_SPLITTER);
        int doneTaskNumber = Integer.parseInt(userInputArray[1]);
        Task taskDone = taskArray[doneTaskNumber];
        taskDone.setTaskAsDone();
    }

    public static void getAllTask(Task[] taskArray, int taskNumber) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskNumber; i ++) {
            Task currentTask = taskArray[i];
            System.out.println(i + "." + currentTask.toString());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        welcomeMessage();
        Scanner scanner = new Scanner(System.in);

        int taskNumber = 1;
        Task[] taskArray = new Task[TASK_ARRAY_SIZE];
        Pattern doneTaskPattern = Pattern.compile(DONE_TASK_INDICATOR);

        while (true) {
            String userInput = scanner.nextLine();
            Matcher matcher = doneTaskPattern.matcher(userInput);
            boolean taskIsDone = matcher.find();

            if (userInput.equals(BYE_INDICATOR)) {
                break;
            } else if (userInput.equals(LIST_INDICATOR)) {
                getAllTask(taskArray, taskNumber);
            } else if (taskIsDone) {
                markTaskAsDone(userInput, taskArray);
            } else {
                try {
                    addTaskToTaskArray(userInput, taskNumber, taskArray);
                    taskNumber++;
                } catch (EmptyDescriptionException e) {
                    e.printExceptionMessage();
                } catch (InvalidTaskTypeException e) {
                    e.printExceptionMessage();
                } catch (InvalidDateIndicatorException e) {
                    e.printExceptionMessage();
                }
            }
        }
        goodbyeMessage();
    }
}

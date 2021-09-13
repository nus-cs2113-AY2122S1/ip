import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private final static String DONE_TASK_INDICATOR = "^done \\d+";
    private final static String DELETE_TASK_INDICATOR = "^delete \\d+";

    private final static String BYE_INDICATOR = "bye";
    private final static String LIST_INDICATOR = "list";

    private final static String TASK_TYPE_TODO = "todo";
    private final static String TASK_TYPE_EVENT = "event";
    private final static String TASK_TYPE_DEADLINE = "deadline";

    private final static String EVENT_DATE_INDICATOR = "/at";
    private final static String DEADLINE_DATE_INDICATOR = "/by";

    // We split the input given by the user with a single white space
    private final static String USER_INPUT_SPLITTER = " ";

    private final static ArrayList<Task> taskArrayList = new ArrayList<Task>();
    
    private static void welcomeMessage() {
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greetings);
    }

    private static void goodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    private static void printNumberOfTask() {
        int numberOfTasks = taskArrayList.size();
        String taskNoun;
        if (numberOfTasks == 1 || numberOfTasks == 0) {
            taskNoun = "task";
        } else {
            taskNoun = "tasks";
        }
        System.out.println("Now you have " + numberOfTasks + " " +
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
            // We split the user input using white spaces,
            // we then combind the userInputArray back into one string
            // starting from index 1 to get the task description
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

        // If the taskDescriptionSplitByDate has length less than 2,
        // it means that the date indicator is not present.
        // We throw the InvalidDateIndicatorException in this case.
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

    public static void addTaskToTaskArray(String userInput, ArrayList<Task> taskArrayList)
            throws InvalidTaskTypeException, EmptyDescriptionException, InvalidDateIndicatorException {
        String taskType = getTaskType(userInput);
        String taskDescription = getTaskDescriptor(userInput);

        switch (taskType) {
            case TASK_TYPE_TODO:
                Todo todo = new Todo(taskDescription);
                taskArrayList.add(todo);
                break;
            case TASK_TYPE_DEADLINE:
                String deadlineTask = getTaskFromTaskDescription(taskDescription, DEADLINE_DATE_INDICATOR);
                String deadlineDate = getDateFromTaskDescription(taskDescription, DEADLINE_DATE_INDICATOR);
                Deadline deadline = new Deadline(deadlineTask, deadlineDate);
                taskArrayList.add(deadline);
                break;
            case TASK_TYPE_EVENT:
                String eventTask = getTaskFromTaskDescription(taskDescription, EVENT_DATE_INDICATOR);
                String eventDate = getDateFromTaskDescription(taskDescription, EVENT_DATE_INDICATOR);
                Event event = new Event(eventTask, eventDate);
                taskArrayList.add(event);
                break;
        }
        printNumberOfTask();
    }

    public static void markTaskAsDone(String userInput, ArrayList<Task> taskArrayList) {
        String[] userInputArray = userInput.split(USER_INPUT_SPLITTER);
        int doneTaskNumber = Integer.parseInt(userInputArray[1]);

        // We take doneTaskNumber and subtract 1 from it
        // since ArrayList's index starts from 1
        doneTaskNumber = doneTaskNumber - 1;
        Task taskDone = taskArrayList.get(doneTaskNumber);
        taskDone.setTaskAsDone();
        System.out.println();
    }

    public static void getAllTask(ArrayList<Task> taskArrayList) {
        System.out.println("Here are the tasks in your list:");
        int taskNumber;
        for (int i = 0; i < taskArrayList.size(); i ++) {
            Task currentTask = taskArrayList.get(i);
            taskNumber = i + 1;
            System.out.println(taskNumber + "." + currentTask.toString());
        }
        System.out.println();
    }

    public static void deleteTaskMessage(Task taskToDelete) {
        System.out.println("Noted. I've removed this task:\n" +
                "  " + taskToDelete.toString());
    }

    public static void deleteTask(String userInput, ArrayList<Task> taskArrayList) {
        String[] userInputArray = userInput.split(USER_INPUT_SPLITTER);
        int deleteTaskNumber = Integer.parseInt(userInputArray[1]);
        int indexOfTaskToDelete = deleteTaskNumber - 1;

        Task taskToDelete = taskArrayList.get(indexOfTaskToDelete);
        taskArrayList.remove(indexOfTaskToDelete);
        deleteTaskMessage(taskToDelete);
        printNumberOfTask();
    }

    public static void printInvalidTaskNumberProvided() {
        System.out.println("Task number provided does not exist!\n");
    }

    public static void main(String[] args) {
        welcomeMessage();
        Scanner scanner = new Scanner(System.in);
        Pattern doneTaskPattern = Pattern.compile(DONE_TASK_INDICATOR);
        Pattern deleteTaskPattern = Pattern.compile(DELETE_TASK_INDICATOR);

        while (true) {
            String userInput = scanner.nextLine();
            Matcher doneMatcher = doneTaskPattern.matcher(userInput);
            boolean taskIsDone = doneMatcher.find();

            Matcher deleteMatcher = deleteTaskPattern.matcher(userInput);
            boolean deleteTask = deleteMatcher.find();

            if (userInput.equals(BYE_INDICATOR)) {
                break;
            } else if (userInput.equals(LIST_INDICATOR)) {
                getAllTask(taskArrayList);
            } else if (taskIsDone) {
                try {
                    markTaskAsDone(userInput, taskArrayList);
                } catch (IndexOutOfBoundsException e) {
                    printInvalidTaskNumberProvided();
                }
            } else if (deleteTask) {
                try {
                    deleteTask(userInput, taskArrayList);
                } catch (IndexOutOfBoundsException e) {
                    printInvalidTaskNumberProvided();
                }
            } else {
                try {
                    addTaskToTaskArray(userInput, taskArrayList);
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

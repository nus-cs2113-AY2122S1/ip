import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static void welcomeMessage() {
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greetings);
    }

    private static void goodbyeMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }

    public static void addTaskToTaskList(String userInput, int taskNumber, Task[] taskList) {
        String[] userInputSplitArray = userInput.split(" ");
        String taskType = userInputSplitArray[0];

        String taskDescription = "";
        for (int i = 1; i < userInputSplitArray.length; i++) {
            taskDescription = taskDescription + " " + userInputSplitArray[i];
        }
        taskDescription = taskDescription.stripLeading();

        if (taskType.equals("todo")) {
            Todo todo = new Todo(taskDescription);
            taskList[taskNumber] = todo;
        } else if (taskType.equals("deadline")) {
            String[] deadlineSplitByDate = taskDescription.split("/by");
            String deadlineTask = deadlineSplitByDate[0];
            String deadlineDate = deadlineSplitByDate[1];
            Deadline deadline = new Deadline(deadlineTask, deadlineDate);
            taskList[taskNumber] = deadline;
        } else if (taskType.equals("event")) {
            String[] eventSplitByDate = taskDescription.split("/at");
            String eventTask = eventSplitByDate[0];
            String eventDate = eventSplitByDate[1];
            Event event = new Event(eventTask, eventDate);
            taskList[taskNumber] = event;
        }

        System.out.println("Now you have " + taskNumber + " tasks in the list.\n");
    }

    public static void markTaskAsDone(String userInput, Task[] taskList) {
        String[] userInputArray = userInput.split(" ");
        int doneTaskNumber = Integer.parseInt(userInputArray[1]);
        Task taskDone = taskList[doneTaskNumber];
        taskDone.setTaskAsDone();
    }

    public static void getAllTask(Task[] taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.length; i ++) {
            Task currentTask = taskList[i];
            if (currentTask == null) {
                break;
            } else {
                System.out.println(i + "." + currentTask.toString());
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        welcomeMessage();
        Scanner scanner = new Scanner(System.in);

        int taskNumber = 1;
        Task[] taskList = new Task[101];
        Pattern doneTaskPattern = Pattern.compile("^done \\d+");

        while (true) {
            String userInput = scanner.nextLine();
            Matcher matcher = doneTaskPattern.matcher(userInput);
            boolean doneTask = matcher.find();

            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                getAllTask(taskList);
            } else if (doneTask) {
                markTaskAsDone(userInput, taskList);
            } else {
                addTaskToTaskList(userInput, taskNumber, taskList);
                taskNumber++;
            }
        }
        goodbyeMessage();
    }
}

import java.util.Scanner;

public class TaskManager {
    private static final int MAX_SIZE = 100;

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DONE = "done";

    private static final String SEPARATOR_SPACE = " ";
    private static final String SEPARATOR_BY = " /by ";
    private static final String SEPARATOR_AT = " /at ";

    private static final String DIVIDER = "    ____________________________________________________________";

    private Task[] tasks;
    private int numberOfTasks;

    public TaskManager() {
        this.tasks = new Task[MAX_SIZE];
        this.numberOfTasks = 0;
    }

    public void processUserInput() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String userInput = sc.nextLine();
            if(userInput.equals(COMMAND_BYE)) {
                break;
            }

            if(userInput.equals(COMMAND_LIST)) {
                printAllTasks();
            } else if (userInput.startsWith(COMMAND_DONE)) {
                String[] inputs = userInput.split(SEPARATOR_SPACE);
                int taskDoneNumber = Integer.parseInt(inputs[1]);

                setTaskDone(taskDoneNumber - 1);
            } else {
                addTask(userInput);
            }
        }
    }

    private void addTask(String taskInput) {
        if(taskInput.startsWith(COMMAND_TODO)) {
            addTodoTask(taskInput);
        } else if (taskInput.startsWith(COMMAND_DEADLINE)) {
            addDeadlineTask(taskInput);
        } else if(taskInput.startsWith(COMMAND_EVENT)) {
            addEventTask(taskInput);
        } else {
            addNormalTask(taskInput);
        }

        printAddTaskMessage();
    }

    private void addNormalTask(String taskInput) {
        tasks[numberOfTasks] = new Task(taskInput);
        numberOfTasks++;
    }

    private void addTodoTask(String taskInput) {
        String taskName = taskInput.substring(5);
        tasks[numberOfTasks] = new Todo(taskName);
        numberOfTasks++;
    }

    private void addDeadlineTask(String taskInput) {
        String taskDescriptionAndDeadline = taskInput.substring(9);
        String[] taskDescriptionAndDeadlineArray = taskDescriptionAndDeadline.split(SEPARATOR_BY);
        String taskDescription = taskDescriptionAndDeadlineArray[0];
        String deadline = taskDescriptionAndDeadlineArray[1];
        tasks[numberOfTasks] = new Deadline(taskDescription, deadline);
        numberOfTasks++;
    }

    private void addEventTask(String taskInput) {
        String taskDescriptionAndStartTime = taskInput.substring(6);
        String[] taskDescriptionAndStartTimeArray = taskDescriptionAndStartTime.split(SEPARATOR_AT);
        String taskDescription = taskDescriptionAndStartTimeArray[0];
        String deadline = taskDescriptionAndStartTimeArray[1];
        tasks[numberOfTasks] = new Event(taskDescription, deadline);
        numberOfTasks++;
    }

    private void setTaskDone(int taskIndex) {
        if(taskIndex < 0) {
            printTaskIndexTooSmallMessage();
            return;
        }
        if(taskIndex > numberOfTasks - 1){
            printTaskIndexTooBigMessage();
            return;
        }

        tasks[taskIndex].markAsDone();
        printMarkAsDoneMessage(taskIndex);
    }

    private void printAddTaskMessage() {
        printHorizontalLine();
        System.out.println("     Got it. I've added this task:\n"
                + "      " + tasks[numberOfTasks - 1]
                + "\n     Now you have " + numberOfTasks
                + (numberOfTasks == 1? " task" : " tasks")
                + " in the list.");
        printHorizontalLine();
    }

    private void printAllTasks() {
        printHorizontalLine();
        for(int i = 0; i < numberOfTasks; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i]);
        }
        printHorizontalLine();
    }

    private void printTaskIndexTooSmallMessage() {
        printHorizontalLine();
        System.out.println("     Please enter a valid task number!");
        printHorizontalLine();
    }

    private void printTaskIndexTooBigMessage() {
        printHorizontalLine();
        System.out.println("     There is only " + numberOfTasks
                + " item(s) in the list!");
        printHorizontalLine();
    }

    private void printMarkAsDoneMessage(int taskIndex) {
        printHorizontalLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + tasks[taskIndex]);
        printHorizontalLine();
    }

    private void printHorizontalLine() {
        System.out.println(DIVIDER);
    }
}

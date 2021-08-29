import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    // Constants
    public static final int MAX_TASKS = 100;
    public static final String EXIT_STRING = "bye";
    public static final String LIST_STRING = "list";
    public static final String DONE_STRING = "done";
    public static final String TODO_STRING = "todo";
    public static final String DEADLINE_STRING = "deadline";
    public static final String EVENT_STRING = "event";
    public static final String OUTPUT_DIVIDER = "____________________________________________________________";
    public static final String MESSAGE_COMMAND_WRONG_FORMAT = " Your command is of the wrong format!";
    public static final String MESSAGE_DONE_CORRECT_FORMAT = " To mark a task as done, enter \"done {ID of done task}\".";
    public static final String MESSAGE_NUMERIC_TASK_ID = " Enter a number for your task ID.";
    public static final String MESSAGE_TASK_NOT_IN_LIST = " Sorry, the task is not in the list! Try again.";
    public static final String MESSAGE_TASK_MARKED_DONE = " Great! I have marked the following task as done:";
    public static final String MESSAGE_CORRECT_TODO_FORMAT = " To add a todo, enter \"todo {description}\".";
    public static final String MESSAGE_CORRECT_DEADLINE_FORMAT = " To add a deadline, enter \"deadline {description} /by {deadline}\".";
    public static final String MESSAGE_COMMAND_LACKS_ARG = " Your command lacks an argument!";
    public static final String MESSAGE_CORRECT_EVENT_FORMAT = " To add an event, enter \"event {description} /at {event time}\".";
    public static final String MESSAGE_INVALID_COMMAND = " Please enter a valid command!";
    public static final String MESSAGE_TASK_ADDED = " I have added a task:";
    public static final String DEADLINE_PREFIX = "/by";
    public static final String EVENT_PREFIX = "/at";
    public static final String MESSAGE_GREET_USER = " Hey there! I'm Duke." + System.lineSeparator() + " How may I help you?";
    public static final String MESSAGE_GOODBYE = " Goodbye! Hope to see you again soon.";
    public static final String MESSAGE_LIST_TASKS = " Here are the tasks in your list:";


    public static void printLogoAndGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_GREET_USER);
        System.out.println(OUTPUT_DIVIDER);
    }

    public static void printTaskList(Task[] tasks) {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_LIST_TASKS);
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i].toString());
        }
        System.out.println(OUTPUT_DIVIDER);
    }

    public static void printGoodbye() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(OUTPUT_DIVIDER);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void markAndPrintTaskDone(Task[] tasks, int taskCount, String doneTask) {
        String[] doneSentence = doneTask.split(" ");

        // Checks if done command entered does not follow the correct format of "done {task ID}".
        if (doneSentence.length != 2) {
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
            System.out.println(MESSAGE_DONE_CORRECT_FORMAT);
            System.out.println(OUTPUT_DIVIDER);
            return;
        }

        // Checks if the task ID entered is numeric.
        if (!isNumeric(doneSentence[1])) {
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
            System.out.println(MESSAGE_NUMERIC_TASK_ID);
            System.out.println(OUTPUT_DIVIDER);
            return;
        }

        int taskToMarkDone = Integer.parseInt(doneSentence[1]);
        // Makes sure that the task being mark done is in the task list.
        if (taskToMarkDone > taskCount || taskToMarkDone <= 0) {
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_TASK_NOT_IN_LIST);
            System.out.println(OUTPUT_DIVIDER);
        } else {
            tasks[taskToMarkDone - 1].setDone(true);
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_TASK_MARKED_DONE);
            System.out.println("   " + tasks[taskToMarkDone - 1].toString());
            System.out.println(OUTPUT_DIVIDER);
        }
    }

    public static boolean canAddAndPrintTodo(Task[] tasks, int taskCount, String addedTodo) {
        String[] splitTodo = addedTodo.split("\\s+", 2);

        // Makes sure that the todo entered is of the format "todo {description}"
        if(splitTodo.length != 2) {
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
            System.out.println(MESSAGE_CORRECT_TODO_FORMAT);
            System.out.println(OUTPUT_DIVIDER);
            return false;
        }

        String todoDescription = splitTodo[1];
        Todo newTodo = new Todo(todoDescription);
        tasks[taskCount] = newTodo;
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_TASK_ADDED);
        System.out.println("   " + newTodo.toString());
        System.out.println(" You now have " + (taskCount + 1) + " task(s) in the list.");
        System.out.println(OUTPUT_DIVIDER);
        return true;
    }

    public static boolean canAddAndPrintDeadline(Task[] tasks, int taskCount, String addedDeadline) {
        String[] splitDeadline = addedDeadline.split("\\s+", 2);
        boolean wasAdded = false;

        // Makes sure that the deadline entered is of the format "deadline {description} /by {deadline}"
        if(splitDeadline.length != 2) {
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
            System.out.println(MESSAGE_CORRECT_DEADLINE_FORMAT);
            System.out.println(OUTPUT_DIVIDER);
            return wasAdded;
        }

        String[] deadlineDescriptionAndDeadline = splitDeadline[1].split(DEADLINE_PREFIX, 2);
        if(deadlineDescriptionAndDeadline.length != 2) {
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
            System.out.println(MESSAGE_CORRECT_DEADLINE_FORMAT);
            System.out.println(OUTPUT_DIVIDER);
            return wasAdded;
        }

        String deadlineDescription = deadlineDescriptionAndDeadline[0].trim();
        String deadlineDeadline = deadlineDescriptionAndDeadline[1].trim();
        boolean isValidDeadline = !deadlineDescription.isEmpty() && !deadlineDeadline.isEmpty();
        // deadline with valid format is added to task list
        if (isValidDeadline) {
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDeadline);
            tasks[taskCount] = newDeadline;
            wasAdded = true;
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_TASK_ADDED);
            System.out.println("   " + newDeadline.toString());
            System.out.println(" You now have " + (taskCount + 1) + " task(s) in the list.");
            System.out.println(OUTPUT_DIVIDER);
        } else {
            wasAdded = false;
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_COMMAND_LACKS_ARG);
            System.out.println(MESSAGE_CORRECT_DEADLINE_FORMAT);
            System.out.println(OUTPUT_DIVIDER);
        }
        return wasAdded;
    }

    public static boolean canAddAndPrintEvent(Task[] tasks, int taskCount, String addedEvent) {
        String[] splitEvent = addedEvent.split("\\s+", 2);
        boolean wasAdded = false;

        // Makes sure that the event entered is of the format "event {description} /at {event time}"
        if(splitEvent.length != 2) {
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
            System.out.println(MESSAGE_CORRECT_EVENT_FORMAT);
            System.out.println(OUTPUT_DIVIDER);
            return wasAdded;
        }

        String[] eventDescriptionAndTime = splitEvent[1].split(EVENT_PREFIX, 2);
        if (eventDescriptionAndTime.length != 2) {
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_COMMAND_WRONG_FORMAT);
            System.out.println(MESSAGE_CORRECT_EVENT_FORMAT);
            System.out.println(OUTPUT_DIVIDER);
            return wasAdded;
        }

        String eventDescription = eventDescriptionAndTime[0].trim();
        String eventTime = eventDescriptionAndTime[1].trim();
        boolean isValidEvent = !eventDescription.isEmpty() && !eventTime.isEmpty();
        // event with valid format is added to task list
        if (isValidEvent) {
            Event newEvent = new Event(eventDescription, eventTime);
            tasks[taskCount] = newEvent;
            wasAdded = true;
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_TASK_ADDED);
            System.out.println("   " + newEvent.toString());
            System.out.println(" You now have " + (taskCount + 1) + " task(s) in the list.");
            System.out.println(OUTPUT_DIVIDER);
        } else {
            wasAdded = false;
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_COMMAND_LACKS_ARG);
            System.out.println(MESSAGE_CORRECT_EVENT_FORMAT);
            System.out.println(OUTPUT_DIVIDER);
        }
        return wasAdded;
    }

    public static void printInvalidCommandMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_INVALID_COMMAND);
        System.out.println(OUTPUT_DIVIDER);
    }

    public static void main(String[] args) {
        printLogoAndGreet();

        String line;
        Scanner in = new Scanner(System.in);

        int taskCount = 0;
        Task[] tasks = new Task[MAX_TASKS];
        line = in.nextLine();

        while (!line.trim().equalsIgnoreCase(EXIT_STRING)) {
            if (line.trim().equalsIgnoreCase(LIST_STRING)) {
                printTaskList(Arrays.copyOf(tasks, taskCount));
            } else if (line.trim().toLowerCase().startsWith(DONE_STRING)) {
                markAndPrintTaskDone(tasks, taskCount, line);
            } else if (line.trim().toLowerCase().startsWith(TODO_STRING)){
                if (canAddAndPrintTodo(tasks, taskCount, line)) {
                    taskCount++;
                }
            } else if (line.trim().toLowerCase().startsWith(DEADLINE_STRING)) {
                if (canAddAndPrintDeadline(tasks, taskCount, line)) {
                    taskCount++;
                }
            } else if (line.trim().toLowerCase().startsWith(EVENT_STRING)) {
                if (canAddAndPrintEvent(tasks, taskCount, line)) {
                    taskCount++;
                }
            } else {
                printInvalidCommandMessage();
            }
            line = in.nextLine();
        }
        printGoodbye();
    }
}

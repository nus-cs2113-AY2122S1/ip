import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printLogoAndGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hey there! I'm Duke.");
        System.out.println(" How may I help you?");
        System.out.println("____________________________________________________________");
    }

    public static void printTaskList(Task[] tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void printGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Goodbye! Hope to see you again soon.");
        System.out.println("____________________________________________________________");
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
            System.out.println("____________________________________________________________");
            System.out.println(" Your command is of the wrong format!");
            System.out.println(" To mark a task as done, enter \"done {ID of done task}\".");
            System.out.println("____________________________________________________________");
            return;
        }

        // Checks if the task ID entered is numeric.
        if (!isNumeric(doneSentence[1])) {
            System.out.println("____________________________________________________________");
            System.out.println(" Your command is of the wrong format!");
            System.out.println(" Enter a number for your task ID.");
            System.out.println("____________________________________________________________");
            return;
        }

        int taskToMarkDone = Integer.parseInt(doneSentence[1]);
        // Makes sure that the task being mark done is in the task list.
        if (taskToMarkDone > taskCount || taskToMarkDone <= 0) {
            System.out.println("____________________________________________________________");
            System.out.println(" Sorry, the task is not in the list! Try again.");
            System.out.println("____________________________________________________________");
        } else {
            tasks[taskToMarkDone - 1].setDone(true);
            System.out.println("____________________________________________________________");
            System.out.println(" Great! I have marked the following task as done: ");
            System.out.println("   " + tasks[taskToMarkDone - 1].toString());
            System.out.println("____________________________________________________________");
        }
    }

    public static boolean canAddAndPrintTodo(Task[] tasks, int taskCount, String addedTodo) {
        String[] splitTodo = addedTodo.split("\\s+", 2);

        // Makes sure that the todo entered is of the format "todo {description}"
        if(splitTodo.length != 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Your command is of the wrong format!");
            System.out.println(" To add a todo, enter \"todo {description}\".");
            System.out.println("____________________________________________________________");
            return false;
        }

        String todoDescription = splitTodo[1];
        Todo newTodo = new Todo(todoDescription);
        tasks[taskCount] = newTodo;
        System.out.println("____________________________________________________________");
        System.out.println(" I have added a task: ");
        System.out.println("   " + newTodo.toString());
        System.out.println(" You now have " + (taskCount + 1) + " task(s) in the list.");
        System.out.println("____________________________________________________________");
        return true;
    }

    public static boolean canAddAndPrintDeadline(Task[] tasks, int taskCount, String addedDeadline) {
        String[] splitDeadline = addedDeadline.split("\\s+", 2);
        boolean wasAdded = false;

        // Makes sure that the deadline entered is of the format "deadline {description} /by {deadline}"
        if(splitDeadline.length != 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Your command is of the wrong format!");
            System.out.println(" To add a deadline, enter \"deadline {description} /by {deadline}\".");
            System.out.println("____________________________________________________________");
            return wasAdded;
        }

        String[] deadlineDescriptionAndDeadline = splitDeadline[1].split("/by", 2);
        if(deadlineDescriptionAndDeadline.length != 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Your command is of the wrong format!");
            System.out.println(" To add a deadline, enter \"deadline {description} /by {deadline}\".");
            System.out.println("____________________________________________________________");
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
            System.out.println("____________________________________________________________");
            System.out.println(" I have added a task: ");
            System.out.println("   " + newDeadline.toString());
            System.out.println(" You now have " + (taskCount + 1) + " task(s) in the list.");
            System.out.println("____________________________________________________________");
        } else {
            wasAdded = false;
            System.out.println("____________________________________________________________");
            System.out.println(" Your command lacks an argument!");
            System.out.println(" To add a deadline, enter \"deadline {description} /by {deadline}\".");
            System.out.println("____________________________________________________________");
        }
        return wasAdded;
    }

    public static boolean canAddAndPrintEvent(Task[] tasks, int taskCount, String addedEvent) {
        String[] splitEvent = addedEvent.split("\\s+", 2);
        boolean wasAdded = false;

        // Makes sure that the event entered is of the format "event {description} /at {event time}"
        if(splitEvent.length != 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Your command is of the wrong format!");
            System.out.println(" To add an event, enter \"event {description} /at {event time}\".");
            System.out.println("____________________________________________________________");
            return wasAdded;
        }

        String[] eventDescriptionAndTime = splitEvent[1].split("/at", 2);
        if (eventDescriptionAndTime.length != 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Your command is of the wrong format!");
            System.out.println(" To add an event, enter \"event {description} /at {event time}\".");
            System.out.println("____________________________________________________________");
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
            System.out.println("____________________________________________________________");
            System.out.println(" I have added a task: ");
            System.out.println("   " + newEvent.toString());
            System.out.println(" You now have " + (taskCount + 1) + " task(s) in the list.");
            System.out.println("____________________________________________________________");
        } else {
            wasAdded = false;
            System.out.println("____________________________________________________________");
            System.out.println(" Your command lacks an argument!");
            System.out.println(" To add an event, enter \"event {description} /at {event time}\".");
            System.out.println("____________________________________________________________");
        }
        return wasAdded;
    }

    public static void printInvalidCommandMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Please enter a valid command!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        printLogoAndGreet();

        String line;
        Scanner in = new Scanner(System.in);

        int taskCount = 0;
        Task[] tasks = new Task[100];
        line = in.nextLine();

        while (!line.trim().equalsIgnoreCase("bye")) {
            if (line.trim().equalsIgnoreCase("list")) {
                printTaskList(Arrays.copyOf(tasks, taskCount));
            } else if (line.trim().toLowerCase().startsWith("done")) {
                markAndPrintTaskDone(tasks, taskCount, line);
            } else if (line.trim().toLowerCase().startsWith("todo")){
                if (canAddAndPrintTodo(tasks, taskCount, line)) {
                    taskCount++;
                }
            } else if (line.trim().toLowerCase().startsWith("deadline")) {
                if (canAddAndPrintDeadline(tasks, taskCount, line)) {
                    taskCount++;
                }
            } else if (line.trim().toLowerCase().startsWith("event")) {
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

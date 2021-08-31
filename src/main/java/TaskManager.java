public class TaskManager {
    private final Task[] tasks = new Task[100];
    private int taskCount = 0;

    private final String bufferLine = "____________________________________________________________\n";
    private final String invalidInput = "____________________________________________________________\n"
            +  " Oops! Looks like I can't read that yet! Please input a valid command.\n"
            + "____________________________________________________________\n";

    public void printList() {
        System.out.println(bufferLine);
        System.out.println(" Here are the tasks in your list:");
        int i = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(" " + i + ". " + task.listTask());
            i = i + 1;
        }
        System.out.println(bufferLine);
    }

    public void printWelcomeMessage() {
        System.out.println(bufferLine);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);
    }

    public void printEndMessage() {
        String bye = "____________________________________________________________\n"
                +  " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(bye);
    }

    public String[] decodeInput(String input) {
        return input.split(" ");
    }

    public void crossOff(String[] inputWords) {
        if (inputWords.length != 2) {
            System.out.println(invalidInput);
            return;
        }
        int taskIndex = Integer.parseInt(inputWords[1]) - 1;
        if (taskIndex >= taskCount) {
            System.out.println(invalidInput);
            return;
        }
        tasks[taskIndex].setDone();
        String doneMessage = "____________________________________________________________\n"
                + " Nice! I've marked this task as done: \n"
                + " [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].description + "\n"
                + "____________________________________________________________\n";
        System.out.println(doneMessage);
    }

    String description;
    private void printAdditionMessage() {
        String addition = "____________________________________________________________\n"
                + " Gotcha! I've added this task: \n"
                + "   " + tasks[taskCount - 1].listTask() + "\n"
                + " Now you have " + (taskCount) + " tasks in the list.\n"
                + "____________________________________________________________\n";
        System.out.println(addition);
    }

    public void addToDo(String input, String[] inputWords) {
        if (inputWords.length <= 1) {
            System.out.println(invalidInput);
            return;
        }
        description = input.substring(5);
        tasks[taskCount] = new ToDo(description);
        taskCount = taskCount + 1;
        printAdditionMessage();
    }

    public void addDeadline(String input, String[] inputWords) {
        if (!input.contains("/by") || input.endsWith("/by")) {
            System.out.println(invalidInput);
            return;
        }
        String endDate;
        description = input.substring(9, input.indexOf("/by") - 1);
        endDate = input.substring(input.indexOf("/by") + 4);
        tasks[taskCount] = new Deadline(description, endDate);
        taskCount = taskCount + 1;
        printAdditionMessage();
    }

    public void addEvent(String input, String[] inputWords) {
        if (!input.contains("/at") || input.endsWith("/at")) {
            System.out.println(invalidInput);
            return;
        }
        String startEndTime;
        description = input.substring(6, input.indexOf("/at") - 1);
        startEndTime = input.substring(input.indexOf("/at") + 4);
        tasks[taskCount] = new Event(description, startEndTime);
        taskCount = taskCount + 1;
        printAdditionMessage();
    }
}

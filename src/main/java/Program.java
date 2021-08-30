import java.util.Objects;

public class Program {
    private boolean canTerminateHal = false;    //when true, the program exits
    private static Task[] listTasks = new Task[999];
    private static int numItems;

    private static int DEADLINE_INDEX = 9;
    private static int EVENT_INDEX = 5;
    private static int TODO_INDEX = 4;
    private static int TASK_STRING_OFFSET = 3;

    public static final String LINE_BREAK_SINGLE = "____________________________________________________________";
    public static final String TASK_ADDED_DONE_TEXT = "Got it! I've added this task: ";
    public static final String DONE_TASK_ERROR_MESSAGE = "No such task exist! Are you sure you keyed in the correct number?";
    public static final String DONE_TASK_SUCCESS_MESSAGE = "Nice! I've marked this task as done:";
    public static final String PRINT_ERROR_MESSAGE = "Your input does not follow my format!\n" +
            "Read properly and type it again!";
    public static final String ENTER_COMMAND_TEXT = "Enter command: ";

    public Program() {
        this.numItems = 0;
    }

    public static int getNumItems() {
        return numItems;
    }

    //function to parse input text by the user and run corresponding function
    public void executeTask(String string) {
        if (Objects.equals(string, "list")) {
            listAllTasks();
        } else if (Objects.equals(string, "bye")) {
            this.executeBye();
        } else if (string.contains("done")) {
            this.executeDoneTask(string);
        } else if (string.contains("deadline")) {
            this.addDeadlineTask(string);
        } else if (string.contains("event")) {
            this.addEventTask(string);
        } else {
            addToDoTask(string);
        }
    }

    //function to add a new deadline task
    public static void addDeadlineTask(String deadlineTask) {
        System.out.println(LINE_BREAK_SINGLE);

        //check if string contains '/by' tag
        if (!deadlineTask.contains("/by")) {
            System.out.println(PRINT_ERROR_MESSAGE);
            System.out.println(LINE_BREAK_SINGLE);
            return;
        }

        String description = deadlineTask.substring(DEADLINE_INDEX, deadlineTask.indexOf('/')).trim();
        String by = deadlineTask.substring(deadlineTask.indexOf("/by") + TASK_STRING_OFFSET).trim();
        Deadline newDeadlineTask = new Deadline(description, by);

        listTasks[numItems] = newDeadlineTask;
        numItems++;

        System.out.println(TASK_ADDED_DONE_TEXT);
        System.out.println(newDeadlineTask.toString());
        printTotalTasks();
        System.out.println(LINE_BREAK_SINGLE);
        System.out.print(ENTER_COMMAND_TEXT);
    }

    //function to add a new event task
    public static void addEventTask(String eventTask) {
        System.out.println(LINE_BREAK_SINGLE);

        //check if string contains '/at' tag
        if (!eventTask.contains("/at")) {
            System.out.println(PRINT_ERROR_MESSAGE);
            System.out.println(LINE_BREAK_SINGLE);
            return;
        }

        String description = eventTask.substring(EVENT_INDEX, eventTask.indexOf('/')).trim();
        String at = eventTask.substring(eventTask.indexOf("/at") + TASK_STRING_OFFSET).trim();
        Event newEventTask = new Event(description, at);

        listTasks[numItems] = newEventTask;
        numItems++;

        System.out.println(TASK_ADDED_DONE_TEXT);
        System.out.println(newEventTask.toString());
        printTotalTasks();
        System.out.println(LINE_BREAK_SINGLE);
        System.out.print(ENTER_COMMAND_TEXT);
    }

    //function to add a new todo task
    public static void addToDoTask(String toDoTask) {
        System.out.println(LINE_BREAK_SINGLE);
        String description;

        if (toDoTask.contains("todo")) {
            description = toDoTask.substring(TODO_INDEX).trim();
        } else {
            description = toDoTask.trim();
        }

        ToDo newTask = new ToDo(description);
        listTasks[numItems] = newTask;
        numItems++;

        System.out.println(TASK_ADDED_DONE_TEXT);
        System.out.println(newTask.toString());
        printTotalTasks();
        System.out.println(LINE_BREAK_SINGLE);
        System.out.print(ENTER_COMMAND_TEXT);
    }

    //function to list all tasks currently saved by the user
    public static void listAllTasks() {
        System.out.println(LINE_BREAK_SINGLE);
        System.out.println("Displaying all items saved:");
        if (numItems == 0) {
            System.out.println("No items found...Add some items now!");
        }
        for (int i = 0; i < numItems; i++) {
            System.out.println(i + 1 + ": " + listTasks[i].toString());
        }
        System.out.println(LINE_BREAK_SINGLE);
        System.out.print(ENTER_COMMAND_TEXT);
    }

    //function to mark individual tasks as done
    public void executeDoneTask(String task) {
        int taskNum = Integer.parseInt(task.substring(task.indexOf(' ') + 1));
        if (taskNum > this.getNumItems() || taskNum <= 0) {
            System.out.println(DONE_TASK_ERROR_MESSAGE);
        } else {
            listTasks[taskNum - 1].markAsDone();
            System.out.println(DONE_TASK_SUCCESS_MESSAGE);
            System.out.println(listTasks[taskNum-1].toString());
        }
        System.out.println(LINE_BREAK_SINGLE);
        System.out.print(ENTER_COMMAND_TEXT);
    }

    //function to exit program
    public void executeBye() {
        this.setCanTerminateHal(true);
    }

    public static void printTotalTasks() {
        System.out.println("You now have " + numItems + " task(s) in your list!");
    }

    public Boolean getCanTerminateHal() {
        return canTerminateHal;
    }

    public void setCanTerminateHal(boolean canTerminateHal) {
        this.canTerminateHal = canTerminateHal;
    }
}

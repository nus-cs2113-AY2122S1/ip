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
    public static final String PRINT_EMPTY_TASKS_MESSAGE = "No items found...Add some items now!";
    public static final String PRINT_ERROR_MESSAGE = "Your input does not follow my format!\n" +
            "Read properly and type it again!";
    public static final String ENTER_COMMAND_TEXT = "Enter command: ";
    public static final String PRINT_EMPTY_DESCRIPTION_MESSAGE =
            "Hmm... did you forget to write your task?";
    public static final String PRINT_EMPTY_DATE_MESSAGE =
            "Hmm... I think you forgot to write your timings!";
    public static final String INVALID_NUMBER_ERROR = "Your input wasn't an integer! Write a valid number";

    public Program() {
        this.numItems = 0;
    }

    public static int getNumItems() {
        return numItems;
    }

    //function to parse input text by the user and run corresponding function
    public void executeTask(String string) throws HalException {
        if (Objects.equals(string, "list")) {
            listAllTasks();
        } else if (Objects.equals(string, "bye")) {
            this.executeBye();
        } else if (string.startsWith("done")) {
            this.executeDoneTask(string);
        } else if (string.startsWith("deadline")) {
            addDeadlineTask(string);
        } else if (string.startsWith("event")) {
            addEventTask(string);
        } else if (string.startsWith("todo")){
            addToDoTask(string);
        } else {
            throw new HalException("I'm sorry, but I don't know what that means :((");
        }
    }

    //function to add a new deadline task
    public static void addDeadlineTask(String deadlineTask) throws HalException {
        System.out.println(LINE_BREAK_SINGLE);

        //check if string contains '/by' tag
        if (!deadlineTask.contains("/by")) {
            System.out.println(PRINT_ERROR_MESSAGE);
            throw new HalException("Wrong Deadline task format");
        }

        String description = deadlineTask.substring(DEADLINE_INDEX, deadlineTask.indexOf('/')).trim();
        String by = deadlineTask.substring(deadlineTask.indexOf("/by") + TASK_STRING_OFFSET).trim();

        if (description.equals("")) {
            System.out.println(PRINT_EMPTY_DESCRIPTION_MESSAGE);
            throw new HalException("Empty description");
        } else if (by.equals("")) {
            System.out.println(PRINT_EMPTY_DATE_MESSAGE);
            throw new HalException("Empty date");
        }

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
    public static void addEventTask(String eventTask) throws HalException {
        System.out.println(LINE_BREAK_SINGLE);

        //check if string contains '/at' tag
        if (!eventTask.contains("/at")) {
            System.out.println(PRINT_ERROR_MESSAGE);
            throw new HalException("Wrong Event task format");
        }

        String description = eventTask.substring(EVENT_INDEX, eventTask.indexOf('/')).trim();
        String at = eventTask.substring(eventTask.indexOf("/at") + TASK_STRING_OFFSET).trim();

        if (description.equals("")) {
            System.out.println(PRINT_EMPTY_DESCRIPTION_MESSAGE);
            throw new HalException("Empty description");
        } else if (at.equals("")) {
            System.out.println(PRINT_EMPTY_DATE_MESSAGE);
            throw new HalException("Empty date");
        }

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
    public static void addToDoTask(String toDoTask) throws HalException {
        System.out.println(LINE_BREAK_SINGLE);
        String description;

        description = toDoTask.substring(TODO_INDEX).trim();

        if (description.equals("")) {
            System.out.println(PRINT_EMPTY_DESCRIPTION_MESSAGE);
            throw new HalException("Empty description");
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
            System.out.println(PRINT_EMPTY_TASKS_MESSAGE);
        }
        for (int i = 0; i < numItems; i++) {
            System.out.println(i + 1 + ": " + listTasks[i].toString());
        }
        System.out.println(LINE_BREAK_SINGLE);
        System.out.print(ENTER_COMMAND_TEXT);
    }

    //function to mark individual tasks as done
    public void executeDoneTask(String task) {
        try {
            int taskNum = Integer.parseInt(task.substring(task.indexOf(' ') + 1));
            if (taskNum > getNumItems() || taskNum <= 0) {
                System.out.println(DONE_TASK_ERROR_MESSAGE);
            } else {
                listTasks[taskNum - 1].markAsDone();
                System.out.println(DONE_TASK_SUCCESS_MESSAGE);
                System.out.println(listTasks[taskNum-1].toString());
            }

            System.out.println(LINE_BREAK_SINGLE);
            System.out.print(ENTER_COMMAND_TEXT);

        } catch (NumberFormatException error) {
            System.out.println(INVALID_NUMBER_ERROR);
            System.out.println(LINE_BREAK_SINGLE);
            System.out.print(ENTER_COMMAND_TEXT);

        }
        

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

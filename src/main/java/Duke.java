import java.util.Scanner;

public class Duke {
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final String EXIT_MESSAGE = LINE_DIVIDER + System.lineSeparator()
            + "Thanks for talking with me, see you soon!" + System.lineSeparator()
            + LINE_DIVIDER;
    public static final int MAX_STORED_TASKS = 100;
    public static final int DONE_OFFSET = 1;
    public static final int DEADLINE_DESC_OFFSET = 9;
    public static final int DEADLINE_BY_OFFSET = 4;
    public static final int EVENT_DESC_OFFSET = 6;
    public static final int EVENT_BY_OFFSET = 4;

    private static Task[] tasks = new Task[MAX_STORED_TASKS]; // Store up to 100 tasks.
    private static int totalTasksCounter = 0;

    public static void printWelcome() {
        String welcomeMessage = LINE_DIVIDER + System.lineSeparator()
                + "Hello! I'm your friendly taskbot, John!" + System.lineSeparator()
                + "Please type @help for a list of commands. How can I help?" + System.lineSeparator()
                + LINE_DIVIDER;
        System.out.println(welcomeMessage);
    }

    public static void printHelp() {
        String helpMessage = LINE_DIVIDER + System.lineSeparator()
                + "todo <task> - Adds a todo task." + System.lineSeparator()
                + "deadline <task> /by <due date> - Adds a deadline task." + System.lineSeparator()
                + "event <task> /at <when> - Adds an event task." + System.lineSeparator()
                + "list - Lists all tasks recorded." + System.lineSeparator()
                + "done <task number> - Marks selected task number as done with an X." + System.lineSeparator()
                + "bye - Exits the taskbot." + System.lineSeparator()
                + LINE_DIVIDER;
        System.out.println(helpMessage);
    }

    public static void printAddedTodo(String todoDescription) {
        String addedMessage = LINE_DIVIDER
                + "Alright! I've successfully added this task:" + System.lineSeparator()
                + "[T]" + "[ " + "] " + todoDescription + System.lineSeparator()
                + "You now have " + totalTasksCounter + " tasks in the list!" + System.lineSeparator()
                + LINE_DIVIDER + System.lineSeparator();
        System.out.println(addedMessage);
    }

    public static void printAddedDeadline(String deadlineDescription, String deadlineBy) {
        String addedMessage = LINE_DIVIDER + System.lineSeparator()
                + "Alright! I've successfully added this task:" + System.lineSeparator()
                + "[D]" + "[ " + "] " + deadlineDescription + "(by: " + deadlineBy + ")" + System.lineSeparator()
                + "You now have " + totalTasksCounter + " tasks in the list!" + System.lineSeparator()
                + LINE_DIVIDER + System.lineSeparator();
        System.out.println(addedMessage);
    }

    public static void printAddedEvent(String eventDescription, String eventAt) {
        String addedMessage = LINE_DIVIDER + System.lineSeparator()
                + "Alright! I've successfully added this task:"  + System.lineSeparator()
                + "[E]" + "[ " + "] " + eventDescription + "(at: " + eventAt + ")" + System.lineSeparator()
                + "You now have " + totalTasksCounter + " tasks in the list!" + System.lineSeparator()
                + LINE_DIVIDER + System.lineSeparator();
        System.out.println(addedMessage);
    }

    public static void printList() {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "As requested, here are the tasks in your list:");
        if (totalTasksCounter == 0) {
            System.out.println("There are no tasks recorded!");
        }
        for (int i = 0; i < totalTasksCounter; i++) {
            String taskType = tasks[i].getType();
            String byOrAt = "";

            if (taskType.equals("D")) {
                byOrAt = "by: ";
            } else if (taskType.equals("E")) {
                byOrAt = "at: ";
            } else {
                byOrAt = "";
            }

            if (byOrAt.equals("")) {
                System.out.println(i + 1 + ". "
                        + "[" + taskType + "]"
                        + "[" + tasks[i].getStatusIcon() + "] "
                        + tasks[i].description);
            } else {
                System.out.println(i + 1 + ". "
                        + "[" + taskType + "]"
                        + "[" + tasks[i].getStatusIcon() + "] "
                        + tasks[i].description + "(" + byOrAt + tasks[i].getWhen() + ")");
            }
        }
        System.out.println(LINE_DIVIDER);
    }

    public static int filterTaskNum(String doneTask) {
        String[] words = doneTask.split(" ");
        if (words.length > 1) { // simple check to see if task number has not been input
            return Integer.parseInt(words[1]);
        }
        return -1;
    }

    public static void markTaskDone(int numOfDone) {
        if (numOfDone == -1) { // Error case
            System.out.println("Missing task number to remove. Please try again.");
        }
        else if ((numOfDone - DONE_OFFSET >= 0) && (tasks[numOfDone - DONE_OFFSET] != null)) {
            tasks[numOfDone - DONE_OFFSET].markAsDone();
            System.out.println(LINE_DIVIDER + System.lineSeparator()
                    + "Great work! I've marked this task as done:" + System.lineSeparator()
                    + "[" + tasks[numOfDone - DONE_OFFSET].getType() + "]" + "[" + tasks[numOfDone - DONE_OFFSET].getStatusIcon() + "] "
                    + tasks[numOfDone - DONE_OFFSET].description + System.lineSeparator()
                    + LINE_DIVIDER);
        }
        else {
            System.out.println("That is not a valid task number! Please try again.");
        }
    }

    public static void addTask(Task t) {
        tasks[totalTasksCounter] = t;
        totalTasksCounter++;
    }

    public static void addTodo(String description) {
        addTask(new Todo(description));
        printAddedTodo(description);
    }

    public static void addDeadline(String description, String by) {
        addTask(new Deadline(description, by));
        printAddedDeadline(description,by);
    }

    public static void addEvent(String description, String at) {
        addTask(new Event(description, at));
        printAddedEvent(description, at);
    }

    public static void processInputs(Scanner in, String line) {
        // while input is not "bye", keep taking inputs.
        while (!line.equals("bye")) {
            if (line.equals("list")) { // print List when "list" command
                printList();
            } else if (line.equals ("@help")) { // print help commands when "@help" command
                printHelp();
            } else if (line.contains("done")) { // mark task as Done when "done" command
                int taskNum = filterTaskNum(line);
                markTaskDone(taskNum);
            } else if (line.contains("todo")) { // add a todo when "todo" command
                String todoDescription = line.substring(5);
                addTodo(todoDescription); // adds todo and prints
            } else if (line.contains("deadline")) { // add a deadline when "deadline" command
                int posOfBy = line.indexOf("/by");
                if (posOfBy == -1) { // throw error if missing /by parameter
                    System.out.println("ERROR! Did you forget /by ?");
                    break;
                }
                int posOfLastChar = line.length();
                String deadlineDescription = line.substring(DEADLINE_DESC_OFFSET, posOfBy); // get description from input
                String deadlineBy = line.substring(posOfBy + DEADLINE_BY_OFFSET, posOfLastChar); // get deadline when from input
                addDeadline(deadlineDescription, deadlineBy); // adds deadline and prints
            } else if (line.contains("event")) { // add an event
                int posOfAt = line.indexOf("/at");
                if (posOfAt == -1) { // throw error if missing /at parameter
                    System.out.println("ERROR! Did you forget /at ?");
                    break;
                }
                int posOfLastChar = line.length();
                String eventDescription = line.substring(EVENT_DESC_OFFSET, posOfAt); // get description from input
                String eventAt = line.substring(posOfAt + EVENT_BY_OFFSET, posOfLastChar); // get event when from input
                addEvent(eventDescription, eventAt); // adds event and prints
            } else { // throw error when no commands are found in input
                System.out.println("Unrecognized command! Please try again, or type @help for a list of commands.");
            }
            line = in.nextLine();
        }
    }

    public static void main(String[] args) {
        // initialize input
        Scanner in = new Scanner(System.in);
        String line;

        // start the taskbot
        printWelcome();
        line = in.nextLine();

        // process inputs by user
        processInputs(in, line);

        // exit after finished
        System.out.println(EXIT_MESSAGE);
    }
}

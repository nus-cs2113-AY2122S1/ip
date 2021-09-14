import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final String EXIT_MESSAGE = LINE_DIVIDER + System.lineSeparator()
            + "Thanks for talking with me, see you soon!" + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String UNKNOWN_COMMAND = LINE_DIVIDER + System.lineSeparator()
            + "Unrecognized command! ☹ Please try again, or type @help for a list of commands." + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String DONE_EMPTY = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, you did not select a task to mark as done ☹" + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String DELETE_EMPTY = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, you did not select a task to delete ☹" + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String DONE_WORD = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, you entered a word instead of a number after done ☹ Please enter the task number to be marked as done!" + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String DELETE_WORD = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, you entered a word instead of a number after delete ☹ Please enter the task number to delete!" + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String DONE_INVALID_NUM = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, you entered an invalid task number! ☹ Please enter the correct task number." + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String DELETE_INVALID_NUM = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, you entered an invalid task number! ☹ Please enter the correct task number." + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String TODO_EMPTY = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, the TODO description cannot be empty ☹" + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String DEADLINE_EMPTY = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, the DEADLINE description cannot be empty ☹" + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String DEADLINE_MISSINGPARAM = LINE_DIVIDER + System.lineSeparator()
            + "Woops! Did you forget the /by parameter?" + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String EVENT_EMPTY = LINE_DIVIDER + System.lineSeparator()
            + "Sorry, the EVENT description cannot be empty ☹" + System.lineSeparator()
            + LINE_DIVIDER;
    public static final String EVENT_MISSINGPARAM = LINE_DIVIDER + System.lineSeparator()
            + "Woops! Did you forget the /at parameter?" + System.lineSeparator()
            + LINE_DIVIDER;

    public static final int ARRAYLIST_PRINT_OFFSET = 1;
    public static final int DONE_OFFSET = 1;
    public static final int DELETE_OFFSET = 1;
    public static final int DEADLINE_DESC_OFFSET = 9;
    public static final int DEADLINE_BY_OFFSET = 4;
    public static final int EVENT_DESC_OFFSET = 6;
    public static final int EVENT_BY_OFFSET = 4;

    private static ArrayList<Task> tasks = new ArrayList<>();

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
                + "delete <task number> - Deletes selected task number." + System.lineSeparator()
                + "bye - Exits the taskbot." + System.lineSeparator()
                + LINE_DIVIDER;
        System.out.println(helpMessage);
    }

    public static void printAddedTodo(String todoDescription) {
        String addedMessage = LINE_DIVIDER + System.lineSeparator()
                + "Alright! I've successfully added this task:" + System.lineSeparator()
                + "[T]" + "[ " + "] " + todoDescription + System.lineSeparator()
                + "You now have " + tasks.size() + " tasks in the list!" + System.lineSeparator()
                + LINE_DIVIDER;
        System.out.println(addedMessage);
    }

    public static void printAddedDeadline(String deadlineDescription, String deadlineBy) {
        String addedMessage = LINE_DIVIDER + System.lineSeparator()
                + "Alright! I've successfully added this task:" + System.lineSeparator()
                + "[D]" + "[ " + "] " + deadlineDescription + "(by: " + deadlineBy + ")" + System.lineSeparator()
                + "You now have " + tasks.size() + " tasks in the list!" + System.lineSeparator()
                + LINE_DIVIDER;
        System.out.println(addedMessage);
    }

    public static void printAddedEvent(String eventDescription, String eventAt) {
        String addedMessage = LINE_DIVIDER + System.lineSeparator()
                + "Alright! I've successfully added this task:"  + System.lineSeparator()
                + "[E]" + "[ " + "] " + eventDescription + "(at: " + eventAt + ")" + System.lineSeparator()
                + "You now have " + tasks.size() + " tasks in the list!" + System.lineSeparator()
                + LINE_DIVIDER;
        System.out.println(addedMessage);
    }

    public static void printList() {
        System.out.println(LINE_DIVIDER + System.lineSeparator()
                + "As requested, here are the tasks in your list:");
        if (tasks.size() == 0) {
            System.out.println("There are no tasks recorded!");
        } else {
            for (Task element : tasks) {
                System.out.println((tasks.indexOf(element) + ARRAYLIST_PRINT_OFFSET) + "." + element.toString());
            }
        }
        System.out.println(LINE_DIVIDER);
    }

    public static int filterTaskNum(String doneTask) throws DukeMissingParamException, NumberFormatException {
        String[] words = doneTask.split(" ");

        if (words.length > 1) { // simple check to see if task number has not been input
            return Integer.parseInt(words[1]);
        }
        else {
            throw new DukeMissingParamException();
        }
    }

    public static void markTaskDone(int numToMark) {
        if ((numToMark - DONE_OFFSET >= 0) && (tasks.get(numToMark - DONE_OFFSET) != null)) {
            tasks.get(numToMark - DONE_OFFSET).markAsDone();
            System.out.println(LINE_DIVIDER + System.lineSeparator()
                    + "Great work! I've marked this task as done:" + System.lineSeparator()
                    + "[" + tasks.get(numToMark - DONE_OFFSET).getType() + "]" + "[" + tasks.get(numToMark - DONE_OFFSET).getStatusIcon() + "] "
                    + tasks.get(numToMark - DONE_OFFSET).description + System.lineSeparator()
                    + LINE_DIVIDER);
        }
        else {
            System.out.println(DONE_INVALID_NUM);
        }
    }

    public static void addTask(Task t) {
        tasks.add(t);
    }

    public static void deleteTask(int numToRemove) throws DukeMissingParamException, NumberFormatException, IndexOutOfBoundsException {
        if ((numToRemove - DELETE_OFFSET >= 0) && (tasks.get(numToRemove - DELETE_OFFSET) != null)) {
            String byOrAt = "";
            if (tasks.get(numToRemove - DELETE_OFFSET).getType().equals("D")) {
                byOrAt = "by: ";
            } else if (tasks.get(numToRemove - DELETE_OFFSET).getType().equals("E")) {
                byOrAt = "at: ";
            } else {
                byOrAt = "";
            }

            if (byOrAt.equals("")) {
                System.out.println(LINE_DIVIDER + System.lineSeparator()
                        + "Alright! I've removed this task:" + System.lineSeparator()
                        + "[" + tasks.get(numToRemove - DELETE_OFFSET).getType() + "]" + "[" + tasks.get(numToRemove - DELETE_OFFSET).getStatusIcon() + "] "
                        + tasks.get(numToRemove - DELETE_OFFSET).description + System.lineSeparator()
                        + "Now you have " + (tasks.size() - DELETE_OFFSET)+ " tasks remaining in the list." + System.lineSeparator()
                        + LINE_DIVIDER);

            } else {
                System.out.println(LINE_DIVIDER + System.lineSeparator()
                        + "Alright! I've removed this task:" + System.lineSeparator()
                        + "[" + tasks.get(numToRemove - DELETE_OFFSET).getType() + "]" + "[" + tasks.get(numToRemove - DELETE_OFFSET).getStatusIcon() + "] "
                        + tasks.get(numToRemove - DELETE_OFFSET).description + "(" + byOrAt + tasks.get(numToRemove - DELETE_OFFSET).getWhen() + ")" + System.lineSeparator()
                        + "Now you have " + (tasks.size() - DELETE_OFFSET) + " tasks remaining in the list." + System.lineSeparator()
                        + LINE_DIVIDER);
            }
        }   tasks.remove(tasks.get(numToRemove - DELETE_OFFSET));
    }

    public static void addTodo(String line) throws DukeMissingDescException {
        if (line.length() == 4 || line.substring(5).isBlank()) {
            throw new DukeMissingDescException();
        }
        String todoDescription = line.substring(5);
        addTask(new Todo(todoDescription));
        printAddedTodo(todoDescription);
    }

    public static void addDeadline(String line) throws DukeMissingDescException, DukeMissingParamException {
        if (line.length() == 8 || line.substring(9).isBlank()) {
            throw new DukeMissingDescException();
        }

        int posOfBy = line.indexOf("/by");
        if (posOfBy == -1) { // throw error if missing /by parameter
            throw new DukeMissingParamException();
        }
        int posOfLastChar = line.length();
        String deadlineDescription = line.substring(DEADLINE_DESC_OFFSET, posOfBy); // get description from input
        String deadlineBy = line.substring(posOfBy + DEADLINE_BY_OFFSET, posOfLastChar); // get deadline when from input
        addTask(new Deadline(deadlineDescription, deadlineBy));
        printAddedDeadline(deadlineDescription, deadlineBy);
    }

    public static void addEvent(String line) throws DukeMissingDescException, DukeMissingParamException {
        if (line.length() == 5 || line.substring(6).isBlank()) {
            throw new DukeMissingDescException();
        }

        int posOfAt = line.indexOf("/at");
        if (posOfAt == -1) { // throw error if missing /at parameter
            throw new DukeMissingParamException();
        }
        int posOfLastChar = line.length();
        String eventDescription = line.substring(EVENT_DESC_OFFSET, posOfAt); // get description from input
        String eventAt = line.substring(posOfAt + EVENT_BY_OFFSET, posOfLastChar); // get event when from input
        addTask(new Event(eventDescription, eventAt));
        printAddedEvent(eventDescription, eventAt);
    }

    public static void processInputs(Scanner in, String line) {
        // while input is not "bye", keep taking inputs.
        while (!line.equals("bye")) {
            if (line.equals("list")) { // print List when "list" command
                printList();
            } else if (line.equals ("@help")) { // print help commands when "@help" command
                printHelp();
            } else if (line.contains("done")) { // mark task as Done when "done" command
                try {
                    int taskNum = filterTaskNum(line);
                    markTaskDone(taskNum);
                }
                catch (DukeMissingParamException e) {
                    System.out.println(DONE_EMPTY);
                }
                catch (NumberFormatException e) {
                    System.out.println(DONE_WORD);
                }
            } else if (line.contains("delete")) { // delete task when "delete" command
                try {
                    int taskNum = filterTaskNum(line);
                    deleteTask(taskNum);
                }
                catch (DukeMissingParamException e) {
                    System.out.println(DELETE_EMPTY);
                }
                catch (NumberFormatException e) {
                    System.out.println(DELETE_WORD);
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println(DELETE_INVALID_NUM);
                }
            } else if (line.contains("todo")) { // add a todo when "todo" command
                try {
                    addTodo(line); // adds todo and prints
                }
                catch (DukeMissingDescException e) {
                    System.out.println(TODO_EMPTY);
                }
            } else if (line.contains("deadline")) { // add a deadline when "deadline" command
                try {
                    addDeadline(line); // adds deadline and prints
                }
                catch (DukeMissingDescException e) {
                    System.out.println(DEADLINE_EMPTY);
                }
                catch (DukeMissingParamException e) {
                    System.out.println(DEADLINE_MISSINGPARAM);
                }
            } else if (line.contains("event")) { // add an event
                try {
                    addEvent(line); // adds event and prints
                }
                catch (DukeMissingDescException e) {
                    System.out.println(EVENT_EMPTY);
                }
                catch (DukeMissingParamException e) {
                    System.out.println(EVENT_MISSINGPARAM);
                }
            } else { // throw error when no commands are found in input
                System.out.println(UNKNOWN_COMMAND);
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

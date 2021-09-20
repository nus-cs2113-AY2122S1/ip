import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;

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
    public static final int TODO_LEN_OFFSET = 4;
    public static final int TODO_SUBSTRING_OFFSET = 5;
    public static final int DEADLINE_DESC_OFFSET = 9;
    public static final int DEADLINE_BY_OFFSET = 4;
    public static final int DEADLINE_LEN_OFFSET = 8;
    public static final int DEADLINE_SUBSTRING_OFFSET = 9;
    public static final int EVENT_DESC_OFFSET = 6;
    public static final int EVENT_BY_OFFSET = 4;
    public static final int EVENT_LEN_OFFSET = 5;
    public static final int EVENT_SUBSTRING_OFFSET = 6;
    public static final int TASK_INIT_OFFSET = 1;
    public static final String DIRECTORY_NAME = "data";
    public static final String FILE_PATH = "data/duke.txt";

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
                + "exit - Exits the taskbot." + System.lineSeparator()
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

        // check to see if task number has not been input
        if (words.length > 1) {
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
            String byString = "by: ";
            String atString = "at: ";
            String taskWordString = "";

            // change word string to print depending on if a todo, deadline or event
            if (tasks.get(numToRemove - DELETE_OFFSET).getType().equals("D")) {
                taskWordString = byString;
            } else if (tasks.get(numToRemove - DELETE_OFFSET).getType().equals("E")) {
                taskWordString = atString;
            } else {
                taskWordString = "";
            }

            if (taskWordString.equals("")) {
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
                        + tasks.get(numToRemove - DELETE_OFFSET).description + "(" + taskWordString + tasks.get(numToRemove - DELETE_OFFSET).getWhen() + ")" + System.lineSeparator()
                        + "Now you have " + (tasks.size() - DELETE_OFFSET) + " tasks remaining in the list." + System.lineSeparator()
                        + LINE_DIVIDER);
            }
        }   tasks.remove(tasks.get(numToRemove - DELETE_OFFSET));
    }

    public static void addTodo(String line) throws DukeMissingDescException {
        if (line.length() == TODO_LEN_OFFSET || line.substring(TODO_SUBSTRING_OFFSET).isBlank()) {
            throw new DukeMissingDescException();
        }
        String todoDescription = line.substring(TODO_SUBSTRING_OFFSET);
        addTask(new Todo(todoDescription));
        printAddedTodo(todoDescription);
    }

    public static void addDeadline(String line) throws DukeMissingDescException, DukeMissingParamException {
        if (line.length() == DEADLINE_LEN_OFFSET || line.substring(DEADLINE_SUBSTRING_OFFSET).isBlank()) {
            throw new DukeMissingDescException();
        }

        int posOfBy = line.indexOf("/by");
        // throw error if missing /by parameter
        if (posOfBy == -1) {
            throw new DukeMissingParamException();
        }

        int posOfLastChar = line.length();

        // get description from input
        String deadlineDescription = line.substring(DEADLINE_DESC_OFFSET, posOfBy);

        // get deadline when from input
        String deadlineBy = line.substring(posOfBy + DEADLINE_BY_OFFSET, posOfLastChar);

        addTask(new Deadline(deadlineDescription, deadlineBy));
        printAddedDeadline(deadlineDescription, deadlineBy);
    }

    public static void addEvent(String line) throws DukeMissingDescException, DukeMissingParamException {
        if (line.length() == EVENT_LEN_OFFSET || line.substring(EVENT_SUBSTRING_OFFSET).isBlank()) {
            throw new DukeMissingDescException();
        }

        int posOfAt = line.indexOf("/at");

        // throw error if missing /at parameter
        if (posOfAt == -1) {
            throw new DukeMissingParamException();
        }

        int posOfLastChar = line.length();

        // get description from input
        String eventDescription = line.substring(EVENT_DESC_OFFSET, posOfAt);

        // get event when from input
        String eventAt = line.substring(posOfAt + EVENT_BY_OFFSET, posOfLastChar);

        addTask(new Event(eventDescription, eventAt));
        printAddedEvent(eventDescription, eventAt);
    }

    public static void initFolder() {
        File directory = new File(DIRECTORY_NAME);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Missing directory, creating new directory.");
        }
    }

    public static void initSaveFile() throws FileNotFoundException{
        File f = new File(FILE_PATH);
        Scanner saveFile = new Scanner(f);
        int addCounter = 0;
        while (saveFile.hasNextLine()) {
            String line = saveFile.nextLine();
            String[] processWordsArr = line.split(("[|]"));
            if (line.charAt(0) == 'T') {
                addTask(new Todo(processWordsArr[2].substring(TASK_INIT_OFFSET)));
            } else if (line.charAt(0) == 'D') {
                addTask(new Deadline(processWordsArr[2].substring(TASK_INIT_OFFSET), processWordsArr[3].substring(TASK_INIT_OFFSET)));
            } else if (line.charAt(0) == 'E') {
                addTask(new Event(processWordsArr[2].substring(TASK_INIT_OFFSET), processWordsArr[3].substring(TASK_INIT_OFFSET)));
            }
            if (processWordsArr[1].charAt(1) == '1') {
                tasks.get(addCounter).markAsDone();
            }
                addCounter++;
        }
    }

    public static void writeToSave() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < tasks.size(); i++) {

            String doneNumber = "0";
            if (tasks.get(i).isDone) {
                doneNumber = "1";
            }

            String textToAdd;
            if (tasks.get(i).getType().equals("T")) {
                textToAdd = tasks.get(i).getType() + " | "
                    + doneNumber + " | "
                    +  tasks.get(i).description + System.lineSeparator();
            } else {
                textToAdd = tasks.get(i).getType() + " | "
                        + doneNumber + " | "
                        +  tasks.get(i).description + "| " + tasks.get(i).getWhen() + System.lineSeparator();
            }
            fw.write(textToAdd);
        }
        fw.close();
    }

    public static void processInputs(Scanner in, String line) {
        boolean isListCommand = line.equals("list");
        boolean isHelpCommand = line.equals("@help");
        boolean isDoneCommand = line.equals("done");
        boolean isDeleteCommand = line.equals("delete");
        boolean isTodoCommand = line.equals("todo");
        boolean isDeadlineCommand = line.equals("deadline");
        boolean isEventCommand = line.equals("event");

        // while input is not "exit", keep taking inputs.
        while (!line.equals("exit")) {
            if (isListCommand) {
                printList();
            } else if (isHelpCommand) {
                printHelp();
            } else if (isDoneCommand) {
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
            } else if (isDeleteCommand) {
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
            } else if (isTodoCommand) {
                try {
                    // adds todo and prints success message
                    addTodo(line);
                }
                catch (DukeMissingDescException e) {
                    System.out.println(TODO_EMPTY);
                }
            } else if (isDeadlineCommand) {
                try {
                    // adds deadline and prints success message
                    addDeadline(line);
                }
                catch (DukeMissingDescException e) {
                    System.out.println(DEADLINE_EMPTY);
                }
                catch (DukeMissingParamException e) {
                    System.out.println(DEADLINE_MISSINGPARAM);
                }
            } else if (isEventCommand) {
                try {
                    // adds event and prints success message
                    addEvent(line);
                }
                catch (DukeMissingDescException e) {
                    System.out.println(EVENT_EMPTY);
                }
                catch (DukeMissingParamException e) {
                    System.out.println(EVENT_MISSINGPARAM);
                }
            } else {
                // throw error when no commands are found in input
                System.out.println(UNKNOWN_COMMAND);
            }
            line = in.nextLine();
        }
    }

    public static void main(String[] args) throws IOException {
        // initialize input
        initFolder();
        try {
            initSaveFile();
        } catch (FileNotFoundException e) {
            File f = new File(FILE_PATH);
            f.createNewFile();
            System.out.println("Missing duke.txt, creating new file.");
        }
        Scanner in = new Scanner(System.in);
        String line;

        // start the taskbot
        printWelcome();
        line = in.nextLine();

        // process inputs by user
        processInputs(in, line);

        // save to file and exit after finished
        try {
            writeToSave();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println(EXIT_MESSAGE);
    }
}

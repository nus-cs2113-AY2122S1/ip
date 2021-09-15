package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Logo of the bot
     */
    private static final String LOGO = " _ __ _   _  __ _ _ __\n"
                                    + "| '__| | | |/ _` | '_ \\\n"
                                    + "| |  | |_| | (_| | | | |\n"
                                    + "|_|   \\__, |\\__,_|_| |_|\n"
                                    + "       __/ |\n"
                                    + "      |___/";
    private static final String SPACING = " ";
    /**
     * A decorative spacer between user inputs and outputs by the bot
     */
    private static final String DIVIDER = "____________________________________________________________";

    /**
     * Commands that tell the bot the event time or the due date of deadline
     */
    private static final String ENTRY_AT = "/at";
    private static final String ENTRY_BY = "/by";

    private static final String MESSAGE_TASK_ADDED = "Added task:\n    ";
    private static final String MESSAGE_WELCOME = "Hello from\n"
                                                + LOGO
                                                + "\nHow can I assist you? Type something below! :D";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_NO_INPUT = "No input found! Please type <mode> + item";
    private static final String MESSAGE_OUT_OF_RANGE = "No such task found! Try a range of 1 to ";

    private static final String ERROR_WRONG_EVENT_FORMAT = "The input was wrong :( "
            + "Please type 'event <description> /at <time of event>'";
    private static final String ERROR_WRONG_DEADLINE_FORMAT = "The input was wrong :( "
            + "Please type 'deadline <description> /by <time of event>'";
    private static final String ERROR_WRONG_TODO_FORMAT = "The input was wrong :( Please type 'todo <description>'";

    private static final String TASK_PLURAL = "tasks";
    private static final String TASK_SINGLE = "task";

    private static final String PRINT_TASK_MESSAGE_FRONT = "Now you have ";
    private static final String PRINT_TASK_MESSAGE_BACK = " in the list.";
    private static final String PRINT_DONE_MESSAGE_FRONT = "I have marked\n     ";
    private static final String PRINT_DONE_MESSAGE_BACK = "\n as done!";
    private static final String PRINT_REMOVE_MESSAGE = "Task removed :\n    ";

    /**
     * Commands for the different cases
     */
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_LIST = "list";

    /**
     * ArrayList that stores all the tasks
     */
    private static ArrayList<Task> tasks = new ArrayList<>();


    /**
     * Main entry point of the application and starts the interaction with user
     */
    public static void main(String[] args) {
        printWelcomeMessage();
        startChat();
        printByeMessage();
    }

    /**
     * Prints the goodbye message
     */
    private static void printByeMessage() {
        System.out.println(MESSAGE_GOODBYE);
    }

    /**
     * Prints the welcome message
     */
    private static void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
    }

    /**
     * Prints a divider
     */
    private static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints the list of tasks stored
     */
    private static void printList() {
        for (Task t: tasks) {
            System.out.println(t);
        }
        printTaskNumber();
        return;
    }

    /**
     * Prints the number of tasks
<<<<<<< HEAD
     *
=======
>>>>>>> branch-Level-6
     */
    private static void printTaskNumber() {
        String task = TASK_PLURAL;
        if (tasks.size() == 1) {
            task = TASK_SINGLE;
        }
        System.out.println(PRINT_TASK_MESSAGE_FRONT + tasks.size() + SPACING + task + PRINT_TASK_MESSAGE_BACK);
    }

    /**
     * Scans for the description of the input task
     *
     * @param chatInput input of the user
     * @return description of task
     */
    private static String scanDescription(String chatInput, boolean isSavingFile) throws DukeException, StringIndexOutOfBoundsException {
        String[] words = chatInput.split(SPACING);

        boolean isMissingDescription = (words.length <= 1 && !isSavingFile);

        if (isMissingDescription) {
            throw new DukeException();
        }

        int spaceCount = 1;

        // If saving the file, we can start from the start and need not account for the command
        int startIdx = isSavingFile ? 0 : words[0].length() + spaceCount;
        int endIdx = 0;

        for (int i = 0; i < words.length; i++) {

            // true if /by or (by: ...) is detected
            boolean isEndOfDescription = (words[i].charAt(0) == '/' || words[i].charAt(0) == '(');
            if (isEndOfDescription) {
                break;
            }
            endIdx += words[i].length() + spaceCount;
        }
        return chatInput.substring(startIdx, endIdx - spaceCount);
    }

    /**
     * Scans for the event time/due date of deadline of task
     *
     * @param chatInput input of user
     * @return either the due date of deadline or event time
     */
    private static String getTimeOfEvent(String chatInput, boolean isSavingInput) throws DukeException {
        String[] words = chatInput.split(SPACING);

        int spaceCount = 1;
        // Accounting for space and semicolon
        int startIdx = isSavingInput ? 1 : 0;

        for (int i = 0; i < words.length; i++) {

            if (i == words.length - 1) {
                throw new DukeException();
            }

            if (checkAtEntry(words[i])) {
                startIdx += ENTRY_AT.length();
                break;
            } else if (checkByEntry(words[i])) {
                startIdx += ENTRY_BY.length();
                break;
            }

            startIdx += words[i].length() + spaceCount;
        }
        String timeOfEvent = chatInput.substring(startIdx + spaceCount);

        if (isSavingInput) {
            // Removing the parenthesis from the time of event
            timeOfEvent = chatInput.substring(startIdx + spaceCount, chatInput.length() - 1);
        }

        return timeOfEvent;
    }

    /**
     * Scans for the command (keyword)
     *
     * @param chatInput input of user
     * @return the command to be executed
     */
    private static String scanCommand(String chatInput) {
        String[] words = chatInput.toLowerCase().split(SPACING);
        return words[0];
    }

    /**
     * Searches for the task number in the command
     *
     * @param chatInput input of user
     * @return task number to be marked as done
     */
    private static int findTaskNumber(String chatInput) {
        int accountForArray = 1;
        String[] words = chatInput.split(SPACING);
        int taskNumber = Integer.parseInt(words[1]) - accountForArray;
        return taskNumber;
    }

    /**
     * Checks if input string is /at
     *
     * @param input input of entry
     * @return true if entry is /at
     */
    private static boolean checkAtEntry(String input) {
        if (input.equals(ENTRY_AT) || input.equals("(at:")) {
            return true;
        }
        return false;
    }

    /**
     * Checks if input string is /by
     *
     * @param input input of entry
     * @return true if entry is /by
     */
    private static boolean checkByEntry(String input) {
        if (input.equals(ENTRY_BY) || input.equals("(by:")) {
            return true;
        }
        return false;
    }


    /**
     * Adds a to-do item to the task list
     *
     * @param chatInput input of user
     */
    private static void addToDoItem(String chatInput, boolean isFileItem, boolean isDone) {
        try {
            ToDo temp = new ToDo(scanDescription(chatInput, false));
            if (isFileItem) {
                if (isDone) {
                    temp.setDone();
                }
                tasks.add(temp);
            } else {
                tasks.add(temp);
                System.out.println(MESSAGE_TASK_ADDED + temp);
                printTaskNumber();
            }
            saveFile();
        } catch (DukeException e) {
            System.out.println(ERROR_WRONG_TODO_FORMAT);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Save failed! Data might be lost.. :(");
        }
    }

    /**
     * Adds an event item to the task list
     *
     * @param chatInput input of user
     */
    private static void addEventItem(String chatInput, boolean isFileItem, boolean isDone) {
        try {
            Event temp = new Event(scanDescription(chatInput, false), getTimeOfEvent(chatInput, false));
            if (isFileItem) {
                if (isDone) {
                    temp.setDone();
                }
                tasks.add(temp);
            } else {
                tasks.add(temp);
                System.out.println(MESSAGE_TASK_ADDED + temp);
                printTaskNumber();
            }
            saveFile();
        } catch (DukeException e) {
            System.out.println(ERROR_WRONG_EVENT_FORMAT);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Save failed! Data might be lost.. :(");
        }

    }

    /**
     * Adds a deadline item to the task list
     *
     * @param chatInput input of user
     */
    private static void addDeadlineItem(String chatInput, boolean isFileItem, boolean isDone) {
        try {
            Deadline temp = new Deadline(scanDescription(chatInput, false), getTimeOfEvent(chatInput, false));
            if (isFileItem) {
                if (isDone) {
                    temp.setDone();
                }
                tasks.add(temp);
            } else {
                tasks.add(temp);
                System.out.println(MESSAGE_TASK_ADDED + temp);
                printTaskNumber();
            }
            saveFile();
        } catch (DukeException e) {
            System.out.println(ERROR_WRONG_DEADLINE_FORMAT);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Save failed! Data might be lost.. :(");
        }
    }

    /**
     * Sets specific task in array as done
     *
     * @param chatInput input of user
     */
    private static void setTaskAsDone(String chatInput) {
        int taskIdx = findTaskNumber(chatInput);
        try {
            Task temp = tasks.get(taskIdx);
            temp.setDone();
            tasks.set(taskIdx, temp);
            System.out.println(PRINT_DONE_MESSAGE_FRONT + temp + PRINT_DONE_MESSAGE_BACK);
            saveFile();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MESSAGE_OUT_OF_RANGE + tasks.size());
        } catch (NumberFormatException e) {
            System.out.println("Please key in a number instead pls :(");
        } catch (IOException e) {
            System.out.println("Save failed! Data might be lost.. :(");
        }
    }

    private static void deleteTask(String chatInput) {
        int taskIdx = findTaskNumber(chatInput);
        try {
            Task temp = tasks.get(taskIdx);
            tasks.remove(taskIdx);
            System.out.println();
            System.out.println(PRINT_REMOVE_MESSAGE + temp);
            saveFile();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MESSAGE_OUT_OF_RANGE + tasks.size());
        } catch (IOException e) {
            System.out.println("Save failed! Data might be lost.. :(");
        }
    }

    private static void loadFile() {
        makeDirectory();
        readFromFile();
    }

    private static void readFromFile() {
        File f = new File("./data/duke.txt");

        try {
            if (f.createNewFile()) {
                System.out.println("Storage file was successfully created!");
            } else {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    addTaskFromFile(s.nextLine());
                }
            }
        } catch (IOException e) {
            System.out.println("There was an error creating/accessing the file.. :(");
        }
    }

    private static void makeDirectory() {
        try {
            Files.createDirectories(Paths.get("./data"));
        } catch (IOException e) {
            System.out.println("Error in creating directory");
        }
    }

    private static void addTaskFromFile(String fileInput) {
        String[] words = fileInput.split(" ");

        // Command is stored in the 2nd item in the array, while
        // isDone() value is stored as the first item in the array.
        // For instance: 1 deadline description /by 9pm
        // marks the deadline item as done.
        boolean isDone = "1".equals(words[0]);
        String command = words[1];

        switch (command) {
        case COMMAND_DEADLINE:
            addDeadlineItem(fileInput.substring(2), true, isDone);
            break;
        case COMMAND_TODO:
            addToDoItem(fileInput.substring(2), true, isDone);
            break;
        case COMMAND_EVENT:
            addEventItem(fileInput.substring(2), true, isDone);
            break;
        default:
            break;
        }
    }

    private static void saveFile() throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        for (int i = 0; i < tasks.size(); i++) {

            String markDone = "0 ";
            String description = "", timeOfEvent = "";
            String command = "todo ";
            String[] words = tasks.toString().split(" ");

            Task curr = tasks.get(i);

            if (curr.isDone()) {
                markDone = "1 ";
            }

            try {
                description = scanDescription(curr.toString().substring(8), true);
                if (curr instanceof Event) {
                    command = "event ";
                    timeOfEvent = " /at " + getTimeOfEvent(curr.toString().substring(8), true);
                } else if (curr instanceof Deadline) {
                    command = "deadline ";
                    timeOfEvent = " /by " + getTimeOfEvent(curr.toString().substring(8), true);
                }

                fw.write(markDone + command + description + timeOfEvent + System.lineSeparator());
            } catch (DukeException e) {
                System.out.println("Error in formatting while saving");
            }
        }
        fw.close();
    }

    /**
     * Executes the main chatting function
     */
    private static void startChat() {
        loadFile();
        boolean isActive = true;
        while (isActive) {
            printDivider();
            String chatInput = SCANNER.nextLine();
            printDivider();
            switch (scanCommand(chatInput)) {
            case COMMAND_BYE:
                isActive = false;
                break;
            case COMMAND_LIST:
                printList();
                break;
            case COMMAND_TODO:
                addToDoItem(chatInput, false, false);
                break;
            case COMMAND_EVENT:
                addEventItem(chatInput, false, false);
                break;
            case COMMAND_DEADLINE:
                addDeadlineItem(chatInput, false, false);
                break;
            case COMMAND_DONE:
                setTaskAsDone(chatInput);
                break;
            case COMMAND_DELETE:
                deleteTask(chatInput);
                break;
            default:
                System.out.println(MESSAGE_NO_INPUT);
                break;
            }
        }
        try {
            saveFile();
        } catch (IOException e) {
            System.out.println("Save failed! Data might be lost.. :(");
        }
    }
}

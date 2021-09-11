package duke;

import duke.exceptions.DukeEmptyDescriptionException;
import duke.exceptions.DukeEmptyTimeException;
import duke.exceptions.DukeExceedMaxTaskException;
import duke.exceptions.DukeInvalidTaskIndex;
import duke.exceptions.DukeTaskAlreadyCompletedException;
import duke.exceptions.DukeMissingKeywordException;
import duke.tasks.TaskManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {

    public static final boolean IS_STARTING = true;
    public static final boolean IS_ENDING = false;

    public static final String DEADLINE_BY_PREFIX = "/by";
    public static final String EVENT_AT_PREFIX = "/at";
    public static final String NL = System.lineSeparator();
    public static final String HELP_MESSAGE = "Valid Commands: " + NL
            + "todo {description of task} (eg. todo homework)" + NL
            + "event {description of event} /at {time of event} (eg. event party at/ 9am)" + NL
            + "deadline {description of task} /by {deadline of task}  (eg. deadline assignment /by 6pm)"
            + NL
            + "list" + NL
            + "done {index number of task done}  (eg. done 1)" + NL
            + "bye";

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String STARTING_MESSAGE = "Hello from" + NL
            + LOGO + NL
            + "Hello! I'm Duke" + NL
            + "What can I do for you?";
    public static final String ENDING_MESSAGE = "Bye. Hope to see you again soon!";

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final TaskManager TASK_MANAGER = new TaskManager();
    public static final File DATA_DIRECTORY = new File("data");
    public static final File DATA_FILE = new File(DATA_DIRECTORY.getPath().concat("/duke.txt"));


    public static void printMessage(String message) {
        final String HORIZONTAL_LINE = "____________________________________________________________";
        System.out.println(HORIZONTAL_LINE + NL + message + NL
                + HORIZONTAL_LINE);
    }

    //Made this as a separate function so that main function doesn't become too big
    public static void printStartingOrEndingMessage(boolean isStart) {
        if (isStart) {
            printMessage(STARTING_MESSAGE);
        } else {
            printMessage(ENDING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        loadPreviousData();
        printStartingOrEndingMessage(IS_STARTING);
        runDuke();
        printStartingOrEndingMessage(IS_ENDING);
    }

    private static void loadPreviousData() {
        System.out.print("Loading data... ");
        if (!DATA_DIRECTORY.isDirectory()) {
            DATA_DIRECTORY.mkdirs();
        }
        if (!DATA_FILE.exists()) {
            System.out.println("No previous data found");
            return;
        }
        try {
            Scanner loadingScanner = new Scanner(DATA_FILE);
            while (loadingScanner.hasNext()) {
                String nextLine = loadingScanner.nextLine();
                loadTask(nextLine);
            }
            System.out.println("Data loaded successfully");
        } catch (IOException e) {
            System.out.println("ERROR : " + e);
        }
    }

    private static void loadTask(String taskDetails) throws IOException{
        String[] splitTaskDetails = taskDetails.split("\\|");
        TASK_MANAGER.addLoadedTask(splitTaskDetails);
    }

    private static void runDuke() {
        boolean conversationIsOver = false;
        while (!conversationIsOver) {
            String inputCommand = SCANNER.nextLine().trim();
            String command = getFirstWord(inputCommand);
            try {
                conversationIsOver = processCommand(conversationIsOver, inputCommand, command);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                // for now these exceptions only occur when a number is passed after done
                // so we can assume the user made an error using the 'done' command
                printMessage("Please enter a valid number after \'done\' : " + inputCommand);
            } catch (DukeEmptyDescriptionException e) {
                printMessage("Please enter a description of the task");
            } catch (DukeExceedMaxTaskException e) {
                printMessage("Exceeded maximum task limit, please delete a task to continue");
            } catch (DukeEmptyTimeException e) {
                printMessage("Please enter the deadline/event time");
            } catch (DukeMissingKeywordException e) {
                printMessage("No " + e.getKeyword() + " detected, press enter to see command syntax");
            } catch (DukeInvalidTaskIndex e) {
                printMessage("Please enter valid task index number");
            } catch (DukeTaskAlreadyCompletedException e) {
                printMessage("This task is already completed");
            } catch (IOException e) {
                System.out.println("ERROR : " + e);
            }
        }
    }

    private static boolean processCommand(boolean conversationIsOver, String inputCommand, String command)
            throws DukeInvalidTaskIndex,
            DukeTaskAlreadyCompletedException,
            DukeEmptyDescriptionException,
            DukeExceedMaxTaskException,
            DukeEmptyTimeException,
            DukeMissingKeywordException,
            IOException{
        switch (command) {
        case "list":
            TASK_MANAGER.printTasks();
            break;
        case "done":
            int indexOfTaskDone = getIntegerFromCommand(inputCommand);
            TASK_MANAGER.setTaskAsDone(indexOfTaskDone);
            break;
        case "bye":
            conversationIsOver = true;
            break;
        case "deadline":
            String deadlineInput = removeFirstWordInSentence(inputCommand, 8);
            TASK_MANAGER.addDeadline(deadlineInput);
            break;
        case "todo":
            String todoInput = removeFirstWordInSentence(inputCommand, 4);
            TASK_MANAGER.addTodo(todoInput);
            break;
        case "event":
            String eventInput = removeFirstWordInSentence(inputCommand, 5);
            TASK_MANAGER.addEvent(eventInput);
            break;
        default:
            printMessage(HELP_MESSAGE);
            break;
        }
        return conversationIsOver;
    }

    private static String removeFirstWordInSentence(String inputCommand, int i) {
        //to remove the words "deadline", "even" or "todo"
        return inputCommand.substring(i).trim();
    }

    private static int getIntegerFromCommand(String input) throws NumberFormatException,
            ArrayIndexOutOfBoundsException {
        String[] splitInput = input.split(" ");
        return Integer.parseInt(splitInput[1]);
    }

    private static String getFirstWord(String inputCommand) {
        //switch to lowercase so that Duke won't be case-sensitive
        return inputCommand.toLowerCase().split(" ")[0];
    }
}

package duke;

import duke.exceptions.DukeEmptyDescriptionException;
import duke.exceptions.DukeEmptyTimeException;
import duke.exceptions.DukeExceedMaxTaskException;
import duke.exceptions.DukeInvalidTaskIndex;
import duke.exceptions.DukeTaskAlreadyCompletedException;
import duke.exceptions.DukeMissingKeywordException;
import duke.tasks.TaskManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static final String DEADLINE_BY_PREFIX = "/by";
    public static final String EVENT_AT_PREFIX = "/at";
    public static final boolean IS_STARTING = true;
    public static final boolean IS_ENDING = false;

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final TaskManager TASK_MANAGER = new TaskManager();
    public static final File DATA_DIRECTORY = new File("data");
    public static final File DATA_FILE = new File(DATA_DIRECTORY.getPath().concat("/duke.txt"));
    public static final Ui UI = new Ui();
    
    public static void main(String[] args) {
        loadPreviousData();
        UI.showStartingOrEndingMessage(IS_STARTING);
        runDuke();
        UI.showStartingOrEndingMessage(IS_ENDING);
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
            String inputCommand = UI.readCommand();
            String command = getFirstWord(inputCommand);
            try {
                conversationIsOver = processCommand(conversationIsOver, inputCommand, command);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                // for now these exceptions only occur when a number is passed after done or delete
                // so we can assume the user made an error using the 'done' or 'delete' command
                UI.showMessage("Please enter a valid number after 'done' or 'delete' : " + inputCommand);
            } catch (DukeEmptyDescriptionException e) {
                UI.showMessage("Please enter a description of the task");
            } catch (DukeExceedMaxTaskException e) {
                UI.showMessage("Exceeded maximum task limit, please delete a task to continue");
            } catch (DukeEmptyTimeException e) {
                UI.showMessage("Please enter the deadline/event time");
            } catch (DukeMissingKeywordException e) {
                UI.showMessage("No " + e.getKeyword() + " detected, press enter to see command syntax");
            } catch (DukeInvalidTaskIndex e) {
                UI.showMessage("Please enter valid task index number");
            } catch (DukeTaskAlreadyCompletedException e) {
                UI.showMessage("This task is already completed");
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
        case "delete":
            int indexOfTaskBeingDeleted = getIntegerFromCommand(inputCommand);
            TASK_MANAGER.removeTask(indexOfTaskBeingDeleted);
            break;
        default:
            UI.showHelpMessage();
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

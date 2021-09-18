package duke;

import duke.exceptions.*;
import duke.tasks.TaskList;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static final String DEADLINE_BY_PREFIX = "/by";
    public static final String EVENT_AT_PREFIX = "/at";
    public static final boolean IS_STARTING = true;
    public static final boolean IS_ENDING = false;
    //TODO think of a better way than down below
    public static TaskList TASK_LIST = new TaskList();
    public static final File DATA_DIRECTORY = new File("data");
    public static final File DATA_FILE = new File(DATA_DIRECTORY.getPath().concat("/duke.txt"));
    public static final Ui UI = new Ui();
    public static final Parser PARSER = new Parser();

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

    private static void loadTask(String taskDetails) throws IOException {
        String[] splitTaskDetails = taskDetails.split("\\|");
        TASK_LIST.addLoadedTask(splitTaskDetails);
    }

    private static void runDuke() {
        boolean conversationIsOver = false;
        while (!conversationIsOver) {
            try {
                String inputCommand = UI.readCommand();
                Command c = PARSER.parse(inputCommand);
                c.execute(TASK_LIST, new Storage(), UI);
                conversationIsOver = c instanceof ExitCommand;
            } catch (DukeException de) {
                UI.showError(de);
            } catch (InvalidCommandException ice) {
                UI.showHelpMessage();
            } catch (IOException ioe) {
                UI.showMessage("Error loading/saving data!!");
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                UI.showMessage("Please enter a valid number after 'done' or 'delete'");
            }
        }
    }
}
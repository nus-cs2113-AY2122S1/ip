package duke;

import duke.exceptions.*;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    //TODO remove these 2 constants
    public static final String DEADLINE_BY_PREFIX = "/by";
    public static final String EVENT_AT_PREFIX = "/at";
    public static final boolean IS_STARTING = true;
    public static final boolean IS_ENDING = false;
    public static final File DATA_DIRECTORY = new File("data");
    public static final File DATA_FILE = new File(DATA_DIRECTORY.getPath().concat("/duke.txt"));
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    
    
    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_DIRECTORY,DATA_FILE);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException fnfe) {
            tasks = new TaskList();
        } catch (IOException ioe) {
            
        }
        
    }
    
    public void start() {
        //loadPreviousData();
        ui.showStartingOrEndingMessage(IS_STARTING);
        runDuke();
        ui.showStartingOrEndingMessage(IS_ENDING);
    }

    public static void main(String[] args) {
        /**
        loadPreviousData();
        UI.showStartingOrEndingMessage(IS_STARTING);
        runDuke();
        UI.showStartingOrEndingMessage(IS_ENDING);*/
        new Duke().start();
    }

//    private void loadPreviousData() {
//        System.out.print("Loading data... ");
//        if (!DATA_DIRECTORY.isDirectory()) {
//            DATA_DIRECTORY.mkdirs();
//        }
//        if (!DATA_FILE.exists()) {
//            System.out.println("No previous data found");
//            return;
//        }
//        try {
//            Scanner loadingScanner = new Scanner(DATA_FILE);
//            while (loadingScanner.hasNext()) {
//                String nextLine = loadingScanner.nextLine();
//                loadTask(nextLine);
//            }
//            System.out.println("Data loaded successfully");
//        } catch (IOException e) {
//            System.out.println("ERROR : " + e);
//        }
//    }

//    private void loadTask(String taskDetails) throws IOException {
//        String[] splitTaskDetails = taskDetails.split("\\|");
//        tasks.addLoadedTask(splitTaskDetails);
//    }

    private void runDuke() {
        boolean conversationIsOver = false;
        while (!conversationIsOver) {
            try {
                String inputCommand = ui.readCommand();
                Command c = Parser.parse(inputCommand);
                c.execute(tasks, storage, ui);
                conversationIsOver = c instanceof ExitCommand;
            } catch (DukeException de) {
                ui.showError(de);
            } catch (InvalidCommandException ice) {
                ui.showHelpMessage();
            } catch (IOException ioe) {
                ui.showMessage("Error loading/saving data!!");
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                ui.showMessage("Please enter a valid number after 'done' or 'delete'");
            }
        }
    }
}
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {
    private static ArrayList<Task> tasks;
    private static Ui ui;
    private static Storage storage;
    private static Parser parser;

    public static void run() throws IOException{
        initDuke();
        runDuke();
        exitDuke();
    }

    public static void initDuke() throws IOException {
        tasks = new ArrayList<>();
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();

        try {
            storage.initSaveFile(tasks);
        } catch (FileNotFoundException e) {
            storage.createSaveFile();
        }
    }

    public static void runDuke() {
        Scanner in = new Scanner(System.in);
        String line;

        // start the taskbot
        ui.printWelcome();
        line = in.nextLine();

        // process inputs by user
        parser.parseInputs(in, line, tasks);
    }

    public static void exitDuke() {
        // save to file and exit after finished
        try {
            storage.writeToSave(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        ui.printExit();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();

    }
}

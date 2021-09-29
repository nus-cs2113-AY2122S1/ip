import duke.Parser;
import duke.Ui;
import duke.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) throws IOException {
        Ui.helloWorld();
        String line = "";
        Scanner in = new Scanner(System.in);

        try {  // Initialize ./data/duke.txt to store tasks
            Storage.initDataStore();
        } catch (IOException e) {
            Ui.displayNoData();
        }
        try {  // Load all tasks stored in ./data/duke.txt
            Storage.loadTasks();
        } catch (FileNotFoundException e) {
            Ui.displayNoFile();
        } catch (duke.DukeException e) {
            Ui.displayWrongFile();
        }

        while (!(line.equals("bye") || line.equals("Bye"))) {
            line = in.nextLine();
            line = line.toLowerCase();

            Parser.Parser(line);
        }

        Ui.goodBye();
        Ui.printHorizontalLine();

    }




}


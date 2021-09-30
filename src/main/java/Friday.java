import parser.InputParser;
import ui.MessagePrinter;
import storage.LoadData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class containing and running main method of entire program
 * Greets user upon initialisation and loads data from storage if data exists.
 * If no prior data is stored, it will create a file under specified path to store user's data
 */
public class Friday {
    public static void main(String[] args) {
        MessagePrinter.initiateProgram();
        try {
            LoadData.loadData();
        } catch (FileNotFoundException e1) {
            try {
                Path path = Paths.get("data/friday.txt");
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        MessagePrinter.greetUser();
        InputParser.parseUserInput();
        MessagePrinter.exitMessage();
    }
}

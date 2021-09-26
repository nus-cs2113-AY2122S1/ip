package duke;

import java.io.FileNotFoundException;

public class Duke {

    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        int currCount = 0;
        try {
            currCount = Storage.readFile();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found");
        }
        Parser.executeCommands(currCount);
        Ui.printGoodBye();
    }
}



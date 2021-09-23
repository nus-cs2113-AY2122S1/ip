import java.io.IOException;

public class Duke {

    public Duke() {
        Ui.welcomeMessage();
        try {
            Storage.initializeStorage();

        } catch (IOException e) {
            Ui.printlnTab(Storage.dataPath + " not found or cannot be created!");
            Ui.printDivider();
            return;
        }
        //TODO: Further testing to see if this try block is necessary
//        try {
        Parser.executeCommand();
//        } catch (NoSuchElementException e) {
//            Ui.printlnTab("Bye. Hope to see you again soon!");
//            Ui.printDivider();
//        }
    }

    public static void main(String[] args) {
        new Duke();
    }
}

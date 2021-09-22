import java.io.IOException;

public class Duke {


    public Duke() {
        Ui.welcomeMessage();
        try {
            Storage.initializeFile();

        } catch (IOException e) {
            Ui.printlnTab("Failed to initialize storage file!");
        }
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

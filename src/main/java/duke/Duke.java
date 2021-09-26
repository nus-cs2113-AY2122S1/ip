package duke;

public class Duke {

    public static void main(String[] args) {
        UI.printWelcome();
        String userLineInput;
        Storage.loadFile();
        do {
            userLineInput = UI.getUserInput();
            Parser.parseInput(userLineInput);
            UI.printLineSpacer();
        } while (!userLineInput.equals("bye"));
        UI.printBye();
    }
}

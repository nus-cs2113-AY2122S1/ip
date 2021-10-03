package duke;

public class Duke {
    public static void main(String[] args) {
        UI.printWelcomeMessage();
        String lineInput = "";
        Storage.getFromFile();
        while (!lineInput.equals("bye")) {
            lineInput = UI.getInput();
            Parser.parseInput(lineInput);
        }
    }
}

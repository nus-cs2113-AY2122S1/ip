package duke;

public class Duke {
    public static void main(String[] args) {
        duke.UI.printWelcomeMessage();
        String lineInput = "";
        duke.Storage.getFromFile();
        while (!lineInput.equals("bye")) {
            lineInput = duke.UI.getInput();
            duke.Parser.parseInput(lineInput);
        }
    }
}
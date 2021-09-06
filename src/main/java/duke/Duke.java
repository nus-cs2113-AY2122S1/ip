package duke;

import duke.validation.DukeException;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        UI.printWelcomeMessage();
        TaskManager t1 = new TaskManager();
        String input;
        String[] inputWords;
        String command;
        Scanner in = new Scanner(System.in);
        do {
            input = in.nextLine().trim();
            inputWords = t1.decodeInput(input);
            command = inputWords[0];
            switch (command) {
            case "todo":
            case "deadline":
            case "event":
                t1.addTask(input, inputWords, command);
                break;
            case "done":
                t1.crossOff(inputWords);
                break;
            case "list":
                t1.printList();
                break;
            default:
                DukeException.invalidInputException();
                break;
            }
        } while (!command.equals("bye"));
        UI.printEndMessage();
    }
}

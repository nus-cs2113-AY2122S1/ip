package duke;

import duke.validation.DukeException;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
        UI.printWelcomeMessage();
        String input;
        String[] inputWords;
        String command;
        Scanner in = new Scanner(System.in);
        do {
            input = in.nextLine().trim();
            inputWords = TaskManager.decodeInput(input);
            command = inputWords[0];
            switch (command) {
            case "todo":
            case "deadline":
            case "event":
                TaskManager.addTask(input, inputWords, command);
                break;
            case "done":
                TaskManager.crossOff(inputWords);
                break;
            case "delete":
                TaskManager.deleteTask(inputWords);
                break;
            case "list":
                TaskManager.printList();
                break;
            default:
                DukeException.invalidInputException();
                break;
            }
        } while (!command.equals("bye"));
        UI.printEndMessage();
    }
}

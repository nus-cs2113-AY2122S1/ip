package duke;

import duke.validation.InvalidFormatException;
import duke.validation.InvalidIndexException;
import duke.validation.InvalidInputException;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        UI.printWelcomeMessage();
        InputFile.readSave();
        String input;
        String[] inputWords;
        String command;
        Scanner in = new Scanner(System.in);
        do {
            input = in.nextLine().trim();
            inputWords = TaskManager.decodeInput(input);
            command = inputWords[0];
            try {
                switch (command) {
                case "todo":
                    TaskManager.addToDo(input, inputWords);
                    break;
                case "deadline":
                    TaskManager.addDeadline(input, inputWords);
                    break;
                case "event":
                    TaskManager.addEvent(input, inputWords);
                    break;
                case "done":
                    TaskManager.crossOff(inputWords);
                    break;
                case "delete":
                    TaskManager.deleteTask(inputWords);
                    break;
                case "list":
                    TaskManager.printList(inputWords);
                    break;
                case "bye":
                    break;
                default:
                    throw new InvalidInputException();
                }
            } catch (InvalidFormatException | InvalidIndexException | InvalidInputException e) {
                UI.printExceptionMessage(e);
            }
        } while (!command.equals("bye"));
        InputFile.writeSave();
        UI.printEndMessage();
    }
}

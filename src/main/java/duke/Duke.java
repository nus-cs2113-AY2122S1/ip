package duke;

import duke.task.*;
import duke.exception.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static ArrayList<Task> taskArrayList = new ArrayList<Task>();

    private static void handleInput(String input) throws DukeCommandException {
        String command = input.split(" ")[0];
        switch (command) {
        case "delete":
            Command.executeDelete(input, taskArrayList);
            break;

        case "done":
            Command.executeDone(input, taskArrayList);
            break;

        case "list":
            Command.executeList(taskArrayList);
            break;

        case "todo":
        case "deadline":
        case "event":
            try {
                Command.executeAdd(input, taskArrayList);
            } catch (IndexOutOfBoundsException | DukeInvalidInputException e) {
                Messages.printDivider();
                System.out.println("Invalid input. Please try again");
                Messages.printDivider();
            }
            break;
        default:
           throw new DukeCommandException();
        }
    }

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        Messages.welcomeMessage();

        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            try {
                handleInput(userInput);
            } catch (DukeCommandException e) {
                Messages.printDivider();
                System.out.println("Invalid command entered. Please try again");
                Messages.printDivider();
            }
            userInput = in.nextLine();
        }

        Messages.exitMessage();
    }
}

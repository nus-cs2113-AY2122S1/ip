package duke;

import duke.task.*;
import duke.exception.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static final String DOTTED_LINE = "___________________________________________________\n";
    private static ArrayList<Task> taskArrayList = new ArrayList<Task>();

    private static void handleInput(String input) throws DukeCommandException {
        String command = input.split(" ")[0];
        switch (command) {
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
                System.out.println(DOTTED_LINE);
                System.out.println("Invalid input. Please try again");
                System.out.println(DOTTED_LINE);
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
                System.out.println(DOTTED_LINE);
                System.out.println("Invalid command entered. Please try again");
                System.out.println(DOTTED_LINE);
            }
            userInput = in.nextLine();
        }

        Messages.exitMessage();
    }
}

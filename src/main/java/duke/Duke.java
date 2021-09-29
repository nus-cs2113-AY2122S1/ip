package duke;

import duke.data.Storage;
import duke.exception.DukeInvalidInputException;
import duke.exception.DukeTimeFormatException;
import duke.exception.DukeParameterException;
import duke.exception.DukeCommandException;
import duke.exception.DukeTaskNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Storage.load();
        Ui.printWelcomeMessage();
        run();
        Ui.printExitMessage();
    }

    /**
     * This function acts as the driving loop for the program where it continuously
     * takes in user inputs and handles them till an exit command is given
     */
    private static void run() {
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        while (!userInput.equals("bye")) {
            try {
                ArrayList<String> arguments = Parser.parseInput(userInput);
                Parser.handleInput(arguments);
            } catch (DukeCommandException e) {
               Ui.printInvalidCommandMessage();
            } catch (DukeParameterException e) {
                Ui.printParameterErrorMessage();
            } catch (DukeInvalidInputException e) {
                Ui.printInvalidInputMessage();
            } catch (DukeTimeFormatException e) {
                Ui.printTimeParseErrorMessage();
            } catch (DukeTaskNotFoundException e) {
                Ui.printInvalidTaskMessage();
            }
            userInput = input.nextLine();
        }
    }
}
